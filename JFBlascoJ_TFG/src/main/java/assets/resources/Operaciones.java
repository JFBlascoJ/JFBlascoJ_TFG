package assets.resources;

import GUI.Modal;
import POJOs.Artista;
import POJOs.Evento;
import POJOs.Tecnico;
import components.atoms.txt.RoundTextArea;
import components.atoms.txt.RoundTextField;
import components.artistchooser.ArtistChooser;
import components.atoms.combo.CustomComboBox;
import components.organisms.tables.TblListado;
import components.organisms.tables.TblRider;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Clase para operaciones comunes entre diferentes vistas.
 *
 * @author José Francisco Blasco Jiménez.
 */
public class Operaciones {

    /**
     * Convierte una cadena a fecha.
     *
     * @param cadFecha Cadena a convertir.
     * @return Fecha con los datos de la cadena pasada por parámetro.
     */
    public static LocalDate deCadenaAFecha(String cadFecha) {
        String[] valores;

        if (cadFecha.contains("-")) {
            valores = cadFecha.split("-");
        } else {
            valores = cadFecha.split("/");
        }

        if (Integer.parseInt(valores[0]) > 2000) {
            return LocalDate.parse(valores[0] + "-" + valores[1] + "-" + valores[2]);
        } else {
            return LocalDate.parse(valores[2] + "-" + valores[1] + "-" + valores[0]);
        }
    }

    /**
     * Convierte la fecha pasada por parámetro en una cadena.
     *
     * @param fecha Fecha a convertir.
     * @param listar Indica si la fecha es para un listado o para asignarla a un
     * evento
     * @return Cadena con formato "dd/MM/yyyy" si listar es true. En caso
     * contrario, "yyyy/MM/dd"
     */
    public static String deFechaACadena(Date fecha, boolean listar) {
        LocalDate f = new java.sql.Date(fecha.getTime()).toLocalDate();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (listar) {
            return f.format(formato);
        } else {
            return f.toString();
        }
    }

