package components.organisms.tables;

import components.atoms.tblrenderers.HeaderRendererAzul;
import components.atoms.tblrenderers.HeaderRendererNaranja;
import assets.objects.Comunes;
import assets.lookandfeel.Colores;
import assets.lookandfeel.Bordes;
import assets.lookandfeel.Fuentes;
import components.atoms.Label;
import components.atoms.scrollbar.ScrollBarCustom;
import java.util.*;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 * Tabla para operar con el rider de un artista o de un evento.
 * @author José Francisco Blasco Jiménez
 */
public class TblRider extends javax.swing.JPanel {

    private int tipo;
    private boolean requerido;
    private Label label;
    private HeaderRendererAzul headerAzul;
    private HeaderRendererNaranja headerNaranja;

    /**
     * Crea una nueva instancia
     */
    public TblRider() {
        initComponents();
        headerAzul = new HeaderRendererAzul();
        headerNaranja = new HeaderRendererNaranja();
        scrollRider.setVerticalScrollBar(new ScrollBarCustom());
    }
    
    /**
     * Setea los objetos comunes.
     * @param comunes 
     */
    public void setComunes(Comunes comunes) {
        setLookAndFeel();
    }

    /**
     * Setea el look and feel de la tabla.
     */
    public void setLookAndFeel() {
        scrollRider.setVerticalScrollBar(new ScrollBarCustom());
        setBackground(Colores.TRANSPARENTE);
        pnlRider.setBackground(Colores.AZUL);
        pnlRider.setBorder(Bordes.AZUL40);
        scrollRider.setBackground(Colores.TRANSPARENTE);
        tabla.setForeground(Colores.AZUL);
    }
    
    /**
     * Comprieba si la tabla es un campo requerido o no
     * @return True si es requerido. False en caso contrario.
     */
    public boolean isRequerido() {
        return requerido;
    }

    /**
     * Setea si la tabla es requerida o no.
     * @param requerido True si es requerido. False en caso contrario.
     */
    public void setRequerido(boolean requerido) {
        this.requerido = requerido;
    }

    /**
     * Devuelve la tabla del componente.
     * @return Tabla del componente.
     */
    public JTable getTabla() {
        return tabla;
    }

    /**
     * Asigna un Label a la tabla, para marcarla en caso de ser requerida.
     * @param label 
     */
    public void setLabel(Label label) {
        this.label = label;
    }

    /**
     * Setea si la tabla es para un artista o para un evento.
     * Si es para un artista, se mostrará la columna "suma".
     * Si es para un evento, esa columna no aparecerá.
     * @param tipo 
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
        getModel().setRowCount(0);

        switch (tipo) {
            case Comunes.TIPO_EVENTO:
                setEventoModel();
                break;
            case Comunes.TIPO_ARTISTA:
                setArtistaModel();
                setRequerido(true);
                coloreaComponente(getModel().getRowCount() == 0 ? true : false);
                getTabla().putClientProperty("rider", this);
                getModel().addTableModelListener(new TableModelListener() {
                    @Override
                    public void tableChanged(TableModelEvent e) {
                        int row = e.getFirstRow();
                        int column = e.getColumn();
                        if (column == 1) {
                            int cantidad = (int) getModel().getValueAt(row, column);
                            if (cantidad == 0)
                                getModel().removeRow(row);
                        }
                        boolean resaltar = getModel().getRowCount() < 1;
                        coloreaComponente(resaltar);
                    }
                });
                break;
        }
    }

    /**
     * Colorea el componente en función de si hay que resaltarlo o no.
     * @param resaltar True si la tabla está vacía. False en caso contrario.
     */
    public void coloreaComponente(boolean resaltar) {
        if (resaltar) {
            label.setForeground(Colores.NARANJA);
            pnlRider.setBackground(Colores.NARANJA);
            pnlRider.setBorder(Bordes.NARANJA40);
            tabla.getTableHeader().setDefaultRenderer(headerNaranja);
        } else {
            label.setForeground(Colores.AZUL);
            pnlRider.setBackground(Colores.AZUL);
            pnlRider.setBorder(Bordes.AZUL40);
            tabla.getTableHeader().setDefaultRenderer(headerAzul);
        }
    }

