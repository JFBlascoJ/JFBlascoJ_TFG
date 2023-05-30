package DAO;

import POJOs.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import org.bson.Document;

/**
 * DAO para el POJO Evento.
 *
 * @author José Francisco Blasco Jiménez.
 */
public class DAOEvento implements IDAO<Evento> {

    private MongoClient cliente;
    private MongoDatabase db;
    private MongoCollection col;
    private DAOTecnico dTec;
    private DAOArtista dArt;

    public DAOEvento(MongoClient cli) {
        cliente = cli;
        db = cliente.getDatabase("salaConciertos");
        col = db.getCollection("eventos");
        dArt = new DAOArtista(cli, this);
        dTec = new DAOTecnico(cli, this);
    }

    /**
     * Da de alta un nuevo registro en la colección "eventos".
     *
     * @param ev Evento a registrar.
     * @return True si se ha podido registrar. False en caso contrario
     */
    @Override
    public boolean insertar(Evento ev) {
        ev.setId(calculaId());
        // No es necesario comprobar si ya existe el evento porque se calcula el id.
        Document aux;
        // Como no sabemos si se ha calculado el rider nates de llamar a este método, lo calculamos.
        ev.calculaRider();

        try {
            // Insertamos el evento
            aux = ev.toDocument(false);
            col.insertOne(aux);
        } catch (MongoException e) {
            return false;
        }
        return true;
    }

    /**
     * Elimina el registro con el identificador pasado por parámetro.
     *
     * @param id Identificador del evento que queremos borrar.
     * @return True si se ha eliminado. False en caso contrario.
     */
    @Override
    public boolean borrar(String id) {
        id = id.toUpperCase();
        Evento ev = consultarPorId(id);
        if (ev == null) {
            return false;
        }
        Document aux = new Document("_id", id);
        try {
            col.deleteOne(aux);
        } catch (MongoException e) {
            return false;
        }
        return true;
    }

    /**
     * Modifica los datos de un evento existente.
     *
     * @param id Identificador del evento a modificar.
     * @param nuevo Evento con los nuevos datos.
     * @return True si se ha modificado. False en caso contrario.
     */
    @Override
    public boolean modificar(String id, Evento nuevo) {
        id = id.toUpperCase();
        Evento ev = consultarPorId(id);
        Document doc, find, update;

        if (ev == null) {
            return false;
        }
        try {
            doc = nuevo.toDocument(true);
            /* Indicamos el criterio de búsqueda.
             * Utilizamos "$set" para indicar los campos que se van a actualizar.*/
            find = new Document("_id", id);
            update = new Document("$set", doc);
            col.updateOne(find, update);

        } catch (MongoException e) {
            return false;
        }
        return true;
    }

    public boolean cambiaIdPorNombreAlBorrarTecnicoOArtista(Evento ev, String idElemento) {
        Document doc = null, update, find = new Document("_id", ev.getId());
        LocalDate hoy = LocalDate.now();
        
        ArrayList<String> idsArtistas;
        Artista aux;
        String nombre = null, indiceDoc = null;
        int indice;

        if (ev.getId() == null) {
            return false;
        }
        try {
            switch (idElemento.toUpperCase().charAt(0)) {
                case 'T':
                    doc = new Document("tecnico", ev.getTecnico().getNombre());
                    break;
                case 'A':
                    for (int i = 0; i < ev.getArtistas().size(); i++) {
                        aux = ev.getArtistas().get(i);
                        if (aux.getId().equals(idElemento)) {
                            indiceDoc = "artistas." + i;
                            indice = i;
                            nombre = aux.getNombre();
                            break;
                        }
                    }
                    doc = new Document(indiceDoc, nombre);
                    break;
            }
            update = new Document("$set", doc);
            col.updateOne(find, update);
        } catch (MongoException e) {
            return false;
        }

        return true;
    }
    
