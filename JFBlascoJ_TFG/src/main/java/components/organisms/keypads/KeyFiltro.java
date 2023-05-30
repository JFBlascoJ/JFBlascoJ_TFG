package components.organisms.keypads;

import assets.lookandfeel.Botones;
import assets.objects.Comunes;
import components.atoms.combo.CustomComboBox;
import components.organisms.tables.TblListado;
import DAO.DAOArtista;
import DAO.DAOEvento;
import DAO.DAOTecnico;
import GUI.Interfaz;
import GUI.Modal;
import POJOs.Tecnico;
import assets.lookandfeel.Colores;
import components.atoms.txt.RoundTextField;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import assets.resources.Operaciones;

/**
 *
 * @author José Francisco Blasco Jiménez
 */
public class KeyFiltro extends javax.swing.JPanel {
    private DAOEvento dEv;
    private DAOArtista dArt;
    private DAOTecnico dTec;
    private HashMap<String, Component> campos;
    private TblListado tabla;
    private boolean flagFiltro, flagId;
    private int tipo;
    private Interfaz parent;
    private Modal modal;
    private Colores colores;
    private Botones botones;

    /**
     * Crea una nueva instancia.
     */
    public KeyFiltro() {
        initComponents();
    }

    /**
     * Setea los objetos comunes.
     * @param comunes Objeto de tipo Comunes a asignar.
     */
    public void setComunes(Comunes comunes) {
        modal = comunes.getMODAL();
        dEv = comunes.getDAOEV();
        dArt = comunes.getDAOART();
        dTec = comunes.getDAOTEC();
    }
    
    /**
     * Setea la tabla con la que va a trabajar la botonera.
     * @param tabla Tabla a asignar.
     */
    public void setTabla(TblListado tabla) {
        this.tabla = tabla;
        tipo = tabla.getTipo();
    }

    /**
     * Setea los campos con los que se va a trabajar.
     * @param campos Mapa con los campos en cuestión.
     */
    public void setCampos(HashMap<String, Component> campos) {
        this.campos = campos;
    }

    /**
     * Setea una ventana para tener referencia para mostrar los mensajes.
     * @param parent Ventana a asignar.
     */
    public void setParent(Interfaz parent) {
        this.parent = parent;
    }
    
