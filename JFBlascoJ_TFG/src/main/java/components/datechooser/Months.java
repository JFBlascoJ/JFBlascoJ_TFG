package components.datechooser;

import assets.lookandfeel.Colores;
import assets.lookandfeel.Fuentes;

public final class Months extends javax.swing.JPanel {

    private Event event;
    private int m;

    public Months() {
        initComponents();
    }

    private void addEvent() {
        for (int i = 0; i < getComponentCount(); i++) {
            ((Button) getComponent(i)).setEvent(event);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmd1 = new components.datechooser.Button();
        cmd2 = new components.datechooser.Button();
        cmd3 = new components.datechooser.Button();
        cmd4 = new components.datechooser.Button();
        cmd5 = new components.datechooser.Button();
        cmd6 = new components.datechooser.Button();
        cmd7 = new components.datechooser.Button();
        cmd8 = new components.datechooser.Button();
        cmd9 = new components.datechooser.Button();
        cmd10 = new components.datechooser.Button();
        cmd11 = new components.datechooser.Button();
        cmd12 = new components.datechooser.Button();

        setBackground(Colores.GRISCLARO);
        setForeground(Colores.AZUL);
        setLayout(new java.awt.GridLayout(4, 4));

        cmd1.setBackground(Colores.TRANSPARENTE);
        cmd1.setForeground(Colores.AZUL);
        cmd1.setText("Enero");
        cmd1.setFont(Fuentes.LABEL);
        cmd1.setName("1"); // NOI18N
        cmd1.setOpaque(true);
        add(cmd1);

        cmd2.setBackground(Colores.TRANSPARENTE);
        cmd2.setForeground(Colores.AZUL);
        cmd2.setText("Febrero");
        cmd2.setFont(Fuentes.LABEL);
        cmd2.setName("2"); // NOI18N
        cmd2.setOpaque(true);
        cmd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd2ActionPerformed(evt);
            }
        });
        add(cmd2);

        cmd3.setBackground(Colores.TRANSPARENTE);
        cmd3.setForeground(Colores.AZUL);
        cmd3.setText("Marzo");
        cmd3.setFont(Fuentes.LABEL);
        cmd3.setName("3"); // NOI18N
        cmd3.setOpaque(true);
        add(cmd3);

        cmd4.setBackground(Colores.TRANSPARENTE);
        cmd4.setForeground(Colores.AZUL);
        cmd4.setText("Abril");
        cmd4.setFont(Fuentes.LABEL);
        cmd4.setName("4"); // NOI18N
        cmd4.setOpaque(true);
        add(cmd4);

        cmd5.setBackground(Colores.TRANSPARENTE);
        cmd5.setForeground(Colores.AZUL);
        cmd5.setText("Mayo");
        cmd5.setFont(Fuentes.LABEL);
        cmd5.setName("5"); // NOI18N
        cmd5.setOpaque(true);
        add(cmd5);

        cmd6.setBackground(Colores.TRANSPARENTE);
        cmd6.setForeground(Colores.AZUL);
        cmd6.setText("Junio");
        cmd6.setFont(Fuentes.LABEL);
        cmd6.setName("6"); // NOI18N
        cmd6.setOpaque(true);
        add(cmd6);

        cmd7.setBackground(Colores.TRANSPARENTE);
        cmd7.setForeground(Colores.AZUL);
        cmd7.setText("Julio");
        cmd7.setFont(Fuentes.LABEL);
        cmd7.setName("7"); // NOI18N
        cmd7.setOpaque(true);
        add(cmd7);

        cmd8.setBackground(Colores.TRANSPARENTE);
        cmd8.setForeground(Colores.AZUL);
        cmd8.setText("Agosto");
        cmd8.setFont(Fuentes.LABEL);
        cmd8.setName("8"); // NOI18N
        cmd8.setOpaque(true);
        add(cmd8);

        cmd9.setBackground(Colores.TRANSPARENTE);
        cmd9.setForeground(Colores.AZUL);
        cmd9.setText("Septiembre");
        cmd9.setFont(Fuentes.LABEL);
        cmd9.setName("9"); // NOI18N
        cmd9.setOpaque(true);
        add(cmd9);

        cmd10.setBackground(Colores.TRANSPARENTE);
        cmd10.setForeground(Colores.AZUL);
        cmd10.setText("Octubre");
        cmd10.setFont(Fuentes.LABEL);
        cmd10.setName("10"); // NOI18N
        cmd10.setOpaque(true);
        cmd10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd10ActionPerformed(evt);
            }
        });
        add(cmd10);

        cmd11.setBackground(Colores.TRANSPARENTE);
        cmd11.setForeground(Colores.AZUL);
        cmd11.setText("Noviembre");
        cmd11.setFont(Fuentes.LABEL);
        cmd11.setName("11"); // NOI18N
        cmd11.setOpaque(true);
        add(cmd11);

        cmd12.setBackground(Colores.TRANSPARENTE);
        cmd12.setForeground(Colores.AZUL);
        cmd12.setText("Diciembre");
        cmd12.setFont(Fuentes.LABEL);
        cmd12.setName("12"); // NOI18N
        cmd12.setOpaque(true);
        cmd12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmd12ActionPerformed(evt);
            }
        });
        add(cmd12);
    }// </editor-fold>//GEN-END:initComponents

    private void cmd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmd2ActionPerformed

    private void cmd10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmd10ActionPerformed

    private void cmd12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmd12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmd12ActionPerformed

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.datechooser.Button cmd1;
    private components.datechooser.Button cmd10;
    private components.datechooser.Button cmd11;
    private components.datechooser.Button cmd12;
    private components.datechooser.Button cmd2;
    private components.datechooser.Button cmd3;
    private components.datechooser.Button cmd4;
    private components.datechooser.Button cmd5;
    private components.datechooser.Button cmd6;
    private components.datechooser.Button cmd7;
    private components.datechooser.Button cmd8;
    private components.datechooser.Button cmd9;
    // End of variables declaration//GEN-END:variables

}
