package components.datechooser;

import assets.lookandfeel.Colores;
import assets.lookandfeel.Fuentes;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;

public final class Dates extends javax.swing.JPanel {

    private Event event;
    private final int MONTH;
    private final int YEAR;
    private final int DAY;
    private int m;
    private int y;
    private int selectDay = 0;
    private int startDate;
    private int max_of_month;

    public Dates() {
        initComponents();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String toDay = df.format(date);
        DAY = Integer.valueOf(toDay.split("-")[0]);
        MONTH = Integer.valueOf(toDay.split("-")[1]);
        YEAR = Integer.valueOf(toDay.split("-")[2]);
    }
    
    public void showDate(int month, int year, SelectedDate select) {
        m = month;
        y = year;
        // selectDay = 0;
        Calendar cd = Calendar.getInstance();
        cd.set(year, month - 1, 1);
        int start = cd.get(Calendar.DAY_OF_WEEK);
        max_of_month = cd.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (start == 1) {
            start += 7;
        }
        clear();
        start += 5;
        startDate = start;
        for (int i = 1; i <= max_of_month; i++) {
            Button cmd = (Button) getComponent(start);
            cmd.setColorSelected(getForeground());
            cmd.setText(i + "");
            if (i == DAY && month == MONTH && year == YEAR) {
                cmd.setBackground(Colores.TRANSPARENTE);
            } else {
                cmd.setBackground(Colores.TRANSPARENTE);
            }
            if (i == select.getDay() && month == select.getMonth() && year == select.getYear()) {
                cmd.setBackground(getForeground());
                cmd.setForeground(new Color(255, 255, 255));
            }
            start++;
        }
    }

    private void clear() {
        for (int i = 7; i < getComponentCount(); i++) {
            ((JButton) getComponent(i)).setText("");
        }
    }

    public void clearSelected() {
        for (int i = 7; i < getComponentCount(); i++) {
            JButton cmd = (JButton) getComponent(i);
            if (MONTH == m && y == YEAR && !cmd.getText().equals("") && Integer.valueOf(cmd.getText()) == DAY) {
                cmd.setBackground(Colores.TRANSPARENTE);
                cmd.setForeground(new java.awt.Color(75, 75, 75));
            } else {
                cmd.setBackground(Colores.TRANSPARENTE);
                cmd.setForeground(new java.awt.Color(75, 75, 75));
            }
        }
        selectDay = 0;
    }

    private void addEvent() {
        for (int i = 7; i < getComponentCount(); i++) {
            ((Button) getComponent(i)).setEvent(event);
        }
    }

