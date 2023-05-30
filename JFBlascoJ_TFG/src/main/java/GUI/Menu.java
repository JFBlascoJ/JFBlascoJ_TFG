package GUI;

import assets.lookandfeel.Colores;
import assets.lookandfeel.Iconos;
import assets.objects.Comunes;
import components.atoms.RoundTextLabel;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Menú para la interfaz gráfica de la aplicación.
 *
 * @author José Francisco Blasco Jiménez
 */
public class Menu extends javax.swing.JPanel {

    private JFrame main;
    private CardLayout card;
    private Container container;
    private JPanel visible;
    private Interfaz frame;
    private RoundTextLabel selected, parentSelected;

    /**
     * Crea una nueva instancia
     */
    public Menu() {
        initComponents();
        selected = null;
        visible = null;
        pnlEventos.setVisible(false);
        pnlArtistas.setVisible(false);
        pnlTecnicos.setVisible(false);
        lblArtistas.setSubMenu(false);
        lblEventos.setSubMenu(false);
        lblTecList.setSubMenu(false);
        lblArtNuevo.setSubMenu(true);
        lblArtList.setSubMenu(true);
        lblEvtNuevo.setSubMenu(true);
        lblEvtList.setSubMenu(true);
        lblTecNuevo.setSubMenu(true);
        lblTecList.setSubMenu(true);
    }

    /**
     * Setea el CardLayout, necesario para la navegación entre las diferentes
     * vistas de la interfaz.
     *
     * @param card CardLayout del contenedor.
     */
    public void setCard(CardLayout card) {
        this.card = card;
    }

    /**
     * Asigna la ventana principal al menú para poder cambiar el título Según la
     * opción elegida del menú.
     *
     * @param frame Ventana principal de la aplicación.
     */
    public void setFrame(Interfaz frame) {
        this.frame = frame;
    }

    /**
     * Obtiene el elemento de listado de eventos del menú para poder ejecutar el
     * manejador de clic desde la home.
     *
     * @return Label de listado de eventos.
     */
    public RoundTextLabel getLblEvtList() {
        return lblEvtList;
    }

    /**
     * Obtiene el elemento de nuevo evento del menú para poder ejecutar el
     * manejador de clic desde la home.
     *
     * @return Label de nuevo evento.
     */
    public RoundTextLabel getLblEvtNuevo() {
        return lblEvtNuevo;
    }

