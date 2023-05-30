package DAO;

import POJOs.*;
import assets.objects.Comunes;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import java.time.LocalDate;
import java.util.*;
import org.bson.Document;

/**
 * DAO para el POJO Tecnico.
 *
 * @author José Francisco Blasco Jiménez
 */
public class DAOTecnico implements IDAO<Tecnico> {

    private MongoClient cliente;
    private MongoDatabase db;
    private MongoCollection col;
    DAOEvento dEv;

    public DAOTecnico(MongoClient cli, DAOEvento daoEv) {
        cliente = cli;
        db = cliente.getDatabase("salaConciertos");
        col = db.getCollection("tecnicos");
        dEv = daoEv;
    }

    /**
     * Da de alta un nuevo registro en la colección "tecnicos".
     *
     * @param t Técnico a registrar.
     * @return True si se ha podido registrar. False en caso contrario.
     */
    @Override
    public boolean insertar(Tecnico t) {
        t.setId(calculaId());
        // No es necesario comprobar si ya existe el técnico porque se calcula el id.        
        Document aux = t.toDocument(false);
        try {
            col.insertOne(aux);
        } catch (MongoException e) {
            return false;
        }
        return true;
    }

    /**
     * Elimina el registro con el identificador pasado por parámetro.
     *
     * @param id Identificador del artista que queremos borrar.
     * @return True si se ha eliminado. False en caso contrario.
     */
    @Override
    public boolean borrar(String id) {
        Document aux;
        id = id.toUpperCase();
        // Rellenamos la lista con los técnicos que no participan en ningún evento.
        Tecnico t = consultarPorId(id);

        // Si no existe, no se puede realizar la operación.
        if (t == null) {
            return false;
        }
        try {
            cambiaIdPorNombre(id);
            eliminaDeEvento(id);
            aux = new Document("_id", id);
            col.deleteOne(aux);
        } catch (MongoException e) {
            return false;
        }
        return true;

//        asignaEventosATecnico(t);
        // Si tiene eventos asignados, no se puede eliminar
//        if (t.getEventos().isEmpty()) {
//            aux = new Document("_id", id);
//            try {
//                col.deleteOne(aux);
//            } catch (MongoException e) {
//                return false;
//            }
//            return true;
//        } else {
//            // Este mensaje de consola es únicamente para hacer la prueba en la clase DAOTest
//            System.out.println("El técnico " + t.getNombre() +", con id " + t.getId()
//                    + " todavía tiene eventos asignados y no se puede eliminar");
//            return false;
//        }
    }

    public void cambiaIdPorNombre(String id) {
        Evento aux;
        String nombreTecnico = consultarPorId(id).getNombre();
        HashMap<String, Object> datos = new HashMap();
        datos.put("tecnico", id);
        datos.put("fechaAl", LocalDate.now());

        ArrayList<Evento> eventos = dEv.filtrar(datos);

        for (Evento e : eventos) {
            dEv.cambiaIdPorNombreAlBorrarTecnicoOArtista(e, id);
        }
    }
    
     
    public void eliminaDeEvento(String id) {
        HashMap<String, Object> datos = new HashMap();
        
        datos.put("tecnico", id);
        datos.put("fechaDesde", LocalDate.now());
        ArrayList<Evento> eventos = dEv.filtrar(datos);
        
        for (Evento e : eventos) {
            dEv.eliminaTecnicoOArtistaDeEvento(e, id);
        }
    }

    /**
     * Modifica los datos de un técnico existente.
     *
     * @param id Identificador del técnico a modificar.
     * @param nuevo Técnico con los nuevos datos.
     * @return True si se ha modificado. False en caso contrario.
     */
    @Override
    public boolean modificar(String id, Tecnico nuevo) {
        id = id.toUpperCase();
        Tecnico t = consultarPorId(id);
        Document doc, find, update;

        if (t == null) {
            return false;
        }
        try {
            doc = nuevo.toDocument(true);
            /* Indicamos el criterio de búsqueda.
             * Utilizamos "$set" para indicar los campos que se van a actualizar.*/
            find = new Document("_id", id);
            update = new Document("$set", new Document(doc));
            col.updateOne(find, update);
        } catch (MongoException e) {
            return false;
        }
        return true;
    }