    public void setSelected(int index) {
        selectDay = index;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdMo = new components.datechooser.Button();
        cmdTu = new components.datechooser.Button();
        cmdWe = new components.datechooser.Button();
        cmdTh = new components.datechooser.Button();
        cmdFr = new components.datechooser.Button();
        cmdSa = new components.datechooser.Button();
        cmdSu = new components.datechooser.Button();
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
        cmd21 = new components.datechooser.Button();
        cmd22 = new components.datechooser.Button();
        cmd23 = new components.datechooser.Button();
        cmd24 = new components.datechooser.Button();
        cmd25 = new components.datechooser.Button();
        cmd26 = new components.datechooser.Button();
        cmd27 = new components.datechooser.Button();
        cmd28 = new components.datechooser.Button();
        cmd29 = new components.datechooser.Button();
        cmd30 = new components.datechooser.Button();
        cmd31 = new components.datechooser.Button();
        cmd32 = new components.datechooser.Button();
        cmd33 = new components.datechooser.Button();
        cmd34 = new components.datechooser.Button();
        cmd35 = new components.datechooser.Button();
        cmd36 = new components.datechooser.Button();
        cmd37 = new components.datechooser.Button();
        cmd38 = new components.datechooser.Button();
        cmd39 = new components.datechooser.Button();
        cmd40 = new components.datechooser.Button();
        cmd41 = new components.datechooser.Button();
        cmd42 = new components.datechooser.Button();

        setBackground(Colores.GRISCLARO);
        setLayout(new java.awt.GridLayout(7, 7));

        cmdMo.setBackground(Colores.TRANSPARENTE);
        cmdMo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdMo.setForeground(Colores.AZUL);
        cmdMo.setText("Mo");
        cmdMo.setFont(Fuentes.LABEL);
        add(cmdMo);

        cmdTu.setBackground(Colores.TRANSPARENTE);
        cmdTu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdTu.setForeground(Colores.AZUL);
        cmdTu.setText("Tu");
        cmdTu.setFont(Fuentes.LABEL);
        add(cmdTu);

        cmdWe.setBackground(Colores.TRANSPARENTE);
        cmdWe.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdWe.setForeground(Colores.AZUL);
        cmdWe.setText("We");
        cmdWe.setFont(Fuentes.LABEL);
        add(cmdWe);

        cmdTh.setBackground(Colores.TRANSPARENTE);
        cmdTh.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdTh.setForeground(Colores.AZUL);
        cmdTh.setText("Th");
        cmdTh.setFont(Fuentes.LABEL);
        add(cmdTh);

        cmdFr.setBackground(Colores.TRANSPARENTE);
        cmdFr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdFr.setForeground(Colores.AZUL);
        cmdFr.setText("Fr");
        cmdFr.setFont(Fuentes.LABEL);
        add(cmdFr);

        cmdSa.setBackground(Colores.TRANSPARENTE);
        cmdSa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdSa.setForeground(Colores.AZUL);
        cmdSa.setText("Sa");
        cmdSa.setFont(Fuentes.LABEL);
        add(cmdSa);

        cmdSu.setBackground(Colores.TRANSPARENTE);
        cmdSu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 5, 1));
        cmdSu.setForeground(Colores.NARANJA);
        cmdSu.setText("Su");
        cmdSu.setFont(Fuentes.LABEL);
        add(cmdSu);

        cmd1.setBackground(Colores.TRANSPARENTE);
        cmd1.setForeground(Colores.AZUL);
        cmd1.setFont(Fuentes.LABEL);
        cmd1.setName("day"); // NOI18N
        add(cmd1);

        cmd2.setBackground(Colores.TRANSPARENTE);
        cmd2.setForeground(Colores.AZUL);
        cmd2.setFont(Fuentes.LABEL);
        cmd2.setName("day"); // NOI18N
        add(cmd2);

        cmd3.setBackground(Colores.TRANSPARENTE);
        cmd3.setForeground(Colores.AZUL);
        cmd3.setText("1");
        cmd3.setFont(Fuentes.LABEL);
        cmd3.setName("day"); // NOI18N
        add(cmd3);

        cmd4.setBackground(Colores.TRANSPARENTE);
        cmd4.setForeground(Colores.AZUL);
        cmd4.setText("2");
        cmd4.setFont(Fuentes.LABEL);
        cmd4.setName("day"); // NOI18N
        add(cmd4);

        cmd5.setBackground(Colores.TRANSPARENTE);
        cmd5.setForeground(Colores.AZUL);
        cmd5.setText("3");
        cmd5.setFont(Fuentes.LABEL);
        cmd5.setName("day"); // NOI18N
        add(cmd5);

        cmd6.setBackground(Colores.TRANSPARENTE);
        cmd6.setForeground(Colores.AZUL);
        cmd6.setText("4");
        cmd6.setFont(Fuentes.LABEL);
        cmd6.setName("day"); // NOI18N
        add(cmd6);

        cmd7.setBackground(Colores.TRANSPARENTE);
        cmd7.setForeground(Colores.AZUL);
        cmd7.setText("5");
        cmd7.setFont(Fuentes.LABEL);
        cmd7.setName("day"); // NOI18N
        add(cmd7);

        cmd8.setBackground(Colores.TRANSPARENTE);
        cmd8.setForeground(Colores.AZUL);
        cmd8.setText("6");
        cmd8.setFont(Fuentes.LABEL);
        cmd8.setName("day"); // NOI18N
        add(cmd8);

        cmd9.setBackground(Colores.TRANSPARENTE);
        cmd9.setForeground(Colores.AZUL);
        cmd9.setText("7");
        cmd9.setFont(Fuentes.LABEL);
        cmd9.setName("day"); // NOI18N
        add(cmd9);

        cmd10.setBackground(Colores.TRANSPARENTE);
        cmd10.setForeground(Colores.AZUL);
        cmd10.setText("8");
        cmd10.setFont(Fuentes.LABEL);
        cmd10.setName("day"); // NOI18N
        add(cmd10);

        cmd11.setBackground(Colores.TRANSPARENTE);
        cmd11.setForeground(Colores.AZUL);
        cmd11.setText("9");
        cmd11.setFont(Fuentes.LABEL);
        cmd11.setName("day"); // NOI18N
        add(cmd11);

        cmd12.setBackground(Colores.TRANSPARENTE);
        cmd12.setForeground(Colores.AZUL);
        cmd12.setText("10");
        cmd12.setFont(Fuentes.LABEL);
        cmd12.setName("day"); // NOI18N
        add(cmd12);

        cmd13.setBackground(Colores.TRANSPARENTE);
        cmd13.setForeground(Colores.AZUL);
        cmd13.setText("11");
        cmd13.setFont(Fuentes.LABEL);
        cmd13.setName("day"); // NOI18N
        add(cmd13);

        cmd14.setBackground(Colores.TRANSPARENTE);
        cmd14.setForeground(Colores.AZUL);
        cmd14.setText("12");
        cmd14.setFont(Fuentes.LABEL);
        cmd14.setName("day"); // NOI18N
        add(cmd14);

        cmd15.setBackground(Colores.TRANSPARENTE);
        cmd15.setForeground(Colores.AZUL);
        cmd15.setText("13");
        cmd15.setFont(Fuentes.LABEL);
        cmd15.setName("day"); // NOI18N
        add(cmd15);

        cmd16.setBackground(Colores.TRANSPARENTE);
        cmd16.setForeground(Colores.AZUL);
        cmd16.setText("14");
        cmd16.setFont(Fuentes.LABEL);
        cmd16.setName("day"); // NOI18N
        add(cmd16);

        cmd17.setBackground(Colores.TRANSPARENTE);
        cmd17.setForeground(Colores.AZUL);
        cmd17.setText("15");
        cmd17.setFont(Fuentes.LABEL);
        cmd17.setName("day"); // NOI18N
        add(cmd17);

        cmd18.setBackground(Colores.TRANSPARENTE);
        cmd18.setForeground(Colores.AZUL);
        cmd18.setText("16");
        cmd18.setFont(Fuentes.LABEL);
        cmd18.setName("day"); // NOI18N
        add(cmd18);

        cmd19.setBackground(Colores.TRANSPARENTE);
        cmd19.setForeground(Colores.AZUL);
        cmd19.setText("17");
        cmd19.setFont(Fuentes.LABEL);
        cmd19.setName("day"); // NOI18N
        add(cmd19);

        cmd20.setBackground(Colores.TRANSPARENTE);
        cmd20.setForeground(Colores.AZUL);
        cmd20.setText("18");
        cmd20.setFont(Fuentes.LABEL);
        cmd20.setName("day"); // NOI18N
        add(cmd20);

        cmd21.setBackground(Colores.TRANSPARENTE);
        cmd21.setForeground(Colores.AZUL);
        cmd21.setText("19");
        cmd21.setFont(Fuentes.LABEL);
        cmd21.setName("day"); // NOI18N
        add(cmd21);

        cmd22.setBackground(Colores.TRANSPARENTE);
        cmd22.setForeground(Colores.AZUL);
        cmd22.setText("20");
        cmd22.setFont(Fuentes.LABEL);
        cmd22.setName("day"); // NOI18N
        add(cmd22);

        cmd23.setBackground(Colores.TRANSPARENTE);
        cmd23.setForeground(Colores.AZUL);
        cmd23.setText("21");
        cmd23.setFont(Fuentes.LABEL);
        cmd23.setName("day"); // NOI18N
        add(cmd23);

        cmd24.setBackground(Colores.TRANSPARENTE);
        cmd24.setForeground(Colores.AZUL);
        cmd24.setText("22");
        cmd24.setFont(Fuentes.LABEL);
        cmd24.setName("day"); // NOI18N
        add(cmd24);

        cmd25.setBackground(Colores.TRANSPARENTE);
        cmd25.setForeground(Colores.AZUL);
        cmd25.setText("23");
        cmd25.setFont(Fuentes.LABEL);
        cmd25.setName("day"); // NOI18N
        add(cmd25);

        cmd26.setBackground(Colores.TRANSPARENTE);
        cmd26.setForeground(Colores.AZUL);
        cmd26.setText("24");
        cmd26.setFont(Fuentes.LABEL);
        cmd26.setName("day"); // NOI18N
        add(cmd26);

        cmd27.setBackground(Colores.TRANSPARENTE);
        cmd27.setForeground(Colores.AZUL);
        cmd27.setText("25");
        cmd27.setFont(Fuentes.LABEL);
        cmd27.setName("day"); // NOI18N
        add(cmd27);

        cmd28.setBackground(Colores.TRANSPARENTE);
        cmd28.setForeground(Colores.AZUL);
        cmd28.setText("26");
        cmd28.setFont(Fuentes.LABEL);
        cmd28.setName("day"); // NOI18N
        add(cmd28);

        cmd29.setBackground(Colores.TRANSPARENTE);
        cmd29.setForeground(Colores.AZUL);
        cmd29.setText("27");
        cmd29.setFont(Fuentes.LABEL);
        cmd29.setName("day"); // NOI18N
        add(cmd29);

        cmd30.setBackground(Colores.TRANSPARENTE);
        cmd30.setForeground(Colores.AZUL);
        cmd30.setText("28");
        cmd30.setFont(Fuentes.LABEL);
        cmd30.setName("day"); // NOI18N
        add(cmd30);

        cmd31.setBackground(Colores.TRANSPARENTE);
        cmd31.setForeground(Colores.AZUL);
        cmd31.setText("29");
        cmd31.setFont(Fuentes.LABEL);
        cmd31.setName("day"); // NOI18N
        add(cmd31);

        cmd32.setBackground(Colores.TRANSPARENTE);
        cmd32.setForeground(Colores.AZUL);
        cmd32.setText("30");
        cmd32.setFont(Fuentes.LABEL);
        cmd32.setName("day"); // NOI18N
        add(cmd32);

        cmd33.setBackground(Colores.TRANSPARENTE);
        cmd33.setForeground(Colores.AZUL);
        cmd33.setText("31");
        cmd33.setFont(Fuentes.LABEL);
        cmd33.setName("day"); // NOI18N
        add(cmd33);

        cmd34.setBackground(Colores.TRANSPARENTE);
        cmd34.setForeground(Colores.AZUL);
        cmd34.setFont(Fuentes.LABEL);
        cmd34.setName("day"); // NOI18N
        add(cmd34);

        cmd35.setBackground(Colores.TRANSPARENTE);
        cmd35.setForeground(Colores.AZUL);
        cmd35.setFont(Fuentes.LABEL);
        cmd35.setName("day"); // NOI18N
        add(cmd35);

        cmd36.setBackground(Colores.TRANSPARENTE);
        cmd36.setForeground(Colores.AZUL);
        cmd36.setFont(Fuentes.LABEL);
        cmd36.setName("day"); // NOI18N
        add(cmd36);

        cmd37.setBackground(Colores.TRANSPARENTE);
        cmd37.setForeground(Colores.AZUL);
        cmd37.setFont(Fuentes.LABEL);
        cmd37.setName("day"); // NOI18N
        add(cmd37);

        cmd38.setBackground(Colores.TRANSPARENTE);
        cmd38.setForeground(Colores.AZUL);
        cmd38.setFont(Fuentes.LABEL);
        cmd38.setName("day"); // NOI18N
        add(cmd38);

        cmd39.setBackground(Colores.TRANSPARENTE);
        cmd39.setForeground(Colores.AZUL);
        cmd39.setFont(Fuentes.LABEL);
        cmd39.setName("day"); // NOI18N
        add(cmd39);

        cmd40.setBackground(Colores.TRANSPARENTE);
        cmd40.setForeground(Colores.AZUL);
        cmd40.setFont(Fuentes.LABEL);
        cmd40.setName("day"); // NOI18N
        add(cmd40);

        cmd41.setBackground(Colores.TRANSPARENTE);
        cmd41.setForeground(Colores.AZUL);
        cmd41.setFont(Fuentes.LABEL);
        cmd41.setName("day"); // NOI18N
        add(cmd41);

        cmd42.setBackground(Colores.TRANSPARENTE);
        cmd42.setForeground(Colores.AZUL);
        cmd42.setFont(Fuentes.LABEL);
        cmd42.setName("day"); // NOI18N
        add(cmd42);
    }// </editor-fold>//GEN-END:initComponents

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
    private components.datechooser.Button cmd13;
    private components.datechooser.Button cmd14;
    private components.datechooser.Button cmd15;
    private components.datechooser.Button cmd16;
    private components.datechooser.Button cmd17;
    private components.datechooser.Button cmd18;
    private components.datechooser.Button cmd19;
    private components.datechooser.Button cmd2;
    private components.datechooser.Button cmd20;
    private components.datechooser.Button cmd21;
    private components.datechooser.Button cmd22;
    private components.datechooser.Button cmd23;
    private components.datechooser.Button cmd24;
    private components.datechooser.Button cmd25;
    private components.datechooser.Button cmd26;
    private components.datechooser.Button cmd27;
    private components.datechooser.Button cmd28;
    private components.datechooser.Button cmd29;
    private components.datechooser.Button cmd3;
    private components.datechooser.Button cmd30;
    private components.datechooser.Button cmd31;
    private components.datechooser.Button cmd32;
    private components.datechooser.Button cmd33;
    private components.datechooser.Button cmd34;
    private components.datechooser.Button cmd35;
    private components.datechooser.Button cmd36;
    private components.datechooser.Button cmd37;
    private components.datechooser.Button cmd38;
    private components.datechooser.Button cmd39;
    private components.datechooser.Button cmd4;
    private components.datechooser.Button cmd40;
    private components.datechooser.Button cmd41;
    private components.datechooser.Button cmd42;
    private components.datechooser.Button cmd5;
    private components.datechooser.Button cmd6;
    private components.datechooser.Button cmd7;
    private components.datechooser.Button cmd8;
    private components.datechooser.Button cmd9;
    private components.datechooser.Button cmdFr;
    private components.datechooser.Button cmdMo;
    private components.datechooser.Button cmdSa;
    private components.datechooser.Button cmdSu;
    private components.datechooser.Button cmdTh;
    private components.datechooser.Button cmdTu;
    private components.datechooser.Button cmdWe;
    // End of variables declaration//GEN-END:variables

    public void next() {
        if (selectDay == max_of_month) {
            selectDay = 0;
        }
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay + 1);
        String n = cmd.getText();
        if (!n.equals("") && Integer.valueOf(n) <= max_of_month) {
            selectDay++;
            event.execute(null, selectDay);
            cmd.setBackground(new Color(206, 110, 245));
        }
    }

    public void back() {
        if (selectDay <= 1) {
            selectDay = max_of_month + 1;
        }
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay - 1);
        String n = cmd.getText();
        if (!n.equals("") && cmd.getName() != null) {
            selectDay--;
            event.execute(null, selectDay);
            cmd.setBackground(Colores.TRANSPARENTE);
        }
    }

    public void up() {
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay - 7);
        String n = cmd.getText();
        if (!n.equals("") && cmd.getName() != null) {
            selectDay -= 7;
            event.execute(null, selectDay);
            cmd.setBackground(Colores.TRANSPARENTE);
        }
    }

    public void down() {
        if (getComponents().length > startDate - 1 + selectDay + 7) {
            JButton cmd = (JButton) getComponent(startDate - 1 + selectDay + 7);
            String n = cmd.getText();
            if (!n.equals("") && cmd.getName() != null) {
                selectDay += 7;
                event.execute(null, selectDay);
                cmd.setBackground(Colores.TRANSPARENTE);
            }
        }
    }

}
