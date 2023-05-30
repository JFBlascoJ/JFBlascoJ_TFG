package components.organisms.tables;

import components.atoms.tblrenderers.HeaderRendererAzul;
import assets.objects.Comunes;
import assets.lookandfeel.Colores;
import assets.lookandfeel.Fuentes;
import DAO.DAOEvento;
import DAO.DAOArtista;
import DAO.DAOTecnico;
import GUI.Container;
import GUI.Modal;
import POJOs.*;
import components.atoms.scrollbar.ScrollBarCustom;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.event.*;
import java.util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import assets.resources.Operaciones;

/**
 * Tabla para listar las diferentes entidades de la base de datos.
 * @author José Francisco Blasco Jiménez.
 */
public class TblListado extends javax.swing.JPanel {
    private Container container;
    private DAOArtista dArt;
    private DAOEvento dEv;
    private DAOTecnico dTec;
    private DefaultTableModel modFiltro;
    private int tipo;
    private CardLayout card;
    private String clickSelected;
    private Modal modal;
    private Colores colores;
    private Fuentes fuentes;
    private Comunes comunes;

    /**
     * Crea una nueva instancia
     */
    public TblListado() {
        initComponents();
        tabla.getTableHeader().setDefaultRenderer(new HeaderRendererAzul());
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                int row = tabla.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    tabla.setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    tabla.setCursor(Cursor.getDefaultCursor());
                }
            }
        });
        scrollPane.setVerticalScrollBar(new ScrollBarCustom());
    }

    /**
     * Setea los elementos comunes.
     * @param comunes Objeto de tipo Comunes a asignar.
     */
    public void setComunes(Comunes comunes) {
        dArt = comunes.getDAOART();
        dEv = comunes.getDAOEV();
        dTec = comunes.getDAOTEC();
        
    }
    
    /**
     * Rellena la tabla con los eventos existentes.
     */
    public void rellenaEventos() {
        Evento ev;
        Artista a;
        ArrayList<Evento> eventos = dEv.consultarTodos(true);
        Object[] datos;
        StringBuilder sb;

        getModel().setRowCount(0);

        for (Evento evento : eventos) {
            sb = new StringBuilder();
            ev = evento;
            datos = new Object[5];

            datos[0] = false;
            datos[1] = (String) ev.getId();
            datos[2] = (String) ev.getNombre();
            datos[3] = (String) Operaciones.deFechaACadena(ev.getFecha(), true);

            for (int i = 0; i < ev.getArtistas().size(); i++) {
                a = ev.getArtistas().get(i);
                if (i == ev.getArtistas().size() - 1) {
                    sb.append(a.getNombre());
                } else {
                    sb.append(a.getNombre() + ", ");
                }
            }

            datos[4] = (String) sb.toString();

            getModel().addRow(datos);
        }
    }

    /**
     * Rellena la tabla con los técnicos existentes.
     */
    public void rellenaTecnicos() {
        Tecnico t;
        ArrayList<Tecnico> tecnicos = dTec.consultarTodosSinCero(false);
        Object[] datos;
        StringBuilder sb;

        while (getModel().getRowCount() > 0) {
            getModel().removeRow(0);
        }

        for (Tecnico tecnico : tecnicos) {
            sb = new StringBuilder();
            t = tecnico;
            datos = new Object[5];

            datos[0] = false;
            datos[1] = (String) t.getId();
            datos[2] = (String) t.getNombre();
            datos[3] = (Integer) t.getTlf();
            datos[4] = (String) t.getMail();

            getModel().addRow(datos);
        }
    }

    /**
     * Rellena la tabla con los artistas existentes.
     */
    public void rellenaArtistas() {
        Artista a;
        ArrayList<Artista> artistas = dArt.consultarTodosSinCero(false);
        Object[] datos;
        StringBuilder sb;

        getModel().setRowCount(0);

        for (Artista artista : artistas) {
            sb = new StringBuilder();
            a = artista;
            datos = new Object[5];

            datos[0] = (boolean) false;
            datos[1] = (String) a.getId();
            datos[2] = (String) a.getNombre();
            datos[3] = (Integer) a.getTlf();
            datos[4] = (String) a.getMail();

            getModel().addRow(datos);
        }
    }

    /**
     * Setea el tipo de tabla según la entidad con la que se va a trabajar
     * @param tipo tipo de entidad
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
        getModel().setColumnCount(0);

        switch (tipo) {
            case Comunes.TIPO_EVENTO:
                setEventosModel();
                rellenaEventos();
                break;
            case Comunes.TIPO_ARTISTA:
                setArtistasTecnicosModel();
                rellenaArtistas();
                break;
            case Comunes.TIPO_TECNICO:
                setArtistasTecnicosModel();
                rellenaTecnicos();
                break;
        }
    }

    /**
     * Setea el modelo de la tabla para trabajar con eventos
     */
    public void setEventosModel() {
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "", "Id", "Nombre", "Fecha", "Artistas"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });

        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(190);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
            tabla.getColumnModel().getColumn(4).setResizable(false);
        }
    }

    /**
     * Setea el modelo de la tabla para trabajar con artistas o técnicos.
     * El modelo es común porque se muestra la misma información en la tabla.
     */
    public void setArtistasTecnicosModel() {
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "", "Id", "Nombre", "Teléfono", "E-mail"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });

        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(0).setResizable(false);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(1).setResizable(false);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(148);
            tabla.getColumnModel().getColumn(2).setResizable(false);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(148);
            tabla.getColumnModel().getColumn(3).setResizable(false);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(294);
            tabla.getColumnModel().getColumn(4).setResizable(false);
        }
    }

    /**
     * Devuelve los elementos seleccionados
     * @return Mapa con los identificadores y su índice correspondiente.
     */
    public HashMap<String, Integer> getSelected() {
        HashMap<String, Integer> listaIds = new HashMap();

        for (int i = 0; i < tabla.getModel().getRowCount(); i++) {
            if ((boolean) tabla.getValueAt(i, 0)) {
                listaIds.put((String) tabla.getValueAt(i, 1), i);
            }
        }
        return listaIds;
    }

    /**
     * Devuelve el elemento seleccionado.
     * @return Identificador del elemento seleccionado
     */
    public String getClickSelected() {
        if (getModel().getRowCount() != 0) {
            int fila = tabla.getSelectedRow();
            clickSelected = tabla.getValueAt(fila, 1).toString();
        }
        return clickSelected;
    }

    /**
     * Filtra los resultados de la tabla según los criterios del usuario.
     * @param filtro Componentes y su valor para efectuar el filtro.
     * @return True si hay resultados. Flase en caso contrario.
     */
    public boolean filtrar(HashMap<String, Object> filtro) {
        Set keys = filtro.keySet();
        Artista a;
        Tecnico t;
        Object [] datosFila;
        StringBuilder sb;
        boolean flag = false;
        
        switch (tipo) {
            case Comunes.TIPO_EVENTO:
                flag = filtraEventos(filtro);
                break;
            case Comunes.TIPO_ARTISTA:
                flag = filtraArtistas(filtro);
                break;
            case Comunes.TIPO_TECNICO:
                flag = filtraTecnicos(filtro);
                break;                
        }
        
        return flag;
    }
    
    /**
     * Filtra los eventos que coinciden con el criterio de búsqueda.
     * @param filtro Mapa con los componentes y su valor para llevar a cabo el filtro.
     * @return True si hay resultados que coincidan con los criterios. False en caso contrario.
     */
    private boolean filtraEventos(HashMap<String, Object> filtro) {
        Set keys = filtro.keySet();
        Evento ev;
        ArrayList<Evento> eventos = new ArrayList();
        if (keys.contains("id")) {
            ev = dEv.consultarPorId((String) filtro.get("id"));
            if (ev != null) {
                getModel().setRowCount(0);
                getModel().addRow(deEventoAFila(ev));
                return true;
            } else
                return false;
        } else {
            eventos = dEv.filtrar(filtro);
            
            if (eventos.size() > 0) {
                getModel().setRowCount(0);
                for (Evento evento : eventos) {
                    getModel().addRow((deEventoAFila(evento)));
                }
                return true;
            } else
                return false;
        }
    }

    /**
     * Filtra los artistas que coinciden con el criterio de búsqueda.
     * @param filtro Mapa con los componentes y su valor para llevar a cabo el filtro.
     * @return True si hay resultados que coincidan con los criterios. False en caso contrario.
     */
    private boolean filtraArtistas(HashMap<String, Object> filtro) {
        Set keys = filtro.keySet();        
        Artista a;
        ArrayList<Artista> artistas = new ArrayList();
        if (keys.contains("id")) {
            a = dArt.consultarPorId((String) filtro.get("id"));
            if (a != null) {
                getModel().setRowCount(0);
                getModel().addRow(deArtistaOTecnicoAFila(a));
                return true;
            } else
                return false;
        } else {
            artistas = dArt.filtrar(filtro);
            
            if (artistas.size() > 0) {
                getModel().setRowCount(0);
                for (Artista artista : artistas) {
                    getModel().addRow((deArtistaOTecnicoAFila(artista)));
                }
                return true;
            } else
                return false;
        }
    }

    /**
     * Filtra los técnicos que coinciden con el criterio de búsqueda.
     * @param filtro Mapa con los componentes y su valor para llevar a cabo el filtro.
     * @return True si hay resultados que coincidan con los criterios. False en caso contrario.
     */
    private boolean filtraTecnicos(HashMap<String, Object> filtro) {
        Set keys = filtro.keySet();
        Tecnico t;
        ArrayList<Tecnico> tecnicos = new ArrayList();
        if (keys.contains("id")) {
            t = dTec.consultarPorId((String) filtro.get("id"));
            if (t != null) {
                getModel().setRowCount(0);
                getModel().addRow(deArtistaOTecnicoAFila(t));
                return true;
            } else
                return false;
        } else {
            tecnicos = dTec.filtrar(filtro);
            
            if (tecnicos.size() > 0) {
                getModel().setRowCount(0);
                for (Tecnico tecnico : tecnicos) {
                    getModel().addRow((deArtistaOTecnicoAFila(tecnico)));
                }
                return true;
            } else
                return false;
        }
    }

    /**
     * convierte un evento en un array con los datos necesarios para mostrarse en la tabla.
     * @param ev Evento a convertir.
     * @return Array de objetos con los datos del evento.
     */
    private Object[] deEventoAFila(Evento ev) {
        Object[] datosFila = new Object[getModel().getColumnCount()];
        StringBuilder sb = new StringBuilder();
        datosFila[0] = false;
        datosFila[1] = ev.getId();
        datosFila[2] = ev.getNombre();
        datosFila[3] = ev.getFecha();
        for (int i = 0; i < ev.getArtistas().size(); i++) {
            sb.append(ev.getArtistas().get(i));
            if (i == ev.getArtistas().size())
                sb.append(", ");
            else
                sb.append(".");
            
        }
        datosFila[4] = sb.toString();
        
        return datosFila;
    }

    /**
     * convierte un artista o un evento en un array con los datos necesarios para mostrarse en la tabla.
     * @param o Artista o técnico a convertir.
     * @return Array de objetos con los datos del artista/técnico.
     */
    private Object[] deArtistaOTecnicoAFila(Object o) {
        Object[] datosFila = new Object[getModel().getColumnCount()];
        if (o instanceof Artista) {
            Artista a = (Artista) o;
            datosFila[0] = (boolean) false;
            datosFila[1] = (String) a.getId();
            datosFila[2] = (String) a.getNombre();
            datosFila[3] = (Integer) a.getTlf();
            datosFila[4] = (String) a.getMail();
        } else {
            Tecnico t = (Tecnico) o;
            datosFila[0] = false;
            datosFila[1] = t.getId();
            datosFila[2] = t.getNombre();
            datosFila[3] = t.getTlf();
            datosFila[4] = t.getMail();
        }
        
        return datosFila;
    }

    /**
     * Recarga la tabla con nuevos datos.
     * @param datos Datos a mostrar.
     */
    public void setData(ArrayList<Object[]> datos) {
        Object[] aux;
        getModel().setRowCount(0);

        for (Object[] dato : datos) {
            getModel().addRow(dato);
        }

    }

    /**
     * Obtiene el modelo de la tabla
     * @return Modelo de la tabla
     */
    public DefaultTableModel getModel() {
        return (DefaultTableModel) tabla.getModel();
    }

    /**
     * Obtiene el tipo de entidad con el que se trabaja en la tabla.
     * @return Tipo de entidad.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Setea el CardLayout de la tabla.
     * @param card CardLayout a asignar.
     */
    public void setCard(CardLayout card) {
        this.card = card;
    }

    /**
     * Asigna el contenedor en el que se encuentra la vista.
     * @param container Contenedor a asignar.
     */
    public void setContainer(Container container) {
        this.container = container;
    }
      

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTabla = new components.atoms.RoundPanel();
        scrollPane = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setBackground(Colores.TRANSPARENTE);
        setPreferredSize(new java.awt.Dimension(700, 200));

        pnlTabla.setBackground(Colores.AZUL);
        pnlTabla.setPreferredSize(new java.awt.Dimension(700, 280));
        pnlTabla.setRoundBottomLeft(40);
        pnlTabla.setRoundBottomRight(40);
        pnlTabla.setRoundTopLeft(40);
        pnlTabla.setRoundTopRight(40);

        scrollPane.setBackground(Colores.TRANSPARENTE);
        scrollPane.setBorder(null);

        tabla.setFont(Fuentes.REGULAR);
        tabla.setForeground(Colores.AZUL);
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Id", "Nombre", "Técnico", "Artistas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabla.setRowHeight(30);
        tabla.setSelectionBackground(Colores.NARANJA);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(148);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(148);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(296);
        }

        javax.swing.GroupLayout pnlTablaLayout = new javax.swing.GroupLayout(pnlTabla);
        pnlTabla.setLayout(pnlTablaLayout);
        pnlTablaLayout.setHorizontalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTablaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        pnlTablaLayout.setVerticalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Cuanto se pulsa en un resultado de la tabla, se abre la vista "Resultado" con los datos de la entidad en cuestión
     * @param evt Evento de ratón.
     */
    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (getModel().getRowCount() != 0) {
            int columna = tabla.columnAtPoint(evt.getPoint());
            
            if (columna > 0) {
                int fila = tabla.getSelectedRow();
                clickSelected = tabla.getValueAt(fila, 1).toString();
                switch (tipo) {
                    case Comunes.TIPO_EVENTO:
                        Evento e = dEv.consultarPorId(clickSelected); 
                        container.getEvtResul().setEv(e);
                        card.show(container, "evtResul");
                        container.getFrame().setTitle("Datos de evento: " + e.getId());
                        break;
                    case Comunes.TIPO_ARTISTA:
                        Artista a = dArt.consultarPorId(clickSelected);
                        container.getArtResul().setArtista(a);
                        card.show(container, "artResul");
                        container.getFrame().setTitle("Datos de artista: " + a.getId());
                        break;
                    case Comunes.TIPO_TECNICO:
                        Tecnico t = dTec.consultarPorId(clickSelected);
                        container.getTecResul().setTec(t);
                        card.show(container, "tecResul");
                        container.getFrame().setTitle("Datos de técnico: " + t.getId());
                        break;
                }
            }
        }
    }//GEN-LAST:event_tablaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.atoms.RoundPanel pnlTabla;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

}
