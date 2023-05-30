package components.organisms.keypads;

import DAO.DAOArtista;
import DAO.DAOEvento;
import DAO.DAOTecnico;
import components.atoms.txt.RoundTextArea;
import components.atoms.txt.RoundTextField;
import assets.objects.Comunes;
import assets.lookandfeel.Botones;
import assets.lookandfeel.Colores;
import components.artistchooser.ArtistChooser;
import components.atoms.combo.CustomComboBox;
import GUI.Container;
import GUI.Interfaz;
import GUI.Modal;
import POJOs.*;
import assets.lookandfeel.Fuentes;
import components.molecules.PnlRiderAdd;
import components.organisms.tables.TblRider;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import assets.resources.Operaciones;

/**
 * Botonera para las vistas de resultado.
 * @author José Francisco Blasco Jiménez
 */
public class KeyResultado extends javax.swing.JPanel {

    private Object actual, nuevo;
    private int tipo;
    private CardLayout card;
    private Container container;
    private boolean modificar, eliminar;
    private HashMap<String, Component> campos;
    private ArrayList<Component> camposOcultar;
    private Interfaz parent;
    private Modal modal;
    private DAOEvento dEv;
    private DAOArtista dArt;
    private DAOTecnico dTec;

    /**
     * Crea una nueva instancia.
     */
    public KeyResultado() {
        initComponents();
        campos = new HashMap();
        btnRestablecer.setVisible(false);
        btnModificar.setVisible(false);
        pnlModificar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVolver.setTipo(Botones.BTN_VOLVER);
        btnRestablecer.setTipo(Botones.BTN_RESTABLECER);
        btnModificar.setTipo(Botones.BTN_MODIFICAR);
        btnEliminar.setTipo(Botones.BTN_ELIM_BLANCO);
    }

    /**
     * Setea los objetos comunes
     * @param comunes Objeto de tipo Comunes a asignar.
     */
    public void setComunes(Comunes comunes) {
        parent = comunes.getPARENT();
        modal = comunes.getMODAL();
        dEv = comunes.getDAOEV();
        dArt = comunes.getDAOART();
        dTec = comunes.getDAOTEC();
    }

    /**
     * Habilita los campos para que se peuda editar su informacíon.
     */
    private void enableFields() {
        Component comp;

        for (String key : campos.keySet()) {
            comp = campos.get(key);
            if (comp instanceof RoundTextField) {
                RoundTextField aux = (RoundTextField) comp;
                aux.habilitar();

            } else if (comp instanceof RoundTextArea) {
                RoundTextArea aux = (RoundTextArea) comp;
                aux.habilitar();
            } else if (comp instanceof CustomComboBox) {
                CustomComboBox aux = (CustomComboBox) comp;
                aux.habilitar();
            } else if (comp instanceof ArtistChooser) {
                ArtistChooser txtLarge = (ArtistChooser) comp;
                txtLarge.habilitar();
            } else if (comp instanceof TblRider) {
                TblRider tabla = (TblRider) comp;
                tabla.habilitar();
            } else if (comp instanceof PnlRiderAdd) {
                PnlRiderAdd panel = (PnlRiderAdd) comp;
                enableFields();
            }

        }

    }

    /**
     * Deshabilita los campos para que no se puedan modificar.
     */
    private void disableFields() {
        Component comp;

        for (String key : campos.keySet()) {
            comp = campos.get(key);
            if (comp instanceof RoundTextField) {
                RoundTextField aux = (RoundTextField) comp;
                aux.deshabilitar();

            } else if (comp instanceof RoundTextArea) {
                RoundTextArea aux = (RoundTextArea) comp;
                aux.deshabilitar();
            } else if (comp instanceof CustomComboBox) {
                CustomComboBox aux = (CustomComboBox) comp;
                aux.deshabilitar();
            } else if (comp instanceof ArtistChooser) {
                ArtistChooser txtLarge = (ArtistChooser) comp;
                txtLarge.deshabilitar();
            } else if (comp instanceof TblRider) {
                TblRider tabla = (TblRider) comp;
                tabla.deshabilitar();
            }
        }
    }

    /**
     * Setea el tipo de entidad con la que se va a trabajar.
     * @param tipo Tipo de entidad con la que se va a trabajar.
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Setea el CardLayout para poder mostar las vistas a las que tiene acceso la botonera.
     * @param card CardLayout a asignar.
     */
    public void setCard(CardLayout card) {
        this.card = card;
    }

    /**
     * Comprueba si está activada la opción de modificar.
     * @return True si está habilitada. False en caso contrario.
     */
    public boolean isModificar() {
        return modificar;
    }

