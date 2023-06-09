package components.atoms;

import assets.lookandfeel.Colores;
import assets.lookandfeel.Iconos;

/**
 * Checkbox personalizado.
 * Hereda de JPanel, no de JCheckbox.
 * @author José Francisco Blasco Jiménez
 */
public class RoundCheckBox extends javax.swing.JPanel {
    private boolean selected;
    private boolean tabla;

    /**
     * Crea una nueva instancia.
     */
    public RoundCheckBox() {
        initComponents();
    }
    
    /**
     * 
     */
    public void resetear() {
        setSelected(false, tabla);
    }
    
    /**
     * Comprueba si está seleccionado.
     * @return True si está seleccionado, Flase en caso contrario.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * 
     * @param selected True para marcarlo como seleccionado, False para deseleccionarlo.
     * @param tabla True si se va a mostrar dentro de una tabla, False si no.
     */
    public void setSelected(boolean selected, boolean tabla) {
        this.selected = selected;
        
        //Si no se va a mostrar dentro de una tabla, cuando no esté seleccionado, se marcará con una X.
        if (!tabla) {
            if (isSelected()) {
                roundPanel1.setBackground(Colores.NARANJA);
                lblSelected.setIcon(Iconos.CHECK_BLANCO);
            } else {
                roundPanel1.setBackground(Colores.AZUL);
                lblSelected.setIcon(Iconos.X_BLANCA);
            }
        // Si no se muestra dentro de una tabla, cuando no esté seleccionado, se nostrará vacío
        } else {
                roundPanel1.setBackground(Colores.NARANJA);
            if (isSelected()) 
                lblSelected.setIcon(Iconos.CHECK_BLANCO);
             else
                lblSelected.setIcon(null);
        }
    }

    /**
     * Comprueba si se muestra dentro de una tabla.
     * @return True si se muestra en una tabla, False en caso contrario.
     */
    public boolean isTabla() {
        return tabla;
    }

    /**
     * Indica si se va a mostrar en una tabla.
     * @param tabla True si se va a mostrar en una tabla, False si no.
     */
    public void setTabla(boolean tabla) {
        this.tabla = tabla;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new components.atoms.RoundPanel();
        lblSelected = new javax.swing.JLabel();

        setBackground(Colores.TRANSPARENTE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        roundPanel1.setBackground(Colores.AZUL);
        roundPanel1.setPreferredSize(new java.awt.Dimension(26, 26));
        roundPanel1.setRoundBottomLeft(20);
        roundPanel1.setRoundBottomRight(20);
        roundPanel1.setRoundTopLeft(20);
        roundPanel1.setRoundTopRight(20);

        lblSelected.setBackground(Colores.TRANSPARENTE);
        lblSelected.setIcon(new javax.swing.ImageIcon("E:\\TFG\\JFBlascoJ_MongoDB\\JFBlascoJ_SalaEventos\\assets\\icons\\CheckBlanco.png")); // NOI18N

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSelected)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSelected)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        setSelected(!isSelected(), tabla);
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblSelected;
    private components.atoms.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