    /**
     * Setea el modelo para trabajar con el rider de un evento (sin la columna "Suma").
     */
    public void setEventoModel() {
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Elemento", "Cantidad"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });

        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(310);
            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(1).setResizable(false);
        }

        tabla.getTableHeader().setDefaultRenderer(headerAzul);
        tabla.setEnabled(false);
    }

    /**
     * Setea el modelo para trabajar con el rider de un artista (con la columna "Suma").
     */
    public void setArtistaModel() {
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Elemento", "Cantidad", "Suma"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });

        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(290);
            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(2).setResizable(false);
        }
    }

    /**
     * Obtiene el modelo de la tabla.
     * @return Modelo de la tabla en cuestión.
     */
    public DefaultTableModel getModel() {
        return (DefaultTableModel) tabla.getModel();
    }

    public ArrayList<Object[]> getRider() {
        Object[] aux;
        ArrayList<Object[]> rider = new ArrayList();
        Integer cantidad;

        for (int i = 0; i < getModel().getRowCount(); i++) {
            cantidad = (Integer) getModel().getValueAt(i, 1);
            if (cantidad > 0) {
                aux = new Object[3];
                for (int j = 0; j < aux.length; j++) {
                    aux[j] = getModel().getValueAt(i, j);
                }
                rider.add(aux);
            }
        }

        if (!rider.isEmpty()) {
            return rider;
        } else {
            return null;
        }
    }

    /**
     * Elimina todo el contenido de la tabla.
     */
    public void resetear() {
        getModel().setRowCount(0);
    }

    /**
     * actualiza el rider de un evento.
     * @param rider Mapa con los conjuntos clave-valor (elemento-cantidad) para actualizar el rider.
     */
    public void actualizaRider(HashMap<String, Integer> rider) {
        Set<String> set = rider.keySet();
        Object[] array;

        getModel().setRowCount(0);
        for (String clave : set) {
            array = new Object[2];
            array[0] = clave;
            array[1] = rider.get(clave);
            getModel().addRow(array);
        }
    }

    /**
     * Carga el rider pasado por parámetro.
     * @param elementos Lista con los elementos del rider a asignar.
     */
    public void asignaRider(ArrayList<Object[]> elementos) {
        resetear();

        for (Object[] el : elementos) {
            getModel().addRow(el);
        }
    }

    /**
     * Habilita la tabla para poder editar los datos.
     */
    public void habilitar() {
        tabla.setEnabled(true);
    }

    /**
     * Deshabilita la tabla para que no se pueda editar.
     */
    public void deshabilitar() {
        tabla.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRider = new components.atoms.RoundPanel();
        scrollRider = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setBackground(Colores.TRANSPARENTE);
        setPreferredSize(new java.awt.Dimension(493, 207));

        pnlRider.setBackground(Colores.AZUL);
        pnlRider.setPreferredSize(new java.awt.Dimension(371, 200));
        pnlRider.setRoundBottomLeft(40);
        pnlRider.setRoundBottomRight(40);
        pnlRider.setRoundTopLeft(40);
        pnlRider.setRoundTopRight(40);

        scrollRider.setBackground(Colores.TRANSPARENTE);
        scrollRider.setBorder(null);
        scrollRider.setPreferredSize(new java.awt.Dimension(450, 200));

        tabla.setFont(Fuentes.REGULAR);
        tabla.setForeground(Colores.AZUL);
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Elemento", "Cantidad", "Suma"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabla.setPreferredSize(new java.awt.Dimension(380, 200));
        tabla.setRowHeight(30);
        tabla.setSelectionBackground(Colores.NARANJA);
        scrollRider.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(30);
        }

        javax.swing.GroupLayout pnlRiderLayout = new javax.swing.GroupLayout(pnlRider);
        pnlRider.setLayout(pnlRiderLayout);
        pnlRiderLayout.setHorizontalGroup(
            pnlRiderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRiderLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(scrollRider, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        pnlRiderLayout.setVerticalGroup(
            pnlRiderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRiderLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(scrollRider, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRider, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.atoms.RoundPanel pnlRider;
    private javax.swing.JScrollPane scrollRider;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

}