    /**
     * Habilita o deshabilita la función de modificar.
     * @param modificar True si se queire habilitar. False si se quiere desactivar.
     * Si se activa, los campos se habilitan, se esconde el botón de eliminar y se muestran los botones "Modificar" y "Restablecer".
     * Si se desactiva, se deshabilitan los campos, restableciendo su valor, y se muestra el botón "Eliminar", escondiendo "Restablecer" y "Modificar".
     */
    public void setModificar(boolean modificar) {
        this.modificar = modificar;

        if (isModificar()) {
            enableFields();
            btnEliminar.setVisible(false);
            btnRestablecer.setVisible(true);
            btnModificar.setVisible(true);
            if (camposOcultar != null) {
                for (Component aux : camposOcultar) {
                    aux.setVisible(true);
                }
            }
            lblModificar.setForeground(Colores.NARANJA);
        } else {
            disableFields();
            restablecer();
            btnEliminar.setVisible(true);
            btnRestablecer.setVisible(false);
            btnModificar.setVisible(false);
            for (Component aux : camposOcultar) {
                aux.setVisible(false);
            }
            lblModificar.setForeground(Colores.AZUL);
        }

        checkModificar.setSelected(isModificar(), checkModificar.isTabla());
    }

    /**
     * Setea los campos con los que va a trabajar la botonera.
     * @param campos Mapa a asignar, conteniendo los componentes.
     */
    public void setCampos(HashMap<String, Component> campos) {
        this.campos = campos;
    }

    /**
     * Setea los campos que hay que ocultar cuando la modificación está deshabilitada.
     * @param campos Campos a ocultar.
     */
    public void setCamposOcultar(ArrayList<Component> campos) {
        this.camposOcultar = campos;

        for (Component aux : camposOcultar) {
            aux.setVisible(false);
        }
    }

    /**
     * Setea la entidad cuyos datos se van a mostrar.
     * @param actual Entidad a asignar.
     */
    public void setActual(Object actual) {
        this.actual = actual;

        if (actual instanceof Evento) {
            tipo = Comunes.TIPO_EVENTO;
        } else if (actual instanceof Artista) {
            tipo = Comunes.TIPO_ARTISTA;
        } else if (actual instanceof Tecnico) {
            tipo = Comunes.TIPO_TECNICO;
        }

        restablecer();
        disableFields();
    }

    /**
     * Setea el contenedor en el que se encuentra la vista.
     * @param container Contenedor de la vista.
     */
    public void setContainer(Container container) {
        this.container = container;
    }

    /**
     * Muestra u oculta la opción de modificar.
     * Si el evento es anterior a la fecha actual, no se podrá modificar.
     * @param visible True si se puede modificar. False en caso contrario.
     */
    public void setModificarOption(boolean visible) {
        pnlModificar.setVisible(visible);
    }

    /**
     * Setea la ventana para tomar referencia a la hora de mostrar los mensajes modales.
     * @param parent Ventana en cuestión.
     */
    public void setParent(Interfaz parent) {
        this.parent = parent;
    }

    /**
     * Elimina la entidad.
     */
    private void eliminar() {
        String entidad = null;
        boolean flag = false;
        ArrayList<Component> lista = new ArrayList();
        switch (tipo) {
            case Comunes.TIPO_EVENTO:
                entidad = "evento";
                lista = parent.getCamposActualizarEventos();
                Evento e = (Evento) actual;
                flag = dEv.borrar(e.getId());

                break;
            case Comunes.TIPO_ARTISTA:
                entidad = "artista";
                lista = parent.getCamposActualizarArtistas();
                Artista a = (Artista) actual;
                flag = dArt.borrar(a.getId());
                break;
            case 2:
                entidad = "técnico";
                lista = parent.getCamposActualizarTecnicos();
                Tecnico t = (Tecnico) actual;
                flag = dTec.borrar(t.getId());
                break;
        }

        if (flag) {
            Operaciones.actualizaDatos(lista);
            modal.setTipo(Modal.MODAL_OK);
            modal.setText("El " + entidad + " ha sido eliminado.");
            volver();
        } else {
            modal.setTipo(Modal.MODAL_ERROR);
            modal.setText("El " + entidad + " no se ha podido eliminar.");
        }
        modal.setOpciones(false);
        modal.showDialog();
    }

