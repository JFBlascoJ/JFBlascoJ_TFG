package components.artistchooser;

import assets.objects.Comunes;
import assets.lookandfeel.*;
import POJOs.*;
import components.organisms.tables.TblRider;
import components.atoms.txt.RoundTextArea;
import components.atoms.scrollbar.ScrollBarCustom;
import DAO.DAOArtista;
import java.util.*;
import javax.swing.DefaultListModel;

/**
 * Selector de artists para la interfaz gráfica.
 * @author José Francisco Blasco Jiménez
 */
public class ArtistChooser extends javax.swing.JPanel {
    private DefaultListModel modArtistas;
    private DefaultListModel modEvento;
    private DAOArtista dArt;
    private Evento ev;
    private TblRider rider;
    private RoundTextArea area;
    

    /**
     * Crea un nuevo selector de artistas.
     */
    public ArtistChooser() {
        initComponents();
        ev = new Evento();
        modArtistas = new DefaultListModel();
        modEvento = new DefaultListModel();
        listaArtistas.setModel(modArtistas);
        listaEvento.setModel(modEvento);
    }
    
    /**
     * Setea los objetos comunes que utiliza el selector.
     * @param comunes Objeto de tipo Comunes a asignar
     */
    public void setComunes(Comunes comunes) {
        dArt = comunes.getDAOART();
        resetear();
        setScrollBar();
    }
    
    /**
     * Asigna una barra de scroll personalizada con el look and feel de la aplicación.
     */
    public void setScrollBar() {
        scrollEvento.setVerticalScrollBar(new ScrollBarCustom());
        scrollEvento.setHorizontalScrollBar(new ScrollBarCustom());
        scrollArtistas.setVerticalScrollBar(new ScrollBarCustom());
        scrollArtistas.setHorizontalScrollBar(new ScrollBarCustom());
    }
    
    /**
     * Añade artistas a la lista de artistas del evento, eliminándolos de la lista 
     * de artistas disponibles.
     */
    public void anadeArtistas() {
        Artista aux;
        int[] seleccion = listaArtistas.getSelectedIndices();
        ArrayList<Artista> lista = new ArrayList();
        
        // Si no hay ningún artista seleccionado, añade el primero de la lista
        if (seleccion.length == 0) {
            aux = (Artista) modArtistas.get(0);
            modEvento.addElement(aux);
            modArtistas.removeElement(aux);
            ev.getArtistas().add(aux);
        } else {
            // Se eliminan los artistas de la lista de disponibles y se añaden a los participantes
            for (int i : seleccion) {
                aux = (Artista) modArtistas.getElementAt(i);
                modEvento.addElement(aux);
                modArtistas.removeElement(aux);
                ev.getArtistas().add(aux);
                lista.add(aux);
                
            }
        }
        
        // Se actulizan el rider y las observaciones.
        artistHandler();
    }

    
     /**
     * Elimina artistas de la lista de artistas del evento, añadiéndolos de nuevo a la lista 
     * de artistas disponibles.
     */
    public void eliminaArtistas() {
        Artista aux;
        ArrayList<Artista> lista = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] seleccion = listaEvento.getSelectedIndices();

        // Si no hay ningún artista seleccionado, se eliminan todos los artistas del evento y se añaden a los disponibles
        if (seleccion.length == 0) {
            aux = (Artista) modEvento.get(0);
            modArtistas.addElement(aux);
            modEvento.removeElement(aux);
            ev.getArtistas().remove(aux);
        } else {
            // Se elimina la selección de los participantes y se añade a los disponibles.
            for (int i : seleccion) {
                aux = (Artista) modEvento.getElementAt(i);
                lista.add(aux);
                modArtistas.addElement(aux);
            }  
            
            for (Artista a : lista) {
                modEvento.removeElement(a);
                ev.getArtistas().remove(a);
                
            }
        }
        
