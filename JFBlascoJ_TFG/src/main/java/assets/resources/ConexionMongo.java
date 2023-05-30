package assets.resources;

import com.mongodb.*;

/**
 * Clase para obtener un cliente de MongoDB
 * @author José Francisco Blasco Jiménez
 */
public class ConexionMongo {
    
    /**
     * Crea un nuevo cliente MongoDB para realizar la conexión
     * @return Cliente MongoDB
     */
    public static MongoClient getConexion() {
        String host, puerto, user, pass, uri;
        // CASA
        host = "192.168.144.141";
        // CLASE
//        host = "192.168.66.192";
        puerto = "27017";
        user = "admin";
        pass = "docker";
        /* Concatenamos los datos de conexión en una cadena.
         * Instanciamos un MongoClientURI a partir de esa cadena.
         * Creamos la conexión con dicha URI.
         */
        uri = "mongodb://" + user + ":" + pass + "@" + host + ":" + puerto;
        MongoClientURI mongoUri = new MongoClientURI(uri);
            MongoClient cliente = null;
//        try {
            cliente = new MongoClient(mongoUri);
            
//        } catch (MongoSecurityException ex) {
//            System.out.println("Mongo Security");
//        } catch (MongoCommandException ex) {
//            System.out.println("COMMAND");
//        } catch (MongoSocketOpenException ex) {
//            System.out.println("SOCKET OPEN");
//        } catch (MongoTimeoutException ex) {
//            System.out.println("MONGO TIMEOUT");
//        } catch (MongoException ex) {
//            System.out.println("MONGO EX");
//        } catch (Exception ex) {
//            System.out.println("EXCEPTION");
//        }
        
        return cliente;
    }    
}
