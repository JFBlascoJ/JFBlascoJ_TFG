package POJOs;

import components.artistchooser.ArtistChooser;
import components.atoms.combo.CustomComboBox;
import components.organisms.tables.TblRider;
import components.atoms.txt.RoundTextArea;
import components.atoms.txt.RoundTextField;
import java.awt.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.bson.Document;
import assets.resources.Operaciones;
import org.bson.BsonArray;
import org.bson.BsonString;
import org.bson.BsonValue;

public class Evento {

    private String id, obsArtistas, obsEvento;
    private String nombre;
    private LocalDate fecha;
    private Tecnico tecnico;
    private ArrayList<Artista> artistas;
    private HashMap<String, Integer> rider;

    public Evento(Integer id, String nombre, String fecha, Tecnico tecnico,
            ArrayList<Artista> artistas, HashMap<String, Integer> rider) {
        setId(id);
        setNombre(nombre);
        setFecha(fecha);
        setTecnico(tecnico);
        setArtistas(artistas);
        setRider(rider);
    }

    public Evento(Integer id, String nombre, String fecha) {
        this(id, nombre, fecha, null, new ArrayList(), new HashMap());
    }

    public Evento(String nombre, String fecha) {
        this(null, nombre, fecha, null, new ArrayList(), new HashMap());
    }

    public Evento() {
        this(null, null);
    }

    public Evento(HashMap<String, Component> campos) {
        RoundTextField txt;
        RoundTextArea area;
        ArtistChooser chooser;
        CustomComboBox combo;
        TblRider tblRider;

        for (String key : campos.keySet()) {
            switch (key) {
                case "txtNombre":
                    txt = (RoundTextField) campos.get(key);
                    setNombre(txt.getText());
                    break;
                case "tecnico":
                    combo = (CustomComboBox) campos.get(key);
                    setTecnico((Tecnico) combo.getSelected());
                    break;
                case "artistas":
                    chooser = (ArtistChooser) campos.get(key);
                    setArtistas(chooser.getArtistas());
                    break;
                case "tblRider":
                    tblRider = (TblRider) campos.get(key);
                    calculaRider(tblRider.getRider());
                    break;
                case "txtObsArtistas":
                    area = (RoundTextArea) campos.get(key);
                    setObsArtistas(area.getText());
                    break;
                case "txtObsEvento":
                    area = (RoundTextArea) campos.get(key);
                    setObsEvento(area.getText());
                    break;
            }
        }

    }

    /**
     * Instancia un evento a partir de un Document con todos sus atributos para
     * añadirlo a la colección.
     *
     * @param doc Documento a partir del que se quiere crear el evento.
     */
    public Evento(Document doc) {
        setRider(new HashMap());
        ArrayList<Document> listaRider = (ArrayList) doc.get("rider");
        Object valorArtistas = doc.get("artistas");
        
        setId(doc.getString("_id"));
        setNombre(doc.getString("nombre"));
        setFecha(Operaciones.deFechaACadena(doc.getDate("fecha"), false));

        if (valorArtistas instanceof ArrayList)
            setArtistas((ArrayList<Artista>) doc.get("artistas"));
        else
            setArtistas(new ArrayList());
        
        if (doc.containsKey("obsEvento")) {
            setObsEvento(doc.getString("obsEvento"));
        }

        for (Document o : listaRider) {
            getRider().put(o.getString("elemento"), o.getInteger("cantidad"));
        }
    }

    /**
     * Calcula el rider total del evento a partir de los riders individuales de
     * los artistas.
     */
    public void calculaRider() {
        ArrayList<Object[]> riderArtista;
        StringBuilder elem;
        int cantidad;
        boolean suma;
        HashMap<String, Integer> rider = new HashMap();

        if (getArtistas().isEmpty()) {
            rider = new HashMap();
        } else {
            for (Artista artista : artistas) {
                riderArtista = artista.getRider();

                for (Object[] o : riderArtista) {
                    elem = new StringBuilder(o[0].toString());
                    cantidad = Integer.parseInt(o[1].toString());
                    if (o[2] != null) {
                        suma = (boolean) o[2];
                    } else {
                        suma = false;
                    }

                    /* Recorre los riders individuales de los artistas.
                     * Si no existe ese elemento en el rider total, lo añade junto con la cantidad.
                     * Si el elemento ya existe, pueden pasar dos cosas:
                     *  - Si la cantidad del rider individual es menor, no pasa nada.
                     *  - Si es mayor, cambia la cantidad por la del rider individual.
                     */
                    if (rider.containsKey(elem.toString())) {
                        if (suma) {
                            cantidad += rider.get(elem.toString());
                            rider.replace(elem.toString(), cantidad);
                        } else {
                            if (rider.get(elem.toString()) < cantidad) {
                                rider.replace(elem.toString(), cantidad);
                            }
                        }
                    } else {
                        rider.put(elem.toString(), cantidad);
                    }
                }
            }
        }
        setRider(rider);
    }

