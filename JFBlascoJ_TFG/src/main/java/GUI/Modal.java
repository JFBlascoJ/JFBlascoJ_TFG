package GUI;

import DAO.DAOArtista;
import DAO.DAOEvento;
import DAO.DAOTecnico;
import POJOs.Artista;
import POJOs.Tecnico;
import assets.lookandfeel.*;
import assets.objects.Comunes;
import components.atoms.Label;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author José Francisco Blasco Jiménez
 */
public class Modal extends javax.swing.JDialog {

    private Interfaz parent;
    private int tipo;
    private Tecnico t;
    private ArrayList<Artista> artistas = new ArrayList();
    private boolean opciones;
    private DAOTecnico daoTec;
    private DAOArtista daoArt;
    private int x, y;

    public static final int MODAL_OK = 0;
    public static final int MODAL_ERROR = 1;
    public static final int MODAL_INFO = 2;
    public static final int MODAL_WARNING = 3;

    /**
     * Creates new form Modal
     */
    public Modal(int tipo, String msg, Interfaz parent, boolean opciones) {
        super(parent, true);
        this.parent = parent;
        setUndecorated(true);
        initComponents();
        setTipo(tipo);
        setText(msg);
        muestraYOcultaBotones(opciones);
        repaint();
    }

    /**
     * Setea los objetos comunes para toda la aplicación.
     *
     * @param comunes Objeto de tipo Comunes a asignar.
     */
    public void setComunes(Comunes comunes) {
        daoArt = comunes.getDAOART();
        daoTec = comunes.getDAOTEC();
    }

    /**
     * Muestra unos botones u otros en función de si la vente ofrece opciones o
     * no.
     *
     * @param opciones True para ofrecer opciones (Sí/No), False para no
     * ofrecerlas (Aceptar)
     */
    private void muestraYOcultaBotones(boolean opciones) {
        if (opciones) {
            btnAceptar.setVisible(false);
            btnSi.setVisible(true);
            btnNo.setVisible(true);
        } else {
            btnAceptar.setVisible(true);
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        }
    }

    /**
     * Muestra la ventana modal.
     */
    public void showDialog() {
        // Calcula la posición central
        int parentX = parent.getLocationOnScreen().x;
        int parentY = parent.getLocationOnScreen().y;
        int parentWidth = parent.getWidth();
        int parentHeight = parent.getHeight();
        // Obtiene la altura y la anchura de la ventana y calcula el centro.
        int dialogWidth = getWidth();
        int dialogHeight = getHeight();
        int dialogX = parentX + (parentWidth - dialogWidth) / 2;
        int dialogY = parentY + (parentHeight - dialogHeight) / 2;

        // Establece la posición del cuadro de diálogo en el centro de la ventana padre
        setLocation(dialogX, dialogY);

        // Muestra el cuadro de diálogo
        setVisible(true);
    }

    /**
     * Setea si el modal va a tener opciones (SÍ/NO) o simplemente un botón de
     * "Aceptar".
     *
     * @param opciones True si tiene opciones, Flase en caso contrario.
     */
    public void setOpciones(boolean opciones) {
        this.opciones = opciones;
        muestraYOcultaBotones(opciones);
    }

    /**
     * Devuelve el mensaje.
     *
     * @return Mensaje del modal.
     */
    public String getText() {
        return lblMensaje.getText();
    }

    /**
     * Setea el mensaje.
     *
     * @param msj
     */
    public void setText(String msj) {
        lblMensaje.setText(msj);
    }

    /**
     * Setea el técnico. Cuando el evento seleccionado no tiene técnico, se deja
     * elegir al usuario si continuar o no. Esto sirve para poder actuar en
     * consecuencia.
     *
     * @param t Técnico a asignar.
     */
    public void setTecnico(Tecnico t) {
        this.t = t;
    }

    /**
     * Setea la lista de artistas. Cuando el evento seleccionado no tiene
     * artistas, se deja elegir al usuario si continuar o no. Esto sirve para
     * poder actuar en consecuencia.
     *
     * @param artistas Lista de artistas a asignar.
     */
    public void setArtistas(ArrayList<Artista> artistas) {
        this.artistas = artistas;
    }