        // Se actulizan el rider y las observaciones.
        artistHandler();
    }

    /**
     * Actualiza el rider y las observaciones al añadir o eliminar artistas.
     */
    private void artistHandler() {
        ev.calculaRider();
        rider.actualizaRider(ev.getRider());        
        observacionesHandler();
    }
    
    /**
     * Actualiza las observaciones del evento al añadir o eliminar artistas.
     */
    private void observacionesHandler() {
        StringBuilder sb = new StringBuilder();
        
        // Añade las observaciones del artista al área de texto en cuestión para añadirlas al evento que se está creando/modificando.
        if (area != null) {
            for (Artista a : ev.getArtistas()) {
                // Si el artista no tiene observaciones, no las añade.
                if (a.getObservaciones() != null && !a.getObservaciones().equals("")) {
                    sb.append(a.getNombre() + ":\n");
                    sb.append(a.getObservaciones());
                    sb.append("\n-------------------------------------\n");
                }
            }
            area.setText(sb.toString());
        }
    }
    
    /**
     * Devuelve los artistas seleccionados para añadirlos al evento
     * @return Artistas en la lista de participantes en el evento.
     */
    public ArrayList<Artista> getArtistas() {
        ArrayList<Artista> artistas = new ArrayList();
        for (int i = 0; i < modEvento.size(); i++) {
            artistas.add((Artista) modEvento.get(i));
        }
        if (!artistas.isEmpty())
            return artistas;
        else
            return null;
    }
    
    /**
     * Añade a la lista del evento los artistas de la lista pasada por parámetro y los elimina de la lista de artistas.
     * @param artistas Artistas a añadir al evento.
     */
    public void setArtistas(ArrayList<Artista> artistas) {
        Artista aux;
        modEvento.setSize(0);
        for (Artista a : artistas) {
            modEvento.addElement(a);
            for (int i = 0; i < modArtistas.size(); i++) {
                aux = (Artista) modArtistas.get(i);
                if (a.getId().equals(aux.getId())) {
                    modArtistas.removeElement(aux);
                    break;
                }
            }
        }
    }
    
    /**
     * Devuelve el Selector a su estado por defecto:
     * Vacía la lista de evento y actualiza la de artistas.
     */
    public void resetear() {
        modEvento.clear();
        modArtistas.clear();
        modArtistas.addAll(dArt.consultarTodosSinCero(true));
    }

    public DefaultListModel getModArtistas() {
        return modArtistas;
    }

    public void setModArtistas(DefaultListModel modArtistas) {
        this.modArtistas = modArtistas;
    }

    public DefaultListModel getModEvento() {
        return modEvento;
    }

    public void setModEvento(DefaultListModel modEvento) {
        this.modEvento = modEvento;
    }

    public TblRider getRider() {
        return rider;
    }

    public void setRider(TblRider rider) {
        this.rider = rider;
    }

    public void setArea(RoundTextArea area) {
        this.area = area;
    }
    
    /**
     * Habilita el selectoe para poder modificar un evento.
     */
    public void habilitar() {
        listaEvento.setEnabled(true);
        listaEvento.setBackground(Colores.BLANCO);
        pnlEvento.setBackground(Colores.BLANCO);
        pnlEvento.setBorder(Bordes.GRIS40);
        listaArtistas.setEnabled(true);
        listaArtistas.setBackground(Colores.BLANCO);
        pnlArtistas.setBackground(Colores.BLANCO);
        pnlArtistas.setBorder(Bordes.GRIS40);
    }
    
    /**
     * Deshabilita el selector para que no se pueda modificar el evento.
     */
    public void deshabilitar() {
        listaEvento.setEnabled(false);
        listaEvento.setBackground(Colores.GRISCLARO);
        pnlEvento.setBackground(Colores.GRISCLARO);
        pnlEvento.setBorder(Bordes.GRIS40);
        listaArtistas.setEnabled(false);
        listaArtistas.setBackground(Colores.GRISCLARO);
        pnlArtistas.setBackground(Colores.GRISCLARO);
        pnlArtistas.setBorder(Bordes.GRIS40);
    }
    
    /**
     * Añade/elimina elementos, o actualiza cantidades del rider.
     */
    private void actualizarRider() {
        Set<String> set = ev.getRider().keySet();
        Object[] array;

        int tam = rider.getModel().getRowCount(), cont = 0;
        while (cont < tam) {
            rider.getModel().removeRow(0);
            cont++;
        }
        for (String clave : set) {
            array = new Object[2];
            array[0] = clave;
            array[1] = ev.getRider().get(clave);
            rider.getModel().addRow(array);
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

        pnlArtistas = new components.atoms.RoundPanel();
        scrollArtistas = new javax.swing.JScrollPane();
        listaArtistas = new javax.swing.JList<Artista>();
        botonera = new javax.swing.JPanel();
        flechaDcha = new javax.swing.JLabel();
        flechaIzq = new javax.swing.JLabel();
        pnlEvento = new components.atoms.RoundPanel();
        scrollEvento = new javax.swing.JScrollPane();
        listaEvento = new javax.swing.JList<Artista>();

        setBackground(Colores.TRANSPARENTE);
        setPreferredSize(new java.awt.Dimension(398, 160));

        pnlArtistas.setBackground(Colores.BLANCO);
        pnlArtistas.setBorder(Bordes.AZUL40);
        pnlArtistas.setPreferredSize(new java.awt.Dimension(160, 199));
        pnlArtistas.setRoundBottomLeft(40);
        pnlArtistas.setRoundBottomRight(40);
        pnlArtistas.setRoundTopLeft(40);
        pnlArtistas.setRoundTopRight(40);

        scrollArtistas.setBackground(new java.awt.Color(255, 255, 255));
        scrollArtistas.setBorder(null);
        scrollArtistas.setPreferredSize(new java.awt.Dimension(144, 144));

        listaArtistas.setBackground(new java.awt.Color(255, 255, 255));
        listaArtistas.setBorder(null);
        listaArtistas.setFont(Fuentes.REGULAR);
        listaArtistas.setForeground(Colores.AZUL);
        listaArtistas.setModel(listaArtistas.getModel());
        listaArtistas.setSelectionBackground(Colores.NARANJA);
        scrollArtistas.setViewportView(listaArtistas);

        javax.swing.GroupLayout pnlArtistasLayout = new javax.swing.GroupLayout(pnlArtistas);
        pnlArtistas.setLayout(pnlArtistasLayout);
        pnlArtistasLayout.setHorizontalGroup(
            pnlArtistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlArtistasLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(scrollArtistas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );
        pnlArtistasLayout.setVerticalGroup(
            pnlArtistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlArtistasLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(scrollArtistas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        flechaDcha.setIcon(new javax.swing.ImageIcon("E:\\TFG\\Redimension\\assets\\buttons\\FlechaDchaAzul.png")); // NOI18N
        flechaDcha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        flechaDcha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flechaDchaMouseClicked(evt);
            }
        });

        flechaIzq.setIcon(new javax.swing.ImageIcon("E:\\TFG\\Redimension\\assets\\buttons\\FlechaIzqAzul.png")); // NOI18N
        flechaIzq.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        flechaIzq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flechaIzqMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout botoneraLayout = new javax.swing.GroupLayout(botonera);
        botonera.setLayout(botoneraLayout);
        botoneraLayout.setHorizontalGroup(
            botoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botoneraLayout.createSequentialGroup()
                .addGroup(botoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(flechaDcha)
                    .addComponent(flechaIzq))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        botoneraLayout.setVerticalGroup(
            botoneraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botoneraLayout.createSequentialGroup()
                .addComponent(flechaDcha)
                .addGap(18, 18, 18)
                .addComponent(flechaIzq)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlEvento.setBackground(Colores.BLANCO);
        pnlEvento.setBorder(Bordes.AZUL40);
        pnlEvento.setPreferredSize(new java.awt.Dimension(160, 199));
        pnlEvento.setRoundBottomLeft(40);
        pnlEvento.setRoundBottomRight(40);
        pnlEvento.setRoundTopLeft(40);
        pnlEvento.setRoundTopRight(40);

        scrollEvento.setBackground(new java.awt.Color(255, 255, 255));
        scrollEvento.setBorder(null);
        scrollEvento.setPreferredSize(new java.awt.Dimension(144, 144));

        listaEvento.setBackground(new java.awt.Color(255, 255, 255));
        listaEvento.setBorder(null);
        listaEvento.setFont(Fuentes.REGULAR);
        listaEvento.setForeground(Colores.AZUL);
        listaEvento.setModel(listaArtistas.getModel());
        listaEvento.setSelectionBackground(Colores.NARANJA);
        scrollEvento.setViewportView(listaEvento);

        javax.swing.GroupLayout pnlEventoLayout = new javax.swing.GroupLayout(pnlEvento);
        pnlEvento.setLayout(pnlEventoLayout);
        pnlEventoLayout.setHorizontalGroup(
            pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEventoLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(scrollEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );
        pnlEventoLayout.setVerticalGroup(
            pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEventoLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(scrollEvento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlArtistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(botonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(pnlEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlArtistas, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(botonera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnlEvento, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void flechaDchaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flechaDchaMouseClicked
        anadeArtistas();
    }//GEN-LAST:event_flechaDchaMouseClicked

    private void flechaIzqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flechaIzqMouseClicked
        eliminaArtistas();
    }//GEN-LAST:event_flechaIzqMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel botonera;
    private javax.swing.JLabel flechaDcha;
    private javax.swing.JLabel flechaIzq;
    private javax.swing.JList<Artista> listaArtistas;
    private javax.swing.JList<Artista> listaEvento;
    private components.atoms.RoundPanel pnlArtistas;
    private components.atoms.RoundPanel pnlEvento;
    private javax.swing.JScrollPane scrollArtistas;
    private javax.swing.JScrollPane scrollEvento;
    // End of variables declaration//GEN-END:variables

}
