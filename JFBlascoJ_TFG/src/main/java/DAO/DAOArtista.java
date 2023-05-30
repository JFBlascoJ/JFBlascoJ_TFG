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
 * DAO para el POJO Artista.
 * @author José Francisco Blasco Jiménez
 */
public class DAOArtista implements IDAO<Artista>{
    private MongoClient cliente;
    private MongoDatabase db;
    private MongoCollection col;
    private DAOEvento dEv;
            
    
    public DAOArtista (MongoClient cli, DAOEvento daoEv) {
        cliente = cli;
        db = cliente.getDatabase("salaConciertos");
        col = db.getCollection("artistas");
        dEv = daoEv;
    }
    
    /**
     * Recupera los datos de todos los documentos de la colección "artistas".
     */
    @Override
    public ArrayList<Artista> consultarTodos(boolean ordenar) { 
        Document aux;
        Artista a;
        ArrayList<Artista> lista = new ArrayList();
        FindIterable resul = col.find().sort(ordenar ? new Document("nombre", 1) : new Document("_id", 1));
        MongoCursor<Document> cur = resul.cursor();
        // Recorremos el cursor, hacemos la conversión y añadimos el POJO a la lista.
        while (cur.hasNext()) {
            aux = cur.next();
            a = new Artista(aux);
            // Asignamos a cada artista los eventos en los que aparece.
            asignaEventosAArtista(a);
            lista.add(a);
        }
        return lista;
    }
    
    public ArrayList<Artista> consultarTodosSinCero(boolean ordenar) {
        ArrayList<Artista> artistas = consultarTodos(ordenar);
        for (Artista a : artistas) {
            if (a.getId().equals("A0")) {
                artistas.remove(a);
                break;
            }
        }
        return artistas;
    }
    
    /**
     * Busca los eventos en los que participa el artista y se los asigna.
     * @param a Artista al que se le van a asignar dichos eventos.
     */
    public void asignaEventosAArtista(Artista a) {
        MongoCollection colEv = db.getCollection("eventos");
        a.setEventos(new ArrayList());
        Document docEvento, query = new Document("artistas", a.getId());
        
        FindIterable eventos = colEv.find(query);
        MongoCursor<Document> cur = eventos.cursor();
        while (cur.hasNext()) {
            docEvento = cur.next();
            a.getEventos().add(new Evento(docEvento));
        }
    }
    
    /**
     * Consulta los artistas que no participan en ningún evento.
     * Estos artistas son los que se pueden borrar.
     * @return lista de artistas que no paracen en nigún evento.
     */
    public ArrayList<Artista> consultarSinEvento() {
        ArrayList<Artista> lista = new ArrayList();
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
        camposLookup.append("foreignField", "artistas");
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
        
        while (cur.hasNext())
            lista.add(new Artista(cur.next()));
        return lista;
    }
    
    /**
     * Da de alta un nuevo registro en la colección "artistas".
     * @param a Artista a registrar.
     * @return True si se ha podido registrar. False en caso contrario
     */
    @Override
    public boolean insertar(Artista a) {
        // No es necesario comprobar si ya existe el artista porque se calcula el id.
        a.setId(calculaId());
        Document doc = a.toDocument(false);
        
        try {
            col.insertOne(doc);
        } catch (MongoException e) {
            return false;       
        }
        return true;
    }
    