    /**
     * Convierte la fecha pasada por parámetro en una cadena.
     *
     * @param fecha Fecha a convertir.
     * @param listar Indica si la fecha es para un listado o para asignarla a un
     * evento
     * @return Cadena con formato "dd/MM/yyyy" si listar es true. En caso
     * contrario, "yyyy/MM/dd"
     */
    public static String deFechaACadena(LocalDate fecha, boolean listar) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (listar) {
            return fecha.format(formato);
        } else {
            String f = fecha.toString();
            return f;
        }
    }

    /**
     * Limpia/resetea los componentes pasados por parámetro.
     *
     * @param campos Campos a limpiar/resetear.
     */
    public static void limpiar(HashMap<String, Component> campos) {
        Component comp;

        // Convierte el componente a la instancia correspondiente y lo resetea
        for (String key : campos.keySet()) {
            comp = campos.get(key);
            if (comp instanceof RoundTextField) {
                RoundTextField aux = (RoundTextField) comp;
                aux.resetear();
            } else if (comp instanceof RoundTextArea) {
                RoundTextArea aux = (RoundTextArea) comp;
                aux.resetear();
            } else if (comp instanceof CustomComboBox) {
                CustomComboBox aux = (CustomComboBox) comp;
                aux.resetear();
            } else if (comp instanceof ArtistChooser) {
                ArtistChooser aux = (ArtistChooser) comp;
                aux.resetear();
            } else if (comp instanceof TblRider) {
                TblRider aux = (TblRider) comp;
                aux.resetear();
            }
        }
    }

    /**
     * Actualiza los datos en los componentes de la aplciación cada vez que se
     * lleva a cabo una operación CRUD.
     *
     * @param lista Lista de componentes a actualizar
     */
    public static void actualizaDatos(ArrayList<Component> lista) {
        for (Component comp : lista) {
            TblListado tabla;
            CustomComboBox combo;
            ArtistChooser chooser;

            // Actualiza los componentes dependiendo del tipo.
            if (comp instanceof TblListado) {
                tabla = (TblListado) comp;
                tabla.setTipo(tabla.getTipo());
            } else if (comp instanceof CustomComboBox) {
                combo = (CustomComboBox) comp;
                combo.setListarCero(combo.isListarCero());
            } else if (comp instanceof ArtistChooser) {
                chooser = (ArtistChooser) comp;
                chooser.resetear();
            }
        }
    }

    /**
     * Comprueba que, al dar de alta o modificar un nuevo evento, los campos
     * requeridos no estén vacíos.
     *
     * @param campos Campos a comprobar.
     * @param modal Ventana modal para mostrar mensaje en caso de error.
     * @return Evento con los datos si está todo correcto, null en caso
     * contrario.
     */
    public static Evento compruebaCamposEvento(HashMap<String, Component> campos, Modal modal) {
        RoundTextField txtNombre = (RoundTextField) campos.get("txtNombre");
        RoundTextField txtFecha = (RoundTextField) campos.get("txtFecha");
        RoundTextArea txtObsArtistas = (RoundTextArea) campos.get("txtObsArtistas"),
                txtObsEvento = (RoundTextArea) campos.get("txtObsEvento");
        ArtistChooser chooser = (ArtistChooser) campos.get("chooser");
        CustomComboBox combo = (CustomComboBox) campos.get("cmbTecnico");
        TblRider tabla = (TblRider) campos.get("tblRider");
        Evento e;
        Tecnico t = (Tecnico) combo.getSelected();
        ArrayList<Artista> artistas = chooser.getArtistas();
        String cadFecha = txtFecha.getText();
        boolean flagTecnicoArtistas, flagPalante = true, flagFechaAnterior;
        StringBuilder errorMsg = new StringBuilder("<html>");
        LocalDate fecha = cadFecha.equals("") ? null : Operaciones.deCadenaAFecha(txtFecha.getText());

        // Se comprueba el campo de nombre.
        if (txtNombre.getText().equals("")) {
            txtNombre.setCorrecto(false);
            // Si está vacío, se pone la bandera en false y se añade el mensaje pertinente al StringBuilder.
            flagPalante = false;
            errorMsg.append("El campo 'nombre' no puede estar vacío.");
        }

        /* Se comprueba que el campo de fecha no esté vacío
         * Si lo está, se marca el campo como no correcto, se setea la bandera a false y se añade el mensaje al StringBuilder.
         */
        if (fecha == null) {
            txtFecha.setCorrecto(false);
            flagPalante = false;
            // Si ya se ha añadido texto al mensaje, se añade un salto de línea.
            if (!errorMsg.toString().equals("<html>")) {
                errorMsg.append("<br>");
            }
            errorMsg.append("El campo 'fecha' no puede estar vacío.");
            /* Si no es null, se comprueba que no sea anterior a la actual.
             * Si es null, se añade el mensaje al StringBuilder y la bandera se setea a false.
             */
        } else {
            if ((flagFechaAnterior = Operaciones.deCadenaAFecha(txtFecha.getText()).isBefore(LocalDate.now()))) {
                // Si ya se ha añadido texto al mensaje, se añade un salto de línea.
                if (!errorMsg.toString().equals("<html>")) {
                    errorMsg.append("<br>");
                }
                errorMsg.append("La fecha no puede ser anterior al día de hoy.");
                flagPalante = false;
            }
        }

        // Si hay algún error, se cierra el mensaje y se añade al modal.
        if (!flagPalante) {
            errorMsg.append("</html>");
            modal.setText(errorMsg.toString());
            return null;
        }

        // Se comprueba si se han añadido artistas o se ha asignado un técnico de Sonido.
        flagTecnicoArtistas = compruebaTecnicoYArtistas(t, artistas, modal);

        // Si la bandera de técnico y artistas es false, se devuelve null.
        if (!flagTecnicoArtistas) {
            return null;
        }

        // Si no, se devuelve un evento con los datos de los campos.
        e = new Evento();
        e.setNombre(txtNombre.getText());
        e.setFecha(txtFecha.getText());
        e.setArtistas(artistas);
        e.setTecnico(t);
        e.setObsArtistas(txtObsArtistas.getText());
        e.setObsEvento(txtObsEvento.getText());
        e.calculaRider();

        return e;
    }

    /**
     * Se comprueba si se han añadido artistas o asignado un técnico de sonido
     * al evento. Si falta uno de los dos, o los dos, el usuario tiene 2
     * opciones: - Registrar el evento igualmente. - Volver atrás y asignarlos.
     *
     * @param t Técnico a comprobar
     * @param artistas Lista de artistas a comprobar.
     * @param modal Ventana modal para mostrar el mensaje, en caso de ser
     * necesario.
     * @return True Si el técnico no es null y la lista de artistas no está
     * vacía. False en caso contrario.
     */
    private static boolean compruebaTecnicoYArtistas(Tecnico t, ArrayList<Artista> artistas, Modal modal) {
        String msg;

        // Si el técnico es null o la lista de artistas es null, se muestra la ventana modal para decir qué se quiere hacer.
        if (t == null || artistas == null) {
            msg = "<html>No has añadido técnico o artistas<br>"
                    + "¿Quieres continuar?</html>";
            modal.setTipo(Modal.MODAL_INFO);
            modal.setText(msg);
            modal.setOpciones(true);
            // Se asignan técnico y artista al modal para que opere con ellos según la opción elegida por el usuario.
            modal.setTecnico(t);
            modal.setArtistas(artistas);
            modal.showDialog();
        }

        /* 
         * Se comprueban los artistas y el técnico después de elegir la opción en el modal. 
         * Si el técnico no es null y la lista de artistas no está vacía, devuelve true. Si no, false.
         */
        if (t != null && !artistas.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Comprueba que los campos requeridos no estén vacíos a la hora de
     * registrar o modificar un artista.
     *
     * @param campos Campos a comprobar.
     * @param modal Ventana modal para mostrar el mensaje, en caso de ser
     * necesario.
     * @return Artista con los datos del formulario si es correcto, null en caso
     * contrario.
     */
    public static Artista compruebaCamposArtista(HashMap<String, Component> campos, Modal modal) {
        RoundTextField txtNombre = (RoundTextField) campos.get("txtNombre"),
                txtTlf = (RoundTextField) campos.get("txtTlf"),
                txtMail = (RoundTextField) campos.get("txtMail");
        RoundTextArea txtObservaciones = (RoundTextArea) campos.get("txtObservaciones");
        Artista a;
        TblRider tabla = (TblRider) campos.get("tblRider");
        ArrayList<Object[]> rider;
        boolean flagContacto = true, flagRider = true, flagNombre = true;
        StringBuilder errorMsg = new StringBuilder("<html>");

        rider = tabla.getRider();

        // Verifica que el nombre no sea una cadena vacía.
        if (txtNombre.getText().equals("")) {
            // De ser así, pone la bandera del nombre en false y añade el mensaje al StringBuilder.
            flagNombre = false;
            errorMsg.append("El campo 'nombre' es obligatorio.");
        }

        // Comprueba que el rider tenga elementos.
        if (rider == null) {
            // Si está vacío, se pone la bandera en falso y se añade el mensaje.
            flagRider = false;
            // Si ya se ha añadido texto al mensaje, se añade un salto de línea.
            if (!errorMsg.toString().equals("<html>")) {
                errorMsg.append("<br>");
            }
            errorMsg.append((errorMsg.toString().equals("<html>") ? "" : "<br>")
                    + "El rider no puede estar vacío.");
        }

        // Confirma que hay al menos una forma de contactar con el artista.
        if (txtTlf.getText().equals("") && txtMail.getText().equals("")) {
            // Si ambos campos están vacíos, se pone la bandera en falso y se añade el mensaje.
            flagContacto = false;
            // Si ya se ha añadido texto al mensaje, se añade un salto de línea.
            if (!errorMsg.toString().equals("<html>")) {
                errorMsg.append("<br>");
            }
            errorMsg.append((errorMsg.toString().equals("<html>") ? "" : "<br>")
                    + "Tiene que haber al menos una forma de contactar con el artista.");
        }

        // Se cierra la etiqueta HTML
        errorMsg.append("</html>");

        // Si está todo correcto, se devuelve un artista con los datos asignados.
        if (flagContacto && flagRider && flagNombre) {
            a = new Artista(txtNombre.getText());
            a.setMail(!txtMail.getText().equals("") ? txtTlf.getText() : null);
            a.setTlf(!txtTlf.getText().equals("") ? Integer.parseInt(txtTlf.getText()) : null);
            a.setObservaciones(!txtObservaciones.getText().equals("") ? txtObservaciones.getText() : null);
            a.setRider(rider);
            return a;
            // Si no, se muestra el mensaje de error y se devuelve null.
        } else {
            modal.setTipo(Modal.MODAL_WARNING);
            modal.setText(errorMsg.toString());
            return null;
        }
    }

    /**
     * Se asegura, al guardar un nuevo técnico, de que los datos son correctos.
     *
     * @param campos Campos a verificar.
     * @param modal Ventana modal para mostrar el mensaje en caso de error.
     * @return
     */
    public static Tecnico compruebaCamposTecnico(HashMap<String, Component> campos, Modal modal) {
        Tecnico t;
        RoundTextField txtNombre = (RoundTextField) campos.get("txtNombre"),
                txtTlf = (RoundTextField) campos.get("txtTlf"),
                txtMail = (RoundTextField) campos.get("txtMail");
        boolean flagNombre = true, flagContacto;
        StringBuilder errorMsg = new StringBuilder("<html>");
        flagContacto = !txtMail.getText().equals("") || !txtTlf.getText().equals("");

        // Comprueba que el nombre no sea una cadena vacía.
        if (txtNombre.getText().equals("")) {
            // En ese caso, se pone la bandera correspondiente en false y se añade el mensaje al StringBuilder.
            flagNombre = false;
            errorMsg.append("El campo 'nombre' es obligatorio.");
        }

        // Se comprueba que haya al menos una forma de contactar con el técnico.
        if (!flagContacto) {
            /* De no ser aí, se añade el mensaje al StringBuilder.
             * Si ya se ha añadido texto al mensaje de error, se añade un salto de línea.
             */
            if (!errorMsg.toString().equals("<html>")) {
                errorMsg.append("<br>");
            }
            errorMsg.append((errorMsg.toString().equals("<html>") ? "" : "<br>")
                    + "Tiene que haber al menos una forma de contactar con el técnico.");
        }
        
        // Cierre de la etiqueta HTML.
        errorMsg.append("</html>");

        // Se está todo en orden, se devuelve un técnico con todos los datos.
        if (flagContacto && flagNombre) {
            t = new Tecnico(txtNombre.getText());
            t.setMail(!txtMail.getText().equals("") ? txtMail.getText() : null);
            t.setTlf(!txtTlf.getText().equals("") ? Integer.parseInt(txtTlf.getText()) : null);

            return t;
        // Si no, se muestra el mensaje de error y se devuelve null.
        } else {
            modal.setTipo(Modal.MODAL_WARNING);
            modal.setText(errorMsg.toString());
            return null;
        }
    }
}