    /**
     * Filtra los resultados según los criterios indicados por el usuario
     */
    public void filtrar() {
        HashMap<String, Object> datos = new HashMap();
        RoundTextField txtField = (RoundTextField) campos.get("txtId");
        CustomComboBox combo;
        ArrayList<String> idsArtistas = new ArrayList();
        
        if (flagId)
            datos.put("id", txtField.getText());
        else {
            switch (tipo) {
                case Comunes.TIPO_EVENTO:
                    txtField = (RoundTextField) campos.get("txtNombre");
                    if (!txtField.getText().equals(""))
                        datos.put("nombre", txtField.getText());
                    txtField = (RoundTextField) campos.get("txtFechaDel");
                    if (!txtField.getText().equals(""))
                        datos.put("fechaDel", Operaciones.deCadenaAFecha(txtField.getText()));
                    txtField = (RoundTextField) campos.get("txtFechaAl");
                    if (!txtField.getText().equals(""))
                        datos.put("fechaAl", Operaciones.deCadenaAFecha(txtField.getText()));
                    combo = (CustomComboBox) campos.get("cmbTecnico");
                    if (combo.getSelectedIndex() != -1) {
                        Tecnico t = (Tecnico) combo.getSelected();
                        datos.put("tecnico", t.getId());
                    }
                break;
                case Comunes.TIPO_ARTISTA:
                    txtField = (RoundTextField) campos.get("txtNombre");
                    if (!txtField.getText().equals(""))
                        datos.put("nombre", txtField.getText());
                    txtField = (RoundTextField) campos.get("txtTlf");
                    if (!txtField.getText().equals(""))
                        datos.put("tlf", txtField.getText());
                    txtField = (RoundTextField) campos.get("txtTlf");
                    if (!txtField.getText().equals(""))
                        datos.put("email", txtField.getText());                   
                    break;
                case Comunes.TIPO_TECNICO:
                    txtField = (RoundTextField) campos.get("txtNombre");
                    if (!txtField.getText().equals(""))
                        datos.put("nombre", txtField.getText());
                    txtField = (RoundTextField) campos.get("txtTlf");
                    if (!txtField.getText().equals(""))
                        datos.put("tlf", txtField.getText());
                    txtField = (RoundTextField) campos.get("txtTlf");
                    if (!txtField.getText().equals(""))
                        datos.put("email", txtField.getText());                
                    break;
            }
        }
        
        if (tabla.filtrar(datos))
            flagFiltro = true;
        else {
            flagFiltro = false;
            modal.setTipo(modal.MODAL_INFO);
            modal.setText("Ningún elemento coincide con los criterios de búsqueda.");
            modal.setOpciones(false);
            modal.showDialog();
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

        btnFiltrarId = new components.atoms.ImgButton();
        pnlFiltrar = new javax.swing.JPanel();
        btnAplicar = new components.atoms.ImgButton();
        btnLimpiar = new components.atoms.ImgButton();

        setBackground(Colores.TRANSPARENTE);

        btnFiltrarId.setIcon(Botones.FILTRARID);
        btnFiltrarId.setTipo(Botones.BTN_FILTRO_ID);
        btnFiltrarId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFiltrarIdMouseClicked(evt);
            }
        });

        pnlFiltrar.setBackground(Colores.TRANSPARENTE);

        btnAplicar.setIcon(Botones.FILTRO_APLICAR);
        btnAplicar.setTipo(Botones.BTN_FILTRO_APLICAR);
        btnAplicar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAplicarMouseClicked(evt);
            }
        });

        btnLimpiar.setIcon(Botones.FILTRO_LIMPIAR);
        btnLimpiar.setTipo(Botones.BTN_FILTRO_LIMPIAR);
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlFiltrarLayout = new javax.swing.GroupLayout(pnlFiltrar);
        pnlFiltrar.setLayout(pnlFiltrarLayout);
        pnlFiltrarLayout.setHorizontalGroup(
            pnlFiltrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFiltrarLayout.createSequentialGroup()
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAplicar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlFiltrarLayout.setVerticalGroup(
            pnlFiltrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltrarLayout.createSequentialGroup()
                .addGroup(pnlFiltrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAplicar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnFiltrarId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 518, Short.MAX_VALUE)
                .addComponent(pnlFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFiltrarId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Al pulsar el botón, se aplican los filtros
     * @param evt Evento de ratón.
     */
    private void btnAplicarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAplicarMouseClicked
        HashMap <String, Component> aux = new HashMap();
        flagId = false;
        filtrar();
        aux.put("txtId", campos.get("txtId"));
        Operaciones.limpiar(aux);
    }//GEN-LAST:event_btnAplicarMouseClicked

    /**
     * Al pulsar el botón "Limpiar", se limpian los campos del filtro.
     * Si hay algún filtro aplicado, se desactiva, mostrando de nuevo el listado completo.
     * @param evt Evento de ratón.
     */
    private void btnLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseClicked
        Operaciones.limpiar(campos);
        if (flagFiltro)
            tabla.setTipo(tipo);
        flagFiltro = false;
        flagId = false;
    }//GEN-LAST:event_btnLimpiarMouseClicked

    /**
     * Al pulsar el botón "Filtrar por id", se filtra por id.
     * Si alguno de los otros campos del filtro no está vacío, se ignora y se limpia.
     * @param evt Evento de ratón.
     */
    private void btnFiltrarIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFiltrarIdMouseClicked
        HashMap<String, Component> aux;
        flagId = true;
        filtrar();
        aux = new HashMap();
        aux.remove("txtId");
        Operaciones.limpiar(aux);
        
    }//GEN-LAST:event_btnFiltrarIdMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.atoms.ImgButton btnAplicar;
    private components.atoms.ImgButton btnFiltrarId;
    private components.atoms.ImgButton btnLimpiar;
    private javax.swing.JPanel pnlFiltrar;
    // End of variables declaration//GEN-END:variables
}
