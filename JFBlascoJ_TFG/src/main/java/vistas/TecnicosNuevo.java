package vistas;

import assets.objects.Comunes;
import assets.lookandfeel.Colores;
import DAO.DAOTecnico;
import java.awt.*;
import java.util.HashMap;
import GUI.Container;

/**
 *
 * @author José Francisco Blasco Jiménez
 */
public class TecnicosNuevo extends javax.swing.JPanel {
    private HashMap<String, Component> campos;
    private DAOTecnico dTec;
    private Container container;

    /**
     * Creates new form NuevoEvento
     */
    public TecnicosNuevo() {
        initComponents();
    }
    
    public void setComunes(Comunes comunes) {
        dTec = comunes.getDAOTEC();
        setCampos();
        seteaMovidasDeComponentes(comunes);
    }
    
    private void seteaMovidasDeComponentes(Comunes comunes) {
        txtMail.setComunes(comunes);
        txtTlf.setComunes(comunes);
        txtNombre.setComunes(comunes);
        botonera.setComunes(comunes);
        txtNombre.setRequerido(true);
        txtNombre.getTextField().getDocument().addDocumentListener(comunes.getListeners().getREQUERIDO());
        txtTlf.getTextField().addKeyListener(comunes.getListeners().getNUMEROS());
        txtMail.getTextField().getDocument().addDocumentListener(comunes.getListeners().getMAIL());
        botonera.setParent(comunes.getPARENT());
        botonera.setCampos(campos);
    }

    private void setCampos() {
        campos = new HashMap();
        campos.put("txtNombre", txtNombre);
        campos.put("txtTlf", txtTlf);
        campos.put("txtMail", txtMail);
        botonera.setCampos(campos);
    }

    public DAOTecnico getDaoTec() {
        return dTec;
    }

    public void setDaoTec(DAOTecnico daoTec) {
        this.dTec = daoTec;
    }

    public void setContainer(Container c) {
        container = c;
        setComunes(c.getComunes());
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
        txtNombre = new components.atoms.txt.RoundTextField();
        lblNombre = new components.atoms.Label();
        txtTlf = new components.atoms.txt.RoundTextField();
        lblTlf = new components.atoms.Label();
        lblMail = new components.atoms.Label();
        txtMail = new components.atoms.txt.RoundTextField();
        botonera = new components.organisms.keypads.KeyNuevo();

        setBackground(Colores.TRANSPARENTE);

        pnlNuevoEvento.setBackground(Colores.GRISAZUL);
        pnlNuevoEvento.setPreferredSize(new java.awt.Dimension(800, 600));
        pnlNuevoEvento.setRoundBottomRight(99);
        pnlNuevoEvento.setRoundTopRight(99);

        txtNombre.setLabel(lblNombre);

        lblNombre.setText("Nombre");

        txtTlf.setLabel(lblTlf);

        lblTlf.setText("Teléfono");

        lblMail.setText("E-mail");

        txtMail.setLabel(lblMail);

        botonera.setTipo(Comunes.TIPO_TECNICO);

        javax.swing.GroupLayout pnlNuevoEventoLayout = new javax.swing.GroupLayout(pnlNuevoEvento);
        pnlNuevoEvento.setLayout(pnlNuevoEventoLayout);
        pnlNuevoEventoLayout.setHorizontalGroup(
            pnlNuevoEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNuevoEventoLayout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addGroup(pnlNuevoEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTlf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlNuevoEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTlf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(260, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNuevoEventoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        pnlNuevoEventoLayout.setVerticalGroup(
            pnlNuevoEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNuevoEventoLayout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addGroup(pnlNuevoEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlNuevoEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTlf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTlf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlNuevoEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                .addComponent(botonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNuevoEvento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNuevoEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.organisms.keypads.KeyNuevo botonera;
    private components.atoms.Label lblMail;
    private components.atoms.Label lblNombre;
    private components.atoms.Label lblTlf;
    private components.atoms.RoundPanel pnlNuevoEvento;
    private components.atoms.txt.RoundTextField txtMail;
    private components.atoms.txt.RoundTextField txtNombre;
    private components.atoms.txt.RoundTextField txtTlf;
    // End of variables declaration//GEN-END:variables
}