    /**
     * Obtiene el elemento de eventos del menú para poder ejecutar el manejador
     * de clic desde la home.
     *
     * @return Label de eventoss.
     */
    public RoundTextLabel getLblEventos() {
        return lblEventos;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new components.atoms.RoundPanel();
        lblLogo = new javax.swing.JLabel();
        pnlSup = new javax.swing.JPanel();
        lblEventos = new components.atoms.RoundTextLabel();
        pnlEventos = new javax.swing.JPanel();
        lblEvtNuevo = new components.atoms.RoundTextLabel();
        lblEvtList = new components.atoms.RoundTextLabel();
        lblArtistas = new components.atoms.RoundTextLabel();
        pnlArtistas = new javax.swing.JPanel();
        lblArtNuevo = new components.atoms.RoundTextLabel();
        lblArtList = new components.atoms.RoundTextLabel();
        lblTecnicos = new components.atoms.RoundTextLabel();
        pnlTecnicos = new javax.swing.JPanel();
        lblTecNuevo = new components.atoms.RoundTextLabel();
        lblTecList = new components.atoms.RoundTextLabel();
        pnlInf = new javax.swing.JPanel();
        lblMenuHome = new components.atoms.RoundTextLabel();
        lblMenuSalir = new components.atoms.RoundTextLabel();

        setBackground(Colores.TRANSPARENTE);
        setPreferredSize(new java.awt.Dimension(200, 600));

        menu.setBackground(Colores.GRISCLARO);
        menu.setPreferredSize(new java.awt.Dimension(200, 600));
        menu.setRoundBottomLeft(99);
        menu.setRoundTopLeft(99);

        lblLogo.setBackground(Colores.TRANSPARENTE);
        lblLogo.setIcon(new javax.swing.ImageIcon("E:\\TFG\\Redimension\\assets\\icons\\BaseMentLogo.png")); // NOI18N

        pnlSup.setBackground(Colores.TRANSPARENTE);

        lblEventos.setBackground(Colores.TRANSPARENTE);
        lblEventos.setText("Eventos");
        lblEventos.setIcono(Iconos.TICKETNARANJA);
        lblEventos.setIconoAlternativo(Iconos.TICKETBLANCO);
        lblEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEventosMouseClicked(evt);
            }
        });

        pnlEventos.setBackground(Colores.TRANSPARENTE);

        lblEvtNuevo.setBackground(Colores.TRANSPARENTE);
        lblEvtNuevo.setText("Nuevo");
        lblEvtNuevo.setIcono(Iconos.ADDNARANJA);
        lblEvtNuevo.setIconoAlternativo(Iconos.ADDBLANCO);
        lblEvtNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEvtNuevoMouseClicked(evt);
            }
        });

        lblEvtList.setBackground(Colores.TRANSPARENTE);
        lblEvtList.setText("Listado");
        lblEvtList.setIcono(Iconos.LISTNARANJA);
        lblEvtList.setIconoAlternativo(Iconos.LISTBLANCO);
        lblEvtList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEvtListMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlEventosLayout = new javax.swing.GroupLayout(pnlEventos);
        pnlEventos.setLayout(pnlEventosLayout);
        pnlEventosLayout.setHorizontalGroup(
            pnlEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEventosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEvtNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEvtList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEventosLayout.setVerticalGroup(
            pnlEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEventosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblEvtNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(lblEvtList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        lblArtistas.setBackground(Colores.TRANSPARENTE);
        lblArtistas.setText("Artistas");
        lblArtistas.setIcono(Iconos.MICNARANJA);
        lblArtistas.setIconoAlternativo(Iconos.MICBLANCO);
        lblArtistas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblArtistasMouseClicked(evt);
            }
        });

        pnlArtistas.setBackground(Colores.TRANSPARENTE);

        lblArtNuevo.setBackground(Colores.TRANSPARENTE);
        lblArtNuevo.setText("Nuevo");
        lblArtNuevo.setIcono(Iconos.ADDNARANJA);
        lblArtNuevo.setIconoAlternativo(Iconos.ADDBLANCO);
        lblArtNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblArtNuevoMouseClicked(evt);
            }
        });

        lblArtList.setBackground(Colores.TRANSPARENTE);
        lblArtList.setText("Listado");
        lblArtList.setIcono(Iconos.LISTNARANJA);
        lblArtList.setIconoAlternativo(Iconos.LISTBLANCO);
        lblArtList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblArtListMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlArtistasLayout = new javax.swing.GroupLayout(pnlArtistas);
        pnlArtistas.setLayout(pnlArtistasLayout);
        pnlArtistasLayout.setHorizontalGroup(
            pnlArtistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlArtistasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlArtistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblArtNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblArtList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlArtistasLayout.setVerticalGroup(
            pnlArtistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlArtistasLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblArtNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(lblArtList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        lblTecnicos.setBackground(Colores.TRANSPARENTE);
        lblTecnicos.setText("Técnicos");
        lblTecnicos.setIcono(Iconos.MIXERNARANJA);
        lblTecnicos.setIconoAlternativo(Iconos.MICBLANCO);
        lblTecnicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTecnicosMouseClicked(evt);
            }
        });

        pnlTecnicos.setBackground(Colores.TRANSPARENTE);

        lblTecNuevo.setBackground(Colores.TRANSPARENTE);
        lblTecNuevo.setText("Nuevo");
        lblTecNuevo.setIcono(Iconos.ADDNARANJA);
        lblTecNuevo.setIconoAlternativo(Iconos.ADDBLANCO);
        lblTecNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTecNuevoMouseClicked(evt);
            }
        });

        lblTecList.setBackground(Colores.TRANSPARENTE);
        lblTecList.setText("Listado");
        lblTecList.setIcono(Iconos.LISTNARANJA);
        lblTecList.setIconoAlternativo(Iconos.LISTBLANCO);
        lblTecList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTecListMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlTecnicosLayout = new javax.swing.GroupLayout(pnlTecnicos);
        pnlTecnicos.setLayout(pnlTecnicosLayout);
        pnlTecnicosLayout.setHorizontalGroup(
            pnlTecnicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTecnicosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTecnicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTecList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTecNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTecnicosLayout.setVerticalGroup(
            pnlTecnicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTecnicosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblTecNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblTecList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlSupLayout = new javax.swing.GroupLayout(pnlSup);
        pnlSup.setLayout(pnlSupLayout);
        pnlSupLayout.setHorizontalGroup(
            pnlSupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSupLayout.createSequentialGroup()
                .addGroup(pnlSupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSupLayout.createSequentialGroup()
                        .addGroup(pnlSupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTecnicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblArtistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addComponent(pnlTecnicos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlSupLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pnlSupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlSupLayout.createSequentialGroup()
                                    .addComponent(lblEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(24, 24, 24))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSupLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pnlEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(pnlArtistas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlSupLayout.setVerticalGroup(
            pnlSupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSupLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblArtistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlArtistas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblTecnicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlTecnicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlInf.setBackground(Colores.TRANSPARENTE);

        lblMenuHome.setBackground(Colores.TRANSPARENTE);
        lblMenuHome.setText("Home");
        lblMenuHome.setIcono(Iconos.HOMENARANJA);
        lblMenuHome.setIconoAlternativo(Iconos.HOMEBLANCO);
        lblMenuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuHomeMouseClicked(evt);
            }
        });

        lblMenuSalir.setBackground(Colores.TRANSPARENTE);
        lblMenuSalir.setText("Salir");
        lblMenuSalir.setIcono(Iconos.LOGOUTNARANJA);
        lblMenuSalir.setIconoAlternativo(Iconos.LOGOUTBLANCO);
        lblMenuSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuSalirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlInfLayout = new javax.swing.GroupLayout(pnlInf);
        pnlInf.setLayout(pnlInfLayout);
        pnlInfLayout.setHorizontalGroup(
            pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMenuSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMenuHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlInfLayout.setVerticalGroup(
            pnlInfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblMenuHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblMenuSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLogo)
                            .addComponent(pnlInf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(pnlSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlInf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(lblLogo)
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * cuando 
     * @param evt 
     */
    private void lblEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEventosMouseClicked
        parentMenuHandler(pnlEventos);
    }//GEN-LAST:event_lblEventosMouseClicked

    private void lblArtistasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArtistasMouseClicked
        parentMenuHandler(pnlArtistas);
    }//GEN-LAST:event_lblArtistasMouseClicked

    private void lblTecnicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTecnicosMouseClicked
        parentMenuHandler(pnlTecnicos);
    }//GEN-LAST:event_lblTecnicosMouseClicked

    private void parentMenuHandler(JPanel submenu) {
        visibleClickHandler(submenu);
//        submenu.setBackground(Colores.TRANSPARENTE);
        menu.setBackground(Colores.GRISAZUL);
        menu.setBackground(Colores.GRISCLARO);
    }
    
    /**
     * Expande o colapsa el panel de submenú en cuestión
     * @param panel Panel de submenú a expandir/colapsar.
     */
    private void visibleClickHandler(JPanel panel) {
        if (visible != null) {
            if (visible.equals(panel)) {
                panel.setVisible(false);
                visible = null;
            } else {
                visible.setVisible(false);
                panel.setVisible(true);
                visible = panel;
            }
        } else {
            panel.setVisible(true);
            visible = panel;
        }

    }

    public void clickHandler(RoundTextLabel parentLabel, RoundTextLabel label, String vista, String titulo) {
        if (selected != null) {
            if (!selected.equals(label)) {
                selected.setSelected(false);
                if (parentSelected != null)
                    parentSelected.setSelected(false);
                label.setSelected(true);
                if (parentLabel != null)
                    parentLabel.setSelected(true);
                selected = label;
                parentSelected = parentLabel;
            }
        } else {
            if (parentLabel != null)
                parentLabel.setSelected(true);
            label.setSelected(true);
            selected = label;
            parentSelected = parentLabel;
        }
        
        card.show(container, vista);
        frame.setTitle(titulo);
    }
    
    public void homeClickHandler(String operacion) {
        switch (operacion) {
            case ("nuevo"):
                lblEvtNuevoMouseClicked(null);
            case ("list"):
                lblEvtListMouseClicked(null);
            default:
    }
        
    }

    private void lblTecNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTecNuevoMouseClicked
        clickHandler(lblTecnicos, lblTecNuevo, "tecNuevo", "Nuevo técnico");
    }//GEN-LAST:event_lblTecNuevoMouseClicked

    private void lblTecListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTecListMouseClicked
        clickHandler(lblTecnicos, lblTecList, "tecList", "Listado de técnicos");
    }//GEN-LAST:event_lblTecListMouseClicked

    private void lblArtNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArtNuevoMouseClicked
        clickHandler(lblArtistas, lblArtNuevo, "artNuevo", "Nuevo artista");
    }//GEN-LAST:event_lblArtNuevoMouseClicked

    private void lblArtListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblArtListMouseClicked
        clickHandler(lblArtistas, lblArtList, "artList", "Listado de artistas");
    }//GEN-LAST:event_lblArtListMouseClicked

    private void lblMenuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuHomeMouseClicked
        clickHandler(null, lblMenuHome, "home", "Home");
    }//GEN-LAST:event_lblMenuHomeMouseClicked

    private void lblMenuSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuSalirMouseClicked
        frame.dispose();
    }//GEN-LAST:event_lblMenuSalirMouseClicked

    private void lblEvtNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEvtNuevoMouseClicked
        clickHandler(lblEventos, lblEvtNuevo, "evtNuevo", "Nuevo evento");
    }//GEN-LAST:event_lblEvtNuevoMouseClicked

    private void lblEvtListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEvtListMouseClicked
        clickHandler(lblEventos, lblEvtList, "evtList", "Listado de eventos");
    }//GEN-LAST:event_lblEvtListMouseClicked

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container c) {
        container = c;
        setCard(container.getCard());
        setFrame(c.getFrame());
    }

    public void setMain(JFrame main) {
        this.main = main;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private components.atoms.RoundTextLabel lblArtList;
    private components.atoms.RoundTextLabel lblArtNuevo;
    private components.atoms.RoundTextLabel lblArtistas;
    private components.atoms.RoundTextLabel lblEventos;
    private components.atoms.RoundTextLabel lblEvtList;
    private components.atoms.RoundTextLabel lblEvtNuevo;
    private javax.swing.JLabel lblLogo;
    private components.atoms.RoundTextLabel lblMenuHome;
    private components.atoms.RoundTextLabel lblMenuSalir;
    private components.atoms.RoundTextLabel lblTecList;
    private components.atoms.RoundTextLabel lblTecNuevo;
    private components.atoms.RoundTextLabel lblTecnicos;
    private components.atoms.RoundPanel menu;
    private javax.swing.JPanel pnlArtistas;
    private javax.swing.JPanel pnlEventos;
    private javax.swing.JPanel pnlInf;
    private javax.swing.JPanel pnlSup;
    private javax.swing.JPanel pnlTecnicos;
    // End of variables declaration//GEN-END:variables
}
