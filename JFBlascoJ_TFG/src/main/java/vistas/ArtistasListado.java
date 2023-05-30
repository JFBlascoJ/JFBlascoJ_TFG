package vistas;

import assets.objects.Listeners;
import assets.lookandfeel.Colores;
import assets.lookandfeel.Bordes;
import GUI.Container;
import assets.lookandfeel.Fuentes;
import assets.objects.Comunes;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.HashMap;

/**
 *
 * @author José Francisco Blasco Jiménez
 */
public class ArtistasListado extends javax.swing.JPanel {
    private HashMap<String, Component> campos;

    /**
     * Creates new form NuevoEvento
     */
    public ArtistasListado() {
        initComponents();
        setCampos();
    }
    
    public void setComunes(Comunes comunes) {
        seteaMovidasDeComponentes(comunes);
    }
    
    private void seteaMovidasDeComponentes(Comunes comunes) {
        txtId.setComunes(comunes);
        txtMail.setComunes(comunes);
        txtNombre.setComunes(comunes);
        txtTlf.setComunes(comunes);
        botoneraFiltro.setComunes(comunes);
        btnEliminar.setComunes(comunes);
        tblArtistas.setComunes(comunes);
        txtTlf.getTextField().addKeyListener(comunes.getListeners().getNUMEROS());
        tblArtistas.setTipo(1);
        botoneraFiltro.setTabla(tblArtistas);
        btnEliminar.setTabla(tblArtistas);
        btnEliminar.setParent(comunes.getPARENT());
        botoneraFiltro.setParent(comunes.getPARENT());
    }

    private void setCampos() {
        campos = new HashMap();
        campos.put("txtId", txtId);
        campos.put("txtNombre", txtNombre);
        campos.put("txtTlf", txtTlf);
        campos.put("txtMail", txtMail);
        botoneraFiltro.setCampos(campos);
    }
        
    public void setContainer(Container c) {
        tblArtistas.setContainer(c);
        setComunes(c.getComunes());
        c.getFrame().getCamposActualizarArtistas().add(tblArtistas);
    }
    
    public void setCard(CardLayout card) {
        tblArtistas.setCard(card);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNuevoEvento = new components.atoms.RoundPanel();
        tblArtistas = new components.organisms.tables.TblListado();
        btnEliminar = new components.organisms.keypads.KeyListado();
        pnlFiltro = new components.atoms.RoundPanel();
        lblNombre = new components.atoms.Label();
        lblTlf = new components.atoms.Label();
        lblMail = new components.atoms.Label();
        jSeparator1 = new javax.swing.JSeparator();
        botoneraFiltro = new components.organisms.keypads.KeyFiltro();
        txtNombre = new components.atoms.txt.RoundTextField();
        txtTlf = new components.atoms.txt.RoundTextField();
        txtMail = new components.atoms.txt.RoundTextField();
        txtId = new components.atoms.txt.RoundTextField();
        lblFiltro = new components.atoms.Label();

        setBackground(Colores.TRANSPARENTE);

        pnlNuevoEvento.setBackground(Colores.GRISAZUL);
        pnlNuevoEvento.setPreferredSize(new java.awt.Dimension(800, 600));
        pnlNuevoEvento.setRoundBottomRight(99);
        pnlNuevoEvento.setRoundTopRight(99);

        pnlFiltro.setBackground(Colores.TRANSPARENTE);
        pnlFiltro.setBorder(Bordes.GRIS40);
        pnlFiltro.setRoundBottomLeft(40);
        pnlFiltro.setRoundBottomRight(40);
        pnlFiltro.setRoundTopLeft(40);
        pnlFiltro.setRoundTopRight(40);

        lblNombre.setText("Nombre");

        lblTlf.setText("Teléfono");

        lblMail.setText("E-mail");

        jSeparator1.setBackground(Colores.BLANCO);
        jSeparator1.setForeground(Colores.BLANCO);
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N

        txtNombre.setLabel(lblNombre);

        txtTlf.setLabel(lblTlf);

        txtMail.setLabel(lblMail);

        javax.swing.GroupLayout pnlFiltroLayout = new javax.swing.GroupLayout(pnlFiltro);
        pnlFiltro.setLayout(pnlFiltroLayout);
        pnlFiltroLayout.setHorizontalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnlFiltroLayout.createSequentialGroup()
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(botoneraFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlFiltroLayout.createSequentialGroup()
                            .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addComponent(lblTlf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtTlf, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addComponent(lblMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlFiltroLayout.setVerticalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTlf, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtTlf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botoneraFiltro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        lblFiltro.setText("Filtros");

        javax.swing.GroupLayout pnlNuevoEventoLayout = new javax.swing.GroupLayout(pnlNuevoEvento);
        pnlNuevoEvento.setLayout(pnlNuevoEventoLayout);
        pnlNuevoEventoLayout.setHorizontalGroup(
            pnlNuevoEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNuevoEventoLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnlNuevoEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNuevoEventoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(pnlNuevoEventoLayout.createSequentialGroup()
                        .addGroup(pnlNuevoEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 22, Short.MAX_VALUE))))
            .addGroup(pnlNuevoEventoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(tblArtistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlNuevoEventoLayout.setVerticalGroup(
            pnlNuevoEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNuevoEventoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(tblArtistas, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNuevoEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNuevoEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.organisms.keypads.KeyFiltro botoneraFiltro;
    private components.organisms.keypads.KeyListado btnEliminar;
    private javax.swing.JSeparator jSeparator1;
    private components.atoms.Label lblFiltro;
    private components.atoms.Label lblMail;
    private components.atoms.Label lblNombre;
    private components.atoms.Label lblTlf;
    private components.atoms.RoundPanel pnlFiltro;
    private components.atoms.RoundPanel pnlNuevoEvento;
    private components.organisms.tables.TblListado tblArtistas;
    private components.atoms.txt.RoundTextField txtId;
    private components.atoms.txt.RoundTextField txtMail;
    private components.atoms.txt.RoundTextField txtNombre;
    private components.atoms.txt.RoundTextField txtTlf;
    // End of variables declaration//GEN-END:variables
}