    /**
     * Setea los valores de los campos con los datos de la entidad.
     */
    public void restablecer() {
        RoundTextArea area;
        RoundTextField txt;
        CustomComboBox combo;
        ArtistChooser chooser;
        TblRider rider;
        Component comp;
        Evento e;
        Artista a;
        Tecnico t;

        Operaciones.limpiar(campos);

        switch (tipo) {
            case Comunes.TIPO_EVENTO:
                Tecnico aux;
                e = (Evento) actual;
                String nombreTecnico = e.getTecnico().getNombre();
                txt = (RoundTextField) campos.get("txtNombre");
                txt.setText(e.getNombre());
                txt = (RoundTextField) campos.get("txtFecha");
                txt.setText(Operaciones.deFechaACadena(e.getFecha(), false));

                chooser = (ArtistChooser) campos.get("artistas");
                chooser.setArtistas(e.getArtistas());
                rider = (TblRider) campos.get("tblRider");
                rider.actualizaRider(e.getRider());
                area = (RoundTextArea) campos.get("txtObsArtistas");
                area.setText(e.getObsArtistas());
                area = (RoundTextArea) campos.get("txtObsEvento");
                area.setText(e.getObsEvento());
                combo = (CustomComboBox) campos.get("cmbTecnico");

                for (int i = 0; i < combo.getModel().getSize(); i++) {
                    aux = (Tecnico) combo.getModel().getElementAt(i);
                    if (aux.getNombre().equals(nombreTecnico)) {
                        combo.setSelectedIndex(i);
                        break;
                    }
                }

                break;
            case Comunes.TIPO_ARTISTA:
                a = (Artista) actual;
                txt = (RoundTextField) campos.get("txtNombre");
                txt.setText(a.getNombre());
                txt = (RoundTextField) campos.get("txtTlf");
                txt.setText(a.getTlf() != null ? a.getTlf().toString() : "");
                txt = (RoundTextField) campos.get("txtMail");
                txt.setText(a.getMail() != null ? a.getMail() : "");
                area = (RoundTextArea) campos.get("txtObservaciones");
                area.setText(a.getObservaciones() != null ? a.getObservaciones() : "");
                rider = (TblRider) campos.get("tblRider");
                rider.asignaRider(a.getRider());
                break;
            case 2:
                t = (Tecnico) actual;
                txt = (RoundTextField) campos.get("txtNombre");
                txt.setText(t.getNombre());
                txt = (RoundTextField) campos.get("txtTlf");
                txt.setText(t.getTlf() != null ? t.getTlf().toString() : "");
                txt = (RoundTextField) campos.get("txtMail");
                txt.setText(t.getMail() != null ? t.getMail() : "");
                break;
        }
    }

    /**
     * Modifica la entidad.
     */
    private void modificar() {
        String entidad = null;
        ArrayList<Component> lista = new ArrayList();
        StringBuilder msgError = new StringBuilder();
        boolean modificado = false, flagIguales = false;
        switch (tipo) {
            case Comunes.TIPO_EVENTO:
                entidad = "evento";
                lista = parent.getCamposActualizarEventos();
                Evento e = (Evento) actual,
                evNuevo = Operaciones.compruebaCamposEvento(campos, modal);
                
                if (evNuevo == null) {
                    modal.showDialog();
                    return;                    
                } else {
                    evNuevo.setId(e.getId());
                }
                if ((flagIguales = e.equals(evNuevo))) {
                    break;
                }

                modificado = dEv.modificar(e.getId(), evNuevo);
                break;

            case Comunes.TIPO_ARTISTA:
                entidad = "artista";
                lista = parent.getCamposActualizarArtistas();
                Artista a = (Artista) actual,
                 artNuevo = Operaciones.compruebaCamposArtista(campos, modal);
                
                if (artNuevo == null) {
                    modal.showDialog();
                    return;
                } else {
                    artNuevo.setId(a.getId());
                    artNuevo.setEventos(a.getEventos());
                }
                
                if (flagIguales = a.equals(artNuevo)) {
                    break;
                }

                modificado = dArt.modificar(a.getId(), artNuevo);
                break;
                
            case Comunes.TIPO_TECNICO:
                entidad = "técnico";
                lista = parent.getCamposActualizarTecnicos();
                Tecnico t = (Tecnico) actual,
                 tecNuevo = Operaciones.compruebaCamposTecnico(campos, modal);
                
                if (tecNuevo == null) {
                    modal.showDialog();
                    return;
                } else {
                    tecNuevo.setId(t.getId());
                    tecNuevo.setEventos(t.getEventos());
                }
                if (tecNuevo == null) {
                    modal.showDialog();
                    return;
                }

                if (flagIguales = t.equals(tecNuevo)) {
                    break;
                }

                if ((tecNuevo = Operaciones.compruebaCamposTecnico(campos, modal)) != null) {
                    modificado = dTec.modificar(t.getId(), tecNuevo);
                    break;
                } else {
                    modal.showDialog();
                    return;
                }
        }

        modal.setTipo(Modal.MODAL_INFO);
        if (modificado) {
            Operaciones.actualizaDatos(lista);
            modal.setText("El " + entidad + " ha sido modificado.");
        } else {
            if (flagIguales) {
                modal.setTipo(Modal.MODAL_WARNING);
                modal.setText("Los datos no han cambiado. No se puede modificar el " + entidad + ".");
            } else {
                tipo = Modal.MODAL_ERROR;
                modal.setText("El" + entidad + "no ha sido modificado");
            }
        }
        modal.setOpciones(false);
        modal.showDialog();
    }

