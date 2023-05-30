package POJOs;

import components.organisms.tables.TblRider;
import components.atoms.txt.RoundTextArea;
import components.atoms.txt.RoundTextField;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import org.bson.Document;

public class Artista {

    private String id, nombre, mail, observaciones;
    private Integer tlf;
    private ArrayList<Evento> eventos;
    private ArrayList<Object[]> rider;

    public Artista(Integer id, String nombre, ArrayList<Evento> eventos, ArrayList<Object[]> rider) {
        Artista.this.setId(id);
        setNombre(nombre);
        setEventos(eventos);
        setRider(rider);
    }

    public Artista(String nombre) {
        this(null, nombre, new ArrayList(), new ArrayList());
    }
    
    public Artista(HashMap<String, Component> campos) {
        RoundTextField txt;
        RoundTextArea area;
        TblRider rider;
        for (String key : campos.keySet()) {
            switch (key) {
                case "txtNombre":
                    txt = (RoundTextField) campos.get(key);
                    setNombre(txt.getText());
                    break;
                case "txtTlf":
                    txt = (RoundTextField) campos.get(key);
                    setTlf(!txt.getText().equals("") ? Integer.parseInt(txt.getText()) : null);
                    break;
                case "txtMail":
                    txt = (RoundTextField) campos.get(key);
                    setMail(txt.getText());
                    break;
                case "txtObservaciones":
                    area = (RoundTextArea) campos.get(key);
                    setObservaciones(area.getText());
                    break;
                case "tblRider":
                    rider = (TblRider) campos.get(key);
                    setRider(rider.getRider());
                    break;
            }
        }
    }

    /**
     * Instanciar un artista a partir de un Document
     * @param doc Document a partir del que se quiere generar el artista.
     */
    public Artista(Document doc) {
        Object[] array;
        setRider(new ArrayList());
        ArrayList<Document> aux = (ArrayList) (doc.containsKey("rider") ? doc.get("rider") : (null));
        
        setId(doc.getString("_id"));
        setNombre(doc.getString("nombre"));
        setObservaciones(doc.containsKey("observaciones") ? doc.getString("observaciones") : (null));
        setMail(doc.containsKey("email") ? doc.getString("email") : (null));
        
        setTlf(doc.containsKey("tlf") ? doc.getInteger("tlf") : null );
        
        if (aux != null) {        
            for (Document o : aux) {
                array = new Object[3];
                    array[0] = o.getString("elemento");
                    array[1] = o.getInteger("cantidad");
                    array[2] = o.containsKey("suma") ? o.getBoolean("suma") : false;
                    getRider().add(array);
            }
        }
    }

    /**
     * Convierte el Artista a un objeto Document con todos sus atributos para añadirlo a la colección.
     * @param modificar Si es false, añade el identificador (para inserción).
     * @return Objeto de tipo Document con los datos del artista.
     */
    public Document toDocument(boolean modificar) {
        Document doc = new Document(), aux;
        ArrayList<Document> auxRider = new ArrayList();
        
        for (Object[] o : getRider()) {
            aux = new Document();
            aux.append("elemento", o[0]);
            aux.append("cantidad", o[1]);
            aux.append("suma", o[2]);
            auxRider.add(aux);
        }
        
        // Si se va a modificar el documento, no se añade el identificador
        if (!modificar)
            doc.append("_id", getId());
        
        doc.append("nombre", getNombre());
        if (getTlf() != null)
            doc.append("tlf", getTlf());
        if (getMail() != null)
            doc.append("email", getMail());
        if (getObservaciones()!= null)
            doc.append("observaciones", getMail());
        doc.append("rider", auxRider);

        return doc;
    }

    @Override
    public String toString() {
        return getNombre();
    }

    public String getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id != null)
            this.id = "A" + id;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getTlf() {
        return tlf;
    }

    public void setTlf(Integer tlf) {
        this.tlf = tlf;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    public ArrayList<Object[]> getRider() {
        return rider;
    }

    public void setRider(ArrayList<Object[]> rider) {
        this.rider = rider;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Artista other = (Artista) obj;
        if (other.getObservaciones() == null)
            other.setObservaciones("");
        if (other.getMail()== null)
            other.setMail("");
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        if (!Objects.equals(this.tlf, other.tlf)) {
            return false;
        }
        if (!Objects.equals(this.eventos, other.eventos)) {
            return false;
        }
        
        return compararRiders(other.rider);
    }

    private boolean compararRiders(ArrayList<Object[]> rider) {
        Object[] aux, auxOther;
        StringBuilder nombre;
        int cantidad;
        boolean suma;
        if (getRider().equals(rider))
            return true;
        if (getRider().size() != rider.size())
            return false;
        
        for (int i = 0; i < getRider().size(); i++) {
            aux = getRider().get(i);
            auxOther = rider.get(i);
            nombre = new StringBuilder((String) aux[0]);
            cantidad = (Integer) aux[1];
            suma = (Boolean) aux[2];
            
            if (!nombre.toString().equals((String) auxOther[0]) || cantidad != (int) auxOther[1] || suma != (boolean) auxOther[2])
                return false;            
        }
        
        return true;
    }
    
}
