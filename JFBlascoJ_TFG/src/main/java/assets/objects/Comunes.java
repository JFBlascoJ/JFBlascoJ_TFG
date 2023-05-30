package assets.objects;

import assets.resources.ConexionMongo;
import DAO.*;
import GUI.*;
import com.mongodb.MongoClient;

/**
 * Clase que contiene objetos comunes utilizados en toda la aplicación.
 *
 * @author José Francisco Blasco Jiménez
 */
public class Comunes {
    private final DAOArtista DAOART;
    private final DAOEvento DAOEV;
    private final DAOTecnico DAOTEC;
    private final Modal MODAL;
    private final Listeners listeners;
    private final Interfaz PARENT;
    private Boolean avisoRider;
    private final MongoClient cliente;

    // Tipos para los componentes que pueden trabajar con diferentes entidades.
    public static final int TIPO_EVENTO = 0;
    public static final int TIPO_ARTISTA = 1;
    public static final int TIPO_TECNICO = 2;

    /**
     * Constructor de la clase Comunes.
     * Inicializa todos los objetos comunes.
     * @param parent Interfaz principal de la aplicación.
     */
    public Comunes(Interfaz parent) {
        PARENT = parent;
        cliente = ConexionMongo.getConexion();
        MODAL = new Modal(99, "", PARENT, true);
        if (cliente == null) {
            MODAL.setTipo(Modal.MODAL_ERROR);
            MODAL.setText("<html>No se ha podido conectar.<br>"
                    + "¿La IP es correcta?<br>"
                    + "¿Has introducido bien la contraseña?"
                    + "¿El servicio está activo en el servidor?");
            MODAL.setOpciones(false);
            MODAL.showDialog();
        }
        DAOEV = new DAOEvento(cliente);
        DAOART = new DAOArtista(cliente, DAOEV);
        DAOTEC = new DAOTecnico(cliente, DAOEV);
        listeners = new Listeners(this);
        avisoRider = true;
    }

    /**
     * Obtiene el DAOArtista utilizado en la aplicación.
     *
     * @return El DAOArtista utilizado.
     */
    public DAOArtista getDAOArt() {
        return DAOART;
    }

    /**
     * Obtiene el DAOEvento utilizado en la aplicación.
     *
     * @return El DAOEvento utilizado.
     */
    public DAOEvento getDAOEv() {
        return DAOEV;
    }

    /**
     * Obtiene el DAOTecnico utilizado en la aplicación.
     *
     * @return El DAOTecnico utilizado.
     */
    public DAOTecnico getDAOTec() {
        return DAOTEC;
    }

    /**
     * Obtiene el DAOArtista utilizado en la aplicación.
     *
     * @return El DAOArtista utilizado.
     */
    public DAOArtista getDAOART() {
        return DAOART;
    }

    /**
     * Obtiene el DAOEvento utilizado en la aplicación.
     *
     * @return El DAOEvento utilizado.
     */
    public DAOEvento getDAOEV() {
        return DAOEV;
    }

    /**
     * Obtiene el DAOTecnico utilizado en la aplicación.
     *
     * @return El DAOTecnico utilizado.
     */
    public DAOTecnico getDAOTEC() {
        return DAOTEC;
    }

    /**
     * Obtiene el objeto Modal utilizado en la aplicación.
     *
     * @return El objeto Modal utilizado.
     */
    public Modal getMODAL() {
        return MODAL;
    }

    /**
     * Obtiene el valor del aviso de Rider.
     *
     * @return El valor del aviso de Rider.
     */
    public Boolean getAvisoRider() {
        return avisoRider;
    }

    public void setAvisoRider(Boolean avisoRider) {
        this.avisoRider = avisoRider;
    }
    
    /**
     * Obtiene el objeto Listeners utilizado en la aplicación.
     *
     * @return El objeto Listeners utilizado.
     */
    public Listeners getListeners() {
        return listeners;
    }

    /**
     * Obtiene la instancia de la interfaz principal de la aplicación.
     *
     * @return La instancia de la interfaz principal.
     */
    public Interfaz getPARENT() {
        return PARENT;
    }
}
