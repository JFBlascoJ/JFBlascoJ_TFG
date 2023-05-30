package POJOs;

import components.atoms.txt.RoundTextField;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import org.bson.Document;

public class Tecnico {
    private String id, nombre, mail;
    Integer tlf;
    private ArrayList<Evento> eventos;

    public Tecnico(Integer id, String nombre, ArrayList<Evento> eventos) {
        Tecnico.this.setId(id);
        setNombre(nombre);
        setEventos(eventos);
    }

    public Tecnico(String nombre) {
        this(null, nombre, new ArrayList());
    }
    
    public Tecnico(HashMap<String, Component> campos) {
        RoundTextField txt;
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
            }
        }
    }
    
    /**
     * Instancia un técnico a partir de un Document con los datos.
     * @param doc Documento a partir del que se quiere crear el técnico.
     */
    public Tecnico(Document doc) {
        setId(doc.getString("_id"));
        setNombre(doc.getString("nombre"));
        setTlf(doc.containsKey("tlf") ? doc.getInteger("tlf") : null);
        setMail(doc.containsKey("email") ? doc.getString("email") : null);
    }
    
     /**
     * Convierte el técnico a un objeto Document con todos sus atributos para añadirlo a la colección.
     * @param modificar Si es false, añade el identificador (para inserción).
     * @return Objeto de tipo Document con los datos del técnico.
     */
    public Document toDocument(boolean modificar) {
        Document doc = new Document();
        
        // Si se va a modificar el documento, no se añade el identificador.
        if (!modificar)
            doc.append("_id", getId());
        doc.append("nombre", getNombre());
        if (getTlf() != null)
            doc.append("tlf", getTlf());
        if (getMail() != null)
            doc.append("email", getMail());
        return doc;
    }

    @Override
    public String toString() {
        return getNombre();
    }
    
    /**
     * Método toString para listado.
     * @return Cadena con los datos del técnico.
     */
    public String toStringList() {
        String tab = "   ";
        StringBuilder sb = new StringBuilder();
        sb.append(getId() + " - " + getNombre() + "\n");
        sb.append(tab + "Eventos:\n");
        for (Evento e : getEventos()) {
            sb.append(tab + tab + "- " + e.getNombre() + "\n");
        }
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id != null)
            this.id = "T" + id;
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
   
    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Tecnico other = (Tecnico) obj;
        if (other.getMail() == null)
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
        if (!Objects.equals(this.tlf, other.tlf)) {
            return false;
        }
        return Objects.equals(this.eventos, other.eventos);
    }
    
}