    /**
     * Si la neuva entidad es null, la setea con los datos de la que se muestra.
     */
    private void modificarHandler() {
        if (nuevo == null) {
            nuevo = actual;
        }
        setModificar(!isModificar());
    }

    /**
     * Vuelve a la pantalla de listado correspondiente en función del tipo de elemento con el que se está trabajando.
     */
    private void volver() {
        switch (tipo) {
            case Comunes.TIPO_EVENTO:
                card.show(container, "evtList");
                parent.setTitle("Listado de eventos");
                break;
            case Comunes.TIPO_ARTISTA:
                card.show(container, "artList");
                parent.setTitle("Listado de artistas");
                break;
            case Comunes.TIPO_TECNICO:
                card.show(container, "tecList");
                parent.setTitle("Listado de técnicos");
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        botonera = new javax.swing.JPanel();
        btnVolver = new components.atoms.ImgButton();
        btnRestablecer = new components.atoms.ImgButton();
        btnModificar = new components.atoms.ImgButton();
        btnEliminar = new components.atoms.ImgButton();
        pnlModificar = new components.atoms.RoundPanel();
        lblModificar = new components.atoms.Label();
        checkModificar = new components.atoms.RoundCheckBox();

        setBackground(Colores.TRANSPARENTE);

        botonera.setBackground(Colores.TRANSPARENTE);

        btnVolver.setIcon(new javax.swing.ImageIcon("E:\\TFG\\Redimension\\assets\\buttons\\Volver.png")); // NOI18N
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVolverMouseClicked(evt);
            }
        });

        btnRestablecer.setIcon(new javax.swing.ImageIcon("E:\\TFG\\Redimension\\assets\\buttons\\Restablecer.png")); // NOI18N
        btnRestablecer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRestablecerMouseClicked(evt);
            }
        });

        btnModificar.setIcon(new javax.swing.ImageIcon("E:\\TFG\\Redimension\\assets\\buttons\\Modificar.png")); // NOI18N
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon("E:\\TFG\\Redimension\\assets\\buttons\\Eliminar_Blanco.png")); // NOI18N
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout botoneraLayout = new javax.swing.GroupLayout(botonera);
        botonera.setLayout(botoneraLayout);
        botoneraLayout.setHorizontalGroup(
            botoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, botoneraLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRestablecer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        botoneraLayout.setVerticalGroup(
            botoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnRestablecer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlModificar.setBackground(Colores.TRANSPARENTE);
        pnlModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlModificarMouseClicked(evt);
            }
        });
        pnlModificar.setLayout(new java.awt.GridBagLayout());

        lblModificar.setForeground(Colores.AZUL);
        lblModificar.setText("Modificar");
        lblModificar.setFont(Fuentes.LABEL);
        lblModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblModificarMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 12, 12);
        pnlModificar.add(lblModificar, gridBagConstraints);

        checkModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkModificarMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlModificar.add(checkModificar, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblModificarMouseClicked
        modificarHandler();
    }//GEN-LAST:event_lblModificarMouseClicked

    private void checkModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkModificarMouseClicked
        modificarHandler();
    }//GEN-LAST:event_checkModificarMouseClicked

    private void btnVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMouseClicked
        volver();
    }//GEN-LAST:event_btnVolverMouseClicked


    private void btnRestablecerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRestablecerMouseClicked
        restablecer();
    }//GEN-LAST:event_btnRestablecerMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        modificar();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        eliminar();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void pnlModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlModificarMouseClicked
        modificarHandler();
    }//GEN-LAST:event_pnlModificarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel botonera;
    private components.atoms.ImgButton btnEliminar;
    private components.atoms.ImgButton btnModificar;
    private components.atoms.ImgButton btnRestablecer;
    private components.atoms.ImgButton btnVolver;
    private components.atoms.RoundCheckBox checkModificar;
    private components.atoms.Label lblModificar;
    private components.atoms.RoundPanel pnlModificar;
    // End of variables declaration//GEN-END:variables

}