    private void calculaRider(ArrayList<Object[]> lista) {
        HashMap<String, Integer> rider = new HashMap();
        StringBuilder elem;
        int cantidad;
        boolean suma;

        for (Object[] o : lista) {
            elem = new StringBuilder(o[0].toString());
            cantidad = Integer.parseInt(o[1].toString());
            if (o[2] != null) {
                suma = (boolean) o[2];
            } else {
                suma = false;
            }

            /* Recorre los riders individuales de los artistas.
             * Si no existe ese elemento en el rider total, lo añade junto con la cantidad.
             * Si el elemento ya existe, pueden pasar dos cosas:
             *  - Si la cantidad del rider individual es menor, no pasa nada.
             *  - Si es mayor, cambia la cantidad por la del rider individual.
             */
            if (rider.containsKey(elem.toString())) {
                if (suma) {
                    cantidad += rider.get(elem.toString());
                    rider.replace(elem.toString(), cantidad);
                } else {
                    if (rider.get(elem.toString()) < cantidad) {
                        rider.replace(elem.toString(), cantidad);
                    }
                }
            } else {
                rider.put(elem.toString(), cantidad);
            }
        }
    }

    /**
     * Convierte el Evento a un objeto Document con todos sus atributos.
     *
     * @param modificar Si es false, añade el identificador (para inserción).
     * @return Objeto de tipo Document con los datos del artista.
     */
    public Document toDocument(boolean modificar) {
        Document aux = new Document(), elemRider;
        ArrayList<String> idArtistas = new ArrayList();
        ArrayList<Document> listaRider = new ArrayList();

        for (Artista a : getArtistas()) {
            idArtistas.add(a.getId());
        }

        // Si se va a modificar el documento, no se añade el identificador al documento
        if (!modificar) {
            aux.append("_id", getId());
        }
        aux.append("nombre", getNombre());
        aux.append("fecha", getFecha());
        aux.append("tecnico", getTecnico().getId());
        aux.append("artistas", idArtistas);
        if (getObsArtistas() != null) {
            aux.append("obsArtistas", getObsArtistas());
        }
        if (getObsEvento() != null) {
            aux.append("obsEvento", getObsEvento());
        }

        for (String elem : getRider().keySet()) {
            elemRider = new Document();
            elemRider.append("elemento", elem);
            elemRider.append("cantidad", getRider().get(elem));
            listaRider.add(elemRider);
        }
        aux.append("rider", listaRider);

        return aux;
    }

    public String getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id != null) {
            this.id = "E" + id;
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        DateTimeFormatter formato;
        if (fecha != null) {
            if (fecha.contains("/")) {
                formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            } else {
                formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            }

            this.fecha = LocalDate.parse(fecha, formato);
        }
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public ArrayList<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(ArrayList<Artista> artistas) {
        this.artistas = artistas;
    }

    public HashMap<String, Integer> getRider() {
        return rider;
    }

    public void setRider(HashMap<String, Integer> rider) {
        this.rider = rider;
    }

    public String getObsArtistas() {
        return obsArtistas;
    }

    public void setObsArtistas(String obsArtistas) {
        this.obsArtistas = obsArtistas;
    }

    public String getObsEvento() {
        return obsEvento;
    }

    public void setObsEvento(String obsEventos) {
        this.obsEvento = obsEventos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evento other = (Evento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.obsArtistas, other.obsArtistas)) {
            return false;
        }
        if (!Objects.equals(this.obsEvento, other.obsEvento)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.tecnico, other.tecnico)) {
            return false;
        }
        if (!Objects.equals(this.artistas, other.artistas)) {
            return false;
        }
        if (!Objects.equals(this.rider, other.rider))
            return false;
        
        return true;
    }
}