    /**
     * Setea el tipo de ventana de modal. Según el tipo, el contorno y el icono
     * serán de un color u otro: INFO Y OK: Azul. ERROR y WARNING: Naranja.
     *
     * @param tipo Tipo de ventana a asignar:
     * - OK: La operación se ha realizado
     * correctamente.
     * - ERROR: Ha habido algún error al realizar la operación.
     * - INFO: Informar al usuario.
     * - WARNING: Alertar al usuario.
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;

        switch (tipo) {
            case Modal.MODAL_OK:
                lblIcon.setIcon(Iconos.MODAL_CHECK);
                pnlContorno.setBackground(Colores.AZUL);
                break;
            case Modal.MODAL_ERROR:
                lblIcon.setIcon(Iconos.MODAL_ERROR);
                pnlContorno.setBackground(Colores.NARANJA);
                break;
            case Modal.MODAL_INFO:
                lblIcon.setIcon(Iconos.MODAL_INFO);
                pnlContorno.setBackground(Colores.AZUL);
                break;
            case Modal.MODAL_WARNING:
                lblIcon.setIcon(Iconos.MODAL_WARNING);
                pnlContorno.setBackground(Colores.NARANJA);
                break;
            default:
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

        pnlContorno = new components.atoms.RoundPanel();
        pnlMain = new components.atoms.RoundPanel();
        pnlMensaje = new javax.swing.JPanel();
        lblIcon = new javax.swing.JLabel();
        lblMensaje = new components.atoms.Label();
        botonera = new javax.swing.JPanel();
        btnAceptar = new components.atoms.ImgButton();
        btnNo = new components.atoms.ImgButton();
        btnSi = new components.atoms.ImgButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(Colores.TRANSPARENTE);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        pnlContorno.setBackground(Colores.AZUL);
        pnlContorno.setMinimumSize(new java.awt.Dimension(400, 193));
        pnlContorno.setRoundBottomLeft(40);
        pnlContorno.setRoundBottomRight(40);
        pnlContorno.setRoundTopLeft(40);
        pnlContorno.setRoundTopRight(40);

        pnlMain.setBackground(Colores.GRISCLARO);
        pnlMain.setRoundBottomLeft(40);
        pnlMain.setRoundBottomRight(40);
        pnlMain.setRoundTopLeft(40);
        pnlMain.setRoundTopRight(40);
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlMensaje.setBackground(Colores.TRANSPARENTE);
        pnlMensaje.setMinimumSize(new java.awt.Dimension(594, 50));
        pnlMensaje.setPreferredSize(new java.awt.Dimension(594, 50));
        pnlMensaje.setLayout(new java.awt.GridBagLayout());

        lblIcon.setIcon(new javax.swing.ImageIcon("E:\\TFG\\Redimension\\assets\\icons\\Modal_CheckCircle.png")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipady = 10;
        pnlMensaje.add(lblIcon, gridBagConstraints);

        lblMensaje.setBackground(Colores.TRANSPARENTE);
        lblMensaje.setForeground(Colores.AZUL);
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensaje.setText("Mensaje");
        lblMensaje.setMaximumSize(new java.awt.Dimension(60, 20));
        lblMensaje.setMinimumSize(new java.awt.Dimension(500, 100));
        lblMensaje.setPreferredSize(new java.awt.Dimension(500, 100));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 1;
        pnlMensaje.add(lblMensaje, gridBagConstraints);

        pnlMain.add(pnlMensaje, java.awt.BorderLayout.CENTER);

        botonera.setBackground(Colores.TRANSPARENTE);
        botonera.setLayout(new java.awt.GridBagLayout());

        btnAceptar.setTipo(Botones.BTN_MODAL_ACEPTAR);
        btnAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAceptarMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        botonera.add(btnAceptar, gridBagConstraints);

        btnNo.setTipo(Botones.BTN_MODAL_NO);
        btnNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNoMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        botonera.add(btnNo, gridBagConstraints);

        btnSi.setTipo(Botones.BTN_MODAL_SI);
        btnSi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSiMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        botonera.add(btnSi, gridBagConstraints);

        pnlMain.add(botonera, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout pnlContornoLayout = new javax.swing.GroupLayout(pnlContorno);
        pnlContorno.setLayout(pnlContornoLayout);
        pnlContornoLayout.setHorizontalGroup(
            pnlContornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContornoLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        pnlContornoLayout.setVerticalGroup(
            pnlContornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContornoLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContorno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContorno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Al pulsar "aceptar", se cierra la ventana.
     *
     * @param evt
     */
    private void btnAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseClicked
        dispose();
        btnAceptar.setIcon(btnAceptar.getNormal());
    }//GEN-LAST:event_btnAceptarMouseClicked
    /**
     * Al pulsar "No", los objetos (técnico y artistas) que sean null, seguirán
     * siendo null.
     *
     * @param evt Evento de ratón que llama al método.
     */
    private void btnNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNoMouseClicked
        t = t == null ? null : t;
        artistas = artistas == null ? null : artistas;
        dispose();
        btnNo.setIcon(btnNo.getNormal());
    }//GEN-LAST:event_btnNoMouseClicked

    /**
     * Al pulser "Sí", los objetos (técnico y artistas) que sean null, se
     * setearán al elemento "comodín" ("Sin artistas", "Sin técnico", o ambos).
     *
     * @param evt Evento de ratón que invoca al método.
     */
    private void btnSiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSiMouseClicked
        t = t == null ? daoTec.consultarPorId("T0") : t;
        if (artistas == null) {
            artistas = new ArrayList();
            artistas.add(daoArt.consultarPorId("A0"));
        }
        dispose();
        btnSi.setIcon(btnNo.getNormal());
    }//GEN-LAST:event_btnSiMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Point p = MouseInfo.getPointerInfo().getLocation();
        setLocation(p.x - x, p.y - y);
        setCursor(new Cursor(Cursor.MOVE_CURSOR));
    }//GEN-LAST:event_formMouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel botonera;
    private components.atoms.ImgButton btnAceptar;
    private components.atoms.ImgButton btnNo;
    private components.atoms.ImgButton btnSi;
    private javax.swing.JLabel lblIcon;
    private components.atoms.Label lblMensaje;
    private components.atoms.RoundPanel pnlContorno;
    private components.atoms.RoundPanel pnlMain;
    private javax.swing.JPanel pnlMensaje;
    // End of variables declaration//GEN-END:variables
}