    public boolean eliminaTecnicoOArtistaDeEvento(Evento ev, String idElemento) {
        Document doc = null, update = null, find = new Document("_id", ev.getId());
        LocalDate hoy = LocalDate.now();
        
        if (ev.getId() == null) {
            return false;
        }
        try {
            switch (idElemento.toUpperCase().charAt(0)) {
                case 'T':
                    doc = new Document("tecnico", "T0");
                    update = new Document("$set", doc);
                    break;
                case 'A':
                    if (ev.getArtistas().size() == 1)
                        update = new Document("$set", new Document("artistas", "A0"));
                    else
                        update = new Document("$pull", new Document("artistas", idElemento));
                    // Expresión de actualización utilizando el operador $pull para eliminar "A2" del array "artistas" y el operador $cond para establecer el campo en null si el array queda vacío
//                    update = new Document("$cond", Arrays.asList(
//                        new Document("$eq", Arrays.asList(new Document("$size", "$artistas"), 1)),
//                        new Document("artistas", "A0"),
//                        new Document("$pull", new Document("artistas", "A2"))
//                    ));
                    break;
            }
            col.updateOne(find, update);
        } catch (MongoException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Consulta evento por identificador de artista.
     *
     * @param id Identificador del evento.
     * @return Evento con ese identificador si existe. Si no existe, null.
     */
    @Override
    public Evento consultarPorId(String id) {
        id = id.toUpperCase();
        Document query = new Document("_id", id);
        Evento ev;
        Document aux;
        try {
            FindIterable<Document> resul = col.find(query);
            // Como solo hay un evento con ese id, nos quedamos con el primero.
            aux = resul.first();
            ev = new Evento(aux);
            asignaTecnicoYArtistas(ev, aux);
        } catch (MongoException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
        return ev;
    }

    @Override
    public ArrayList<Evento> filtrar(HashMap<String, Object> datos) {
        Evento ev;
        MongoCursor<Document> cur;
        ArrayList<Evento> eventos = new ArrayList();
        Document query = new Document(), aux;
        Set<String> keys = datos.keySet();
        for (String key : keys) {
            switch (key) {
                case ("nombre"):
                    query.append(key,
                            new Document("$regex", ".*" + datos.get(key) + ".*").append("$options", "i"));
                    break;
                case ("fechaDel"):
                    if (keys.contains("fechaAl")) {
                        Document docBetween = new Document("$gte", datos.get(key));
                        docBetween.append("$lte", datos.get("fechaAl"));
                        query.append("fecha", docBetween);
                    } else {
                        query.append("fecha", datos.get(key));
                    }
                    break;
                case ("fechaAl"):
                    if (!keys.contains("fechaDel")) {
                        Document docLessEqual = new Document("$lte", datos.get(key));
                        query.append("fecha", docLessEqual);
                    }
                        break;
                case ("tecnico"):
                    query.append(key, datos.get(key));
                    break;
                case ("artistas"):
                    if (datos.get("artistas") instanceof String)
                        query.append(key, datos.get(key));
                    else if (datos.get("artistas") instanceof String[])
                        query.append(key, new Document("$all", datos.get(key)));
            }
        }

        FindIterable<Document> resul = col.find(query);
        cur = resul.cursor();

        while (cur.hasNext()) {
            aux = cur.next();
            ev = new Evento(aux);
            asignaTecnicoYArtistas(ev, aux);
            eventos.add(ev);
        }

        return eventos;
    }
    
    public boolean comprobarDisponibilidad(LocalDate fecha) {
        ArrayList<Evento> eventos = filtrar(new HashMap<String, Object>() {{
            put("fechaDel", fecha);
        }});
        
        return eventos.isEmpty() ? true : false;
    }

    /**
     * Busca el evento asignado al rider cuyo ObjectId es pasado por parámetro.
     *
     * @param idRider ObjectId del rider asignado al evento cuyos datos se
     * quieren recuperar.
     * @return Evento que tiene asignado el rider en cuestión.
     */
    public Evento consultarPorRider(String idRider) {
        idRider = idRider.toUpperCase();
        Document query = new Document("rider", idRider);
        Evento ev;
        Document aux;
        try {
            FindIterable<Document> resul = col.find(query);
            aux = resul.first();
            ev = new Evento(aux);
            asignaTecnicoYArtistas(ev, aux);
        } catch (MongoException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
        return ev;

    }

    /**
     * Busca el evento asignado al rider cuyo ObjectId es pasado por parámetro.
     *
     * @param idArtista ObjectId del rider asignado al evento cuyos datos se
     * quieren recuperar.
     * @return Evento que tiene asignado el rider en cuestión.
     */
    public ArrayList<Evento> consultarPorArtista(String idArtista) {
        ArrayList<Evento> lista = new ArrayList();
        idArtista = idArtista.toUpperCase();
        Evento ev;
        Document query = new Document("artistas", idArtista), aux;
        MongoCursor<Document> cur;
        try {
            FindIterable<Document> resul = col.find(query);
            cur = resul.cursor();
            while (cur.hasNext()) {
                aux = cur.next();
                ev = new Evento(aux);
                asignaTecnicoYArtistas(ev, aux);
                lista.add(ev);
            }
        } catch (MongoException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
        return lista;

    }

    /**
     * Busca un evento por fecha, con el fin de comprobar su disponibilidad.
     *
     * @param fecha Fecha a comprobar.
     * @return Evento que tendrá lugar en esa fecha. Null si no existe.
     */
    public Evento consultarPorFecha(LocalDate fecha) {
        Document query = new Document("fecha", fecha);
        Evento ev;
        Document aux;
        try {
            FindIterable<Document> resul = col.find(query);
            aux = resul.first();
            ev = new Evento(aux);
            asignaTecnicoYArtistas(ev, aux);
        } catch (MongoException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
        return ev;
    }

    /**
     * Busca eventos en un período concreto, en los que participe un artista
     * concreto.
     *
     * @param desde Fecha inicial del período.
     * @param hasta Fecha final del período
     * @param artista Artista a buscar en los eventos.
     * @return Lista con los eventos que tienen lugar entre las fechas
     * indicadas, en los que participa el artista.
     */
    public ArrayList<Evento> consultaPorArtistaEntreDosFechas(LocalDate desde, LocalDate hasta, String artista) {
        ArrayList<Evento> lista = new ArrayList();
        Evento ev;
        LocalDate auxFecha;
        FindIterable<Document> resul;
        Document docFecha, docBetween, docEvento, docArtista, query;
        MongoCursor<Document> cur;

        // Si la fecha inicial es mayor que la final, se cambian.
        if (hasta.compareTo(desde) == -1) {
            auxFecha = desde;
            desde = hasta;
            hasta = auxFecha;
        }

        /* Utilizamos "$gte" para indicar que la fecha tiene que ser mayor o igual que la indicada.
         * Con "$lte" indicamos que la fecha tiene que ser menor o igual.
         * De esta forma, conseguimos lo que en SQL sería un "between".
         */
        docBetween = new Document("$gte", desde);
        docBetween.append("$lte", hasta);
        docFecha = new Document("fecha", docBetween);
        docArtista = new Document("artistas", artista);

        /* Utilizamos "$and" para indicar que deben cumplirse las condiciones indicadas.
         * Es decir: Los resultados tienen que tener lugar entre las dos fechas indicadas.
         * Además, tiene que participar el artista.
         */
        query = new Document("$and", Arrays.asList(docFecha, docArtista));

        try {
            resul = col.find(query);
            cur = resul.cursor();
            while (cur.hasNext()) {
                docEvento = cur.next();
                ev = new Evento(docEvento);
                asignaTecnicoYArtistas(ev, docEvento);
                lista.add(ev);
            }
        } catch (MongoException | NullPointerException e) {
            return null;
        }
        return lista;
    }

    /**
     * Asigna objetos de tipo Tecnico, Rider y Artista, todos con sus atributos,
     * al evento en cuestión
     *
     * @param ev Evento al que se van a asginar dichos objetos.
     * @param doc Documento del que se etraerán los datos..
     */
    public void asignaTecnicoYArtistas(Evento ev, Document doc) {
        String idTecnico = doc.getString("tecnico");
        Tecnico t = idTecnico.matches("^T\\d+") ? dTec.consultarPorId(idTecnico) : new Tecnico(idTecnico);
        Artista a;
        Object valorArtistas = doc.get("artistas");
        ArrayList<String> idArtistas;
        ArrayList<Artista> artistas = new ArrayList();
        
        if (valorArtistas instanceof ArrayList)
            idArtistas = (ArrayList<String>) doc.get("artistas");
        else {
            idArtistas = new ArrayList();
            idArtistas.add("T0");
        }

        for (String idArtista : idArtistas) {
            if (idArtista.matches("^A\\d+"))
                a = dArt.consultarPorId(idArtista);
            else {
                a = new Artista(idArtista);
            }
            
            artistas.add(a);
        }
        ev.setTecnico(t);
        ev.setArtistas(artistas);
    }

    /**
     * Calcula el número del id del del evento que se va a añadir a la
     * colección. Para ello, hace una consulta de todos los eventos, obteniendo
     * el identificador del último de ellos. Obtiene el número y le suma 1.
     *
     * @return El número correspondiente al nuevo registro.
     */
    public Integer calculaId() {
        FindIterable resul = col.find();
        ArrayList<Document> listaResul = new ArrayList();
        Document doc;
        String cad;
        Integer n;

        resul.into(listaResul);

        if (listaResul.isEmpty()) {
            return 1;
        }
        doc = listaResul.get(listaResul.size() - 1);
        cad = doc.getString("_id");
        n = Integer.parseInt(cad.substring(1));

        return n + 1;
    }

    /**
     * Recupera los datos de todos los documentos de la colección "eventos".
     */
    @Override
    public ArrayList<Evento> consultarTodos(boolean ordenar) {
        Evento ev;
        Document aux;
        ArrayList<Evento> lista = new ArrayList();
        FindIterable resul = col.find().sort(ordenar ? new Document("fecha", 1) : new Document("_id", 1));
        MongoCursor<Document> cursor = resul.cursor();
        // Recorremos el cursor, hacemos la conversión y añadimos el POJO a la lista.
        while (cursor.hasNext()) {
            aux = cursor.next();
            ev = new Evento(aux);
            asignaTecnicoYArtistas(ev, aux);
            lista.add(ev);
        }
        return lista;
    }    

    /**
     * Recupera el objeto DAOTecnico asignado al evento.
     *
     * @return DAOTecnico asignado al evento.
     */
    public DAOTecnico getdTec() {
        return dTec;
    }

    /**
     * Recupera el objeto DAOArtista asignado al evento.
     *
     * @return DAOArtista asignado al evento.
     */
    public DAOArtista getdArt() {
        return dArt;
    }

}
