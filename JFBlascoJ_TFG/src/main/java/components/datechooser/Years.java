package components.datechooser;

import assets.lookandfeel.Colores;
import assets.lookandfeel.Fuentes;
import javax.swing.JButton;

public final class Years extends javax.swing.JPanel {
private Event event;
    private int startYear;

    public Years() {
        initComponents();
    }
    
    public int showYear(int year) {
        year = calculateYear(year);
        for (int i = 0; i < getComponentCount(); i++) {
            JButton cmd = (JButton) getComponent(i);
            cmd.setText(year + "");
            year++;
        }
        return startYear;
    }

    private int calculateYear(int year) {
        year -= year % 10;
        startYear = year;
        return year;
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
        cmd13 = new components.datechooser.Button();
        cmd14 = new components.datechooser.Button();
        cmd15 = new components.datechooser.Button();
        cmd16 = new components.datechooser.Button();
        cmd17 = new components.datechooser.Button();
        cmd18 = new components.datechooser.Button();
        cmd19 = new components.datechooser.Button();
        cmd20 = new components.datechooser.Button();

        setBackground(Colores.GRISCLARO);
        setLayout(new java.awt.GridLayout(5, 4));

        cmd1.setBackground(Colores.TRANSPARENTE);
        cmd1.setForeground(Colores.AZUL);
        cmd1.setText("2010");
        cmd1.setFont(Fuentes.LABEL);
        cmd1.setName("year"); // NOI18N
        cmd1.setOpaque(true);
        add(cmd1);

        cmd2.setBackground(Colores.TRANSPARENTE);
        cmd2.setForeground(Colores.AZUL);
        cmd2.setText("2011");
        cmd2.setFont(Fuentes.LABEL);
        cmd2.setName("year"); // NOI18N
        cmd2.setOpaque(true);
        add(cmd2);

        cmd3.setBackground(Colores.TRANSPARENTE);
        cmd3.setForeground(Colores.AZUL);
        cmd3.setText("2012");
        cmd3.setFont(Fuentes.LABEL);
        cmd3.setName("year"); // NOI18N
        cmd3.setOpaque(true);
        add(cmd3);

        cmd4.setBackground(Colores.TRANSPARENTE);
        cmd4.setForeground(Colores.AZUL);
        cmd4.setText("2013");
        cmd4.setFont(Fuentes.LABEL);
        cmd4.setName("year"); // NOI18N
        cmd4.setOpaque(true);
        add(cmd4);

        cmd5.setBackground(Colores.TRANSPARENTE);
        cmd5.setForeground(Colores.AZUL);
        cmd5.setText("2014");
        cmd5.setFont(Fuentes.LABEL);
        cmd5.setName("year"); // NOI18N
        cmd5.setOpaque(true);
        add(cmd5);

        cmd6.setBackground(Colores.TRANSPARENTE);
        cmd6.setForeground(Colores.AZUL);
        cmd6.setText("2015");
        cmd6.setFont(Fuentes.LABEL);
        cmd6.setName("year"); // NOI18N
        cmd6.setOpaque(true);
        add(cmd6);

        cmd7.setBackground(Colores.TRANSPARENTE);
        cmd7.setForeground(Colores.AZUL);
        cmd7.setText("2016");
        cmd7.setFont(Fuentes.LABEL);
        cmd7.setName("year"); // NOI18N
        cmd7.setOpaque(true);
        add(cmd7);

        cmd8.setBackground(Colores.TRANSPARENTE);
        cmd8.setForeground(Colores.AZUL);
        cmd8.setText("2017");
        cmd8.setFont(Fuentes.LABEL);
        cmd8.setName("year"); // NOI18N
        cmd8.setOpaque(true);
        add(cmd8);

        cmd9.setBackground(Colores.TRANSPARENTE);
        cmd9.setForeground(Colores.AZUL);
        cmd9.setText("2018");
        cmd9.setFont(Fuentes.LABEL);
        cmd9.setName("year"); // NOI18N
        cmd9.setOpaque(true);
        add(cmd9);

        cmd10.setBackground(Colores.TRANSPARENTE);
        cmd10.setForeground(Colores.AZUL);
        cmd10.setText("2019");
        cmd10.setFont(Fuentes.LABEL);
        cmd10.setName("year"); // NOI18N
        cmd10.setOpaque(true);
        add(cmd10);

        cmd11.setBackground(Colores.TRANSPARENTE);
        cmd11.setForeground(Colores.AZUL);
        cmd11.setText("2020");
        cmd11.setFont(Fuentes.LABEL);
        cmd11.setName("year"); // NOI18N
        cmd11.setOpaque(true);
        add(cmd11);

        cmd12.setBackground(Colores.TRANSPARENTE);
        cmd12.setForeground(Colores.AZUL);
        cmd12.setText("2021");
        cmd12.setFont(Fuentes.LABEL);
        cmd12.setName("year"); // NOI18N
        cmd12.setOpaque(true);
        add(cmd12);

        cmd13.setBackground(Colores.TRANSPARENTE);
        cmd13.setForeground(Colores.AZUL);
        cmd13.setText("2022");
        cmd13.setFont(Fuentes.LABEL);
        cmd13.setName("year"); // NOI18N
        cmd13.setOpaque(true);
        add(cmd13);

        cmd14.setBackground(Colores.TRANSPARENTE);
        cmd14.setForeground(Colores.AZUL);
        cmd14.setText("2023");
        cmd14.setFont(Fuentes.LABEL);
        cmd14.setName("year"); // NOI18N
        cmd14.setOpaque(true);
        add(cmd14);

        cmd15.setBackground(Colores.TRANSPARENTE);
        cmd15.setForeground(Colores.AZUL);
        cmd15.setText("2024");
        cmd15.setFont(Fuentes.LABEL);
        cmd15.setName("year"); // NOI18N
        cmd15.setOpaque(true);
        add(cmd15);

        cmd16.setBackground(Colores.TRANSPARENTE);
        cmd16.setForeground(Colores.AZUL);
        cmd16.setText("2025");
        cmd16.setFont(Fuentes.LABEL);
        cmd16.setName("year"); // NOI18N
        cmd16.setOpaque(true);
        add(cmd16);

        cmd17.setBackground(Colores.TRANSPARENTE);
        cmd17.setForeground(Colores.AZUL);
        cmd17.setText("2026");
        cmd17.setFont(Fuentes.LABEL);
        cmd17.setName("year"); // NOI18N
        cmd17.setOpaque(true);
        add(cmd17);

        cmd18.setBackground(Colores.TRANSPARENTE);
        cmd18.setForeground(Colores.AZUL);
        cmd18.setText("2027");
        cmd18.setFont(Fuentes.LABEL);
        cmd18.setName("year"); // NOI18N
        cmd18.setOpaque(true);
        add(cmd18);

        cmd19.setBackground(Colores.TRANSPARENTE);
        cmd19.setForeground(Colores.AZUL);
        cmd19.setText("2028");
        cmd19.setFont(Fuentes.LABEL);
        cmd19.setName("year"); // NOI18N
        cmd19.setOpaque(true);
        add(cmd19);

        cmd20.setBackground(Colores.TRANSPARENTE);
        cmd20.setForeground(Colores.AZUL);
        cmd20.setText("2029");
        cmd20.setFont(Fuentes.LABEL);
        cmd20.setName("year"); // NOI18N
        cmd20.setOpaque(true);
        add(cmd20);
    }// </editor-fold>//GEN-END:initComponents

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    public int next(int year) {
        showYear(year + 20);
        return startYear;
    }

    public int back(int year) {
        showYear(year - 20);
        return startYear;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.datechooser.Button cmd1;
    private components.datechooser.Button cmd10;
    private components.datechooser.Button cmd11;
    private components.datechooser.Button cmd12;
    private components.datechooser.Button cmd13;
    private components.datechooser.Button cmd14;
    private components.datechooser.Button cmd15;
    private components.datechooser.Button cmd16;
    private components.datechooser.Button cmd17;
    private components.datechooser.Button cmd18;
    private components.datechooser.Button cmd19;
    private components.datechooser.Button cmd2;
    private components.datechooser.Button cmd20;
    private components.datechooser.Button cmd3;
    private components.datechooser.Button cmd4;
    private components.datechooser.Button cmd5;
    private components.datechooser.Button cmd6;
    private components.datechooser.Button cmd7;
    private components.datechooser.Button cmd8;
    private components.datechooser.Button cmd9;
    // End of variables declaration//GEN-END:variables

}
