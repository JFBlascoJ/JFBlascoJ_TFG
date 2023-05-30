package components.atoms.txt;

import assets.lookandfeel.Colores;
import assets.lookandfeel.Bordes;
import assets.lookandfeel.Fuentes;
import assets.objects.Comunes;
import components.atoms.scrollbar.ScrollBarCustom;
import javax.swing.JTextArea;
/**
 *
 * @author José Francisco Blasco Jiménez
 */
public class RoundTextArea extends javax.swing.JPanel {  
    /**
     * Creates new form RoundTextArea
     */
    public RoundTextArea() {
        initComponents();
        scrollPane.setVerticalScrollBar(new ScrollBarCustom());
    }

    public JTextArea getArea() {
        return area;
    }

    public void setArea(JTextArea area) {
        this.area = area;
    }

    public String getText() {
        return area.getText();
    }

    public void setText(String text) {
        area.setText(text);
    }
    
    public void resetear() {
        area.setText("");
    }
    
    public void habilitar() {
        setEnabled(true);
        area.setEditable(true);
        area.setBackground(Colores.BLANCO);
        pnlRound.setBackground(Colores.BLANCO);
        pnlRound.setBorder(Bordes.TEXTFIELD);
    }
    
    public void deshabilitar() {
        setEnabled(false);
        area.setEditable(false);
        area.setBackground(Colores.GRISCLARO);
        pnlRound.setBackground(Colores.GRISCLARO);
        pnlRound.setBorder(Bordes.TEXTFIELD_DISABLED);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRound = new components.atoms.RoundPanel();
        scrollPane = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();

        setBackground(Colores.TRANSPARENTE);
        setPreferredSize(new java.awt.Dimension(350, 217));

        pnlRound.setBackground(new java.awt.Color(255, 255, 255));
        pnlRound.setBorder(Bordes.AZUL40);
        pnlRound.setPreferredSize(new java.awt.Dimension(350, 160));
        pnlRound.setRoundBottomLeft(40);
        pnlRound.setRoundBottomRight(40);
        pnlRound.setRoundTopLeft(40);
        pnlRound.setRoundTopRight(40);

        scrollPane.setBorder(null);

        area.setColumns(20);
        area.setFont(Fuentes.REGULAR);
        area.setForeground(Colores.AZUL);
        area.setLineWrap(true);
        area.setRows(5);
        area.setBorder(null);
        scrollPane.setViewportView(area);

        javax.swing.GroupLayout pnlRoundLayout = new javax.swing.GroupLayout(pnlRound);
        pnlRound.setLayout(pnlRoundLayout);
        pnlRoundLayout.setHorizontalGroup(
            pnlRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRoundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlRoundLayout.setVerticalGroup(
            pnlRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRoundLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private components.atoms.RoundPanel pnlRound;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