    /**
     * Elimina el registro con el identificador pasado por parámetro.
     * @param id Identificador del artista que queremos borrar.
     * @return True si se ha eliminado. False en caso contrario.
     */
    @Override
    public boolean borrar(String id) {
        Document aux;
        id = id.toUpperCase();
        // Rellenamos la lista con los técnicos que no participan en ningún evento.
        Artista a = consultarPorId(id);
        // Si no existe, no se puede realizar la operación.
        if (a == null)
            return false;
        try {
            cambiaIdPorNombre(id);
            eliminaDeEvento(id);
            aux = new Document("_id", id);
            col.deleteOne(aux);
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return true;
//        asignaEventosAArtista(a);
//        // Si tiene eventos asignados, no se puede eliminar
//        if (a.getEventos().isEmpty()) {
//            aux = new Document("_id", id);
//            try {
//                col.deleteOne(aux);
//            } catch (MongoException e) {
//                return false;
//            }
//            return true;
//        } else {
//            // Este mensaje de consola es únicamente para hacer la prueba en la clase DAOTest
//            System.out.println("El artista " + a.getNombre() +", con id " + a.getId()
//                    + " todavía tiene eventos asignados y no se puede eliminar");
//            return false;
//        }
    }
    
    public void cambiaIdPorNombre(String id) {
        HashMap<String, Object> datos = new HashMap();
        
        datos.put("artistas", id);
        datos.put("fechaAl", LocalDate.now());
        ArrayList<Evento> eventos = dEv.filtrar(datos);
        
        for (Evento e : eventos) {
            dEv.cambiaIdPorNombreAlBorrarTecnicoOArtista(e, id);
        }
    }
    
    public void eliminaDeEvento(String id) {
        HashMap<String, Object> datos = new HashMap();
        
        datos.put("artistas", id);
        datos.put("fechaDesde", LocalDate.now());
        ArrayList<Evento> eventos = dEv.filtrar(datos);
        
        for (Evento e : eventos) {
            dEv.eliminaTecnicoOArtistaDeEvento(e, id);
        }
    }


    /**
     * Modifica los datos de un artista existente.
     * @param id Identificador del artista a modificar.
     * @param nuevo Artista con los nuevos datos.
     * @return True si se ha modificado. False en caso contrario.
     */
    @Override
    public boolean modificar(String id, Artista nuevo) {
        id = id.toUpperCase();
        Artista a = consultarPorId(id);
        Document doc, find, update;
        ArrayList<Evento> eventos;
        // Si no existe, devuelve false.
        if (a == null)
            return false;
        try {            
            doc = nuevo.toDocument(true);
            /* Indicamos el criterio de búsqueda.
             * Utilizamos "$set" para indicar los campos que se van a actualizar.*/
            find = new Document("_id", id);
            update = new Document();
            update.append("$set", new Document(doc));
            col.updateOne(find, update);
            
            // Si se ha modificado el rider del artista, se obtienen los eventos en los que ha participado.
            if (!a.getRider().equals(nuevo.getRider())) {
                eventos = dEv.consultarPorArtista(id);
                // Se recorre la lista de eventos y see modifica el rider correspondiente a cada uno de ellos.
                for (Evento e : eventos) {
                    e.calculaRider();
                    dEv.modificar(e.getId(), e);
                }
            }                
            } catch (MongoException e) {
                return false;
            }   
        return true;
    }
    
    /**
     * Consulta artista por identificador de artista.
     * @param id Identificador del artista.
     * @return Artista con ese identificador si existe. Si no existe, null.
     */
    @Override
    public Artista consultarPorId(String id) {
        id = id.toUpperCase();
        Document query = new Document("_id", id);
        Artista a;
        Document aux;
        try {
            FindIterable<Document> resul = col.find(query);
            // Como hay un único arista con ese id, nos quedamos con el primero.
            aux = resul.first();
            a = new Artista(aux);
        } catch (MongoException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
        return a;
    }
    
    @Override
    public ArrayList<Artista> filtrar(HashMap<String, Object> datos) {
        Artista a;
        MongoCursor<Document> cur;
        ArrayList<Artista> artistas = new ArrayList();
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
                            new Document("$regex", ".*" + datos.get(key) + ".*"));
                    break;
                case ("email"):
                    query.append(key,
                            new Document("$regex", ".*" + datos.get(key) + ".*").append("$options", "i"));
            }
        }
        
        FindIterable<Document> resul = col.find(query);
        cur = resul.cursor();
        
        while (cur.hasNext()) {
            a = new Artista(cur.next());
            artistas.add(a);
        }
        
        return artistas;
    }
    

    /**
     * Calcula el número del id del del artista que se va a añadir a la colección.
     * Para ello, hace una consulta de todos los artistas, obteniendo el identificador del último de ellos.
     * Obtiene el número y le suma 1.
     * @return El número correspondiente al nuevo registro.
     */
    public Integer calculaId() {
        FindIterable resul = col.find();
        ArrayList <Document> listaResul = new ArrayList();
        Document doc;
        String cad;
        Integer n;        
        
        resul.into(listaResul);
        
        if (listaResul.isEmpty())
            return 1;
        doc = listaResul.get(listaResul.size() - 1);
        cad = doc.getString("_id");
        n = Integer.parseInt(cad.substring(1));
        
        return n + 1;        
    }
}