    /**
     * Consulta técnico por identificador de artista.
     *
     * @param id Identificador del técnico.
     * @return Técnico con ese identificador si existe. Si no existe, null.
     */
    @Override
    public Tecnico consultarPorId(String id) {
        id = id.toUpperCase();
        Document query = new Document("_id", id);
        Tecnico t;
        Document aux;
        try {
            FindIterable<Document> resul = col.find(query);
            // Como solo hay un técnico con ese id, nos quedamos con el primero.
            aux = resul.first();
            t = new Tecnico(aux);
        } catch (MongoException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
        return t;
    }

    /**
     * Recupera los datos de todos los documentos de la colección "artistas".
     */
    @Override
    public ArrayList<Tecnico> consultarTodos(boolean ordenar) {
        Document aux;
        Tecnico t;
        ArrayList<Tecnico> lista = new ArrayList();
        FindIterable resul = col.find().sort(ordenar ? new Document("nombre", 1) : new Document("_id", 1));
        MongoCursor<Document> cursor = resul.cursor();
        // Recorremos el cursor, hacemos la conversión y añadimos el POJO a la lista.
        while (cursor.hasNext()) {
            aux = cursor.next();
            t = new Tecnico(aux);
//            asignaEventosATecnico(t);
            lista.add(t);
        }
        return lista;
    }
    
    
    public ArrayList<Tecnico> consultarTodosSinCero(boolean ordenar) {
        ArrayList<Tecnico> tecnicos = consultarTodos(ordenar);
        for (Tecnico t : tecnicos) {
            if (t.getId().equals("T0")) {
                tecnicos.remove(t);
                break;
            }
        }
        return tecnicos;        
    }

    /**
     * Busca los eventos en los que participa el técnico y se los asigna.
     *
     * @param t Artista al que se le van a asignar dichos eventos.
     */
    public void asignaEventosATecnico(Tecnico t) {
        MongoCollection colEv = db.getCollection("eventos");
        t.setEventos(new ArrayList());
        Document docEvento, query = new Document("tecnico", t.getId());

        FindIterable eventos = colEv.find(query);
        MongoCursor<Document> cur = eventos.cursor();
        while (cur.hasNext()) {
            docEvento = cur.next();
            t.getEventos().add(new Evento(docEvento));
        }
    }

    /**
     * Calcula el número del id del del técnico que se va a añadir a la
     * colección. Para ello, hace una consulta de todos los técnicos, obteniendo
     * el identificador del último de ellos. Obtiene el número y le suma 1.
     *
     * @return El número correspondiente al nuevo registro. 1 si la colección
     * está vacía.
     */
    public Integer calculaId() {
        FindIterable resul = col.find();
        ArrayList<Document> listaResul = new ArrayList();
        Document doc;
        String cad;
        Integer n;

        resul.into(listaResul);

        // Si la colección está vacía, devuelve 1. Si no, Obtiene el número del último registro y le suma 1.
        if (listaResul.isEmpty()) {
            return 1;
        }
        doc = listaResul.get(listaResul.size() - 1);
        cad = doc.getString("_id");
        n = Integer.parseInt(cad.substring(1));

        return n + 1;
    }

    /**
     * Consulta los técnicos que no participan en ningún evento. Estos técnicos
     * son los que se pueden borrar.
     *
     * @return lista de técnicos que no aparacen en nigún evento.
     */
    public ArrayList<Tecnico> consultarSinEvento() {
        ArrayList<Tecnico> lista = new ArrayList();
        Document camposLookup, lookup, camposProject, project, condicion;
        MongoCursor<Document> cur;

        /* Utilizamos "$lookup" para buscar datos en una colección externa.
         * Indicamos los siguiente:
         *  - Colección externa en la que queremos buscar.
         *  - Campo local con el que se van a corresponder los datos de dicha colección.
         *  - Campo de la colección externa que se corresponden con el campo local.
         *  - Nombre del campo en la consulta que vamos a hacer.
         */
        camposLookup = new Document("from", "eventos");
        camposLookup.append("localField", "_id");
        camposLookup.append("foreignField", "tecnico");
        camposLookup.append("as", "eventos");
        lookup = new Document("$lookup", camposLookup);

        /* Utilizamos "$project" para indicar los campos de la consulta que vamos a hacer
         * El número 1 indica que el valor de ese campo es el mismo que se recupera en la consulta.
         * Para indicar un campo de la colección externa, hay que espeficicar el nombre que le hemos dado al campo en el lookup
         * seguido de un punto y el nombre del campo en cuestión, en este caso "$eventos.nombre".
         */
        camposProject = new Document("_id", 1);
        camposProject.append("nombre", 1);
        camposProject.append("rider", 1);
        camposProject.append("eventos", "$eventos.nombre");
        project = new Document("$project", camposProject);

        /* Utilizamos "$match" para indicar que únicamente queremos recuperar los artistas que cumplan una condición (similar a WHERE en SQL).
         * En este caso, queremos solamente los elementos que tengan vacío el campo "eventos.
         */
        condicion = new Document("$match", new Document("eventos", new ArrayList()));

        /* Metemos los tres documento que hemos creado en una colección y hacemos la agregación.
         * Creamos un cursor para poder acceder a los resultados y los metemos en la lista que devolveremos.
         */
        List<Document> pipeline = Arrays.asList(lookup, project, condicion);
        AggregateIterable<Document> resul = col.aggregate(pipeline);
        cur = resul.cursor();

        while (cur.hasNext()) {
            lista.add(new Tecnico(cur.next()));
        }

        return lista;
    }

    @Override
    public ArrayList<Tecnico> filtrar(HashMap<String, Object> datos) {
        Tecnico t;
        MongoCursor<Document> cur;
        ArrayList<Tecnico> tecnicos = new ArrayList();
        Document query = new Document();
        Set<String> keys = datos.keySet();
        for (String key : keys) {
            switch (key) {
                case ("nombre"):
                    query.append(key,
                            new Document("$regex", ".*" + datos.get(key) + ".*").append("$options", "i"));
                    break;
                case ("tlf"):
                    query.append(key,
                            new Document("$regex", ".*" + datos.get(key) + ".*").append("$options", "i"));
                    break;
                case ("email"):
                    query.append(key,
                            new Document("$regex", ".*" + datos.get(key) + ".*").append("$options", "i"));
            }
        }

        FindIterable<Document> resul = col.find(query);
        cur = resul.cursor();

        while (cur.hasNext()) {
            t = new Tecnico(cur.next());
            tecnicos.add(t);
        }

        return tecnicos;
    }
}
