package components.atoms;

import assets.lookandfeel.Colores;
import assets.lookandfeel.Fuentes;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * Clase personalizada para los elementos del menú. Cuenta con un fondo con
 * esquinas redondeadas y un icono a la izquierda del texto.
 *
 * @author José Francisco Blasco Jiménez
 */
public class RoundTextLabel extends JLabel {

    private int roundTopLeft;
    private int roundTopRight;
    private int roundBottomLeft;
    private int roundBottomRight;
    private Icon icono, iconoAlternativo;
    private boolean subMenu, selected;
    private MouseListener listener;

    /**
     * Crea una nueva instancia.
     */
    public RoundTextLabel() {
        roundTopLeft = 35;
        roundTopRight = 35;
        roundBottomLeft = 35;
        roundBottomRight = 35;
        setBackground(Colores.TRANSPARENTE);
        setFont(Fuentes.LABEL);
        setForeground(Colores.NARANJA);
        selected = false;
        setOpaque(false);
        setPreferredSize(
                new Dimension(150, 30));
        setHorizontalAlignment(SwingConstants.LEFT);
        setCursor(
                new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!selected) {
                    fondoBlanco();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!selected) {
                    fondoTransparente();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isSubMenu()) {
                    selectionHandler();
                }
            }
        });

    }

    /**
     * Deselecciona el elemento seleccionado al seleccionar otro
     */
    public void fondoTransparente() {
        setBackground(Colores.GRISCLARO);
        setForeground(Colores.NARANJA);
        setIcon(icono);
    }

    /**
     * Marca el elemento padre del elemento del menú. De esta manera, el usuario
     * puede identificar con qué entidad está trabajando auqne todos los
     * elementos estén colapsados.
     */
    public void fondoBlanco() {
        setBackground(Colores.BLANCO);
        setForeground(Colores.NARANJA);
        setIcon(icono);
    }

    /**
     * Marca como seleccionado el elemento del menú en cuestión
     */
    public void fondoNaranja() {
        setBackground(Colores.NARANJA);
        setForeground(Colores.BLANCO);
        setIcon(iconoAlternativo);
    }

    /**
     * Maneja el cambio de color según las propiedades del elemento.. -
     * Seleccionado - Submenú: Fondo naranja. - No submenú: Fondo blanco. - No
     * seleccionado: Fondo transparente.
     */
    public void selectionHandler() {
        if (selected) {
            if (isSubMenu()) {
                fondoNaranja();
            } else {
                fondoBlanco();
            }
        } else {
            fondoTransparente();
        }
    }

    /**
     * Setea el icono en su estado normal.
     *
     * @param ico Icono a asignar como normal.
     */
    public void setIcono(Icon ico) {
        this.icono = ico;
        setIcon(ico);
    }

    /**
     * Setea el icono que se mostrará cuando el elemento esté seleccionado.
     *
     * @param ico Icono a asignar como alternativo.
     */
    public void setIconoAlternativo(Icon iconoAlternativo) {
        this.iconoAlternativo = iconoAlternativo;
    }

    /**
     * Comprueba si es un elemento hijo (submenú).
     *
     * @return True si es submenú, False en caso contrario.
     */
    public boolean isSubMenu() {
        return subMenu;
    }

    /**
     * Setea el elemento como hijo o como padre.
     *
     * @param subMenu True si es hijo, False en caso contrario.
     */
    public void setSubMenu(boolean subMenu) {
        this.subMenu = subMenu;
    }

    /**
     * Comprueba si el elemento está seleccionado o no.
     *
     * @return Treu si está seleccionado. False en caso contrario.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Marca el elemento como seleccionado o no.
     *
     * @param sel True si está seleccionado, Flase en caso contrario.
     */
    public void setSelected(boolean sel) {
        selected = sel;
        selectionHandler();
    }

    /**
     * Pinta el componente con esquinas redondeadas basadas en los valores
     * especificados para cada esquina.
     * @param grphcs el contexto Graphics en el cual se va a pintar
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        // Obtener el contexto Graphics2D para pintar con gráficos vectoriales de alta calidad
        Graphics2D g2 = (Graphics2D) grphcs.create();
        // Habilitar el suavizado de bordes para obtener bordes suaves
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Establecer el color de fondo del componente
        g2.setColor(getBackground());
        // Crear un objeto Area para almacenar la forma del componente con esquinas redondeadas
        Area area = new Area(createRoundTopLeft());
        // Si el radio de esquina superior derecha es mayor que cero, intersectar el área con la esquina redondeada superior derecha
        if (roundTopRight > 0) {
            area.intersect(new Area(createRoundTopRight()));
        }
        // Si el radio de esquina inferior izquierda es mayor que cero, intersectar el área con la esquina redondeada inferior izquierda
        if (roundBottomLeft > 0) {
            area.intersect(new Area(createRoundBottomLeft()));
        }
        // Si el radio de esquina inferior derecha es mayor que cero, intersectar el área con la esquina redondeada inferior derecha
        if (roundBottomRight > 0) {
            area.intersect(new Area(createRoundBottomRight()));
        }
        // Rellenar el área resultante con el color de fondo
        g2.fill(area);
        // Liberar los recursos del contexto Graphics2D
        g2.dispose();
        // Llamar al método paintComponent de la superclase para realizar el resto del pintado
        super.paintComponent(grphcs);
    }

    /**
     * Crea la forma de la esquina superior izquierda con esquinas redondeadas.
     * @return la forma de la esquina superior izquierda
     */
    private Shape createRoundTopLeft() {
        int width = getWidth();
        int height = getHeight();
        // Obtener el radio de esquina redondeada para la esquina superior izquierda
        int roundX = Math.min(width, roundTopLeft);
        int roundY = Math.min(height, roundTopLeft);

        // Crear un objeto Area para almacenar la forma de la esquina superior izquierda con esquinas redondeadas
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        // Agregar un área rectangular para crear una esquina superior izquierda suavizada
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        // Agregar un área rectangular para crear una esquina superior izquierda suavizada
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        // Devolver la forma resultante de la esquina superior izquierda con esquinas redondeadas
        return area;
    }

    /**
     * Crea la forma de la esquina superior derecha con esquinas redondeadas.
     * @return la forma de la esquina superior derecha
     */
    private Shape createRoundTopRight() {
        int width = getWidth();
        int height = getHeight();
        // Obtener el radio de esquina redondeada para la esquina superior derecha
        int roundX = Math.min(width, roundTopRight);
        int roundY = Math.min(height, roundTopRight);

        // Crear un objeto Area para almacenar la forma de la esquina superior derecha con esquinas redondeadas
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        // Agregar un área rectangular para crear una esquina superior derecha suavizada
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        // Agregar un área rectangular para crear una esquina superior derecha suavizada
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        // Devolver la forma resultante de la esquina superior derecha con esquinas redondeadas
        return area;
    }

    /**
     * Crea la forma de la esquina inferior izquierda con esquinas redondeadas.
     * @return la forma de la esquina inferior izquierda
     */
    private Shape createRoundBottomLeft() {
        int width = getWidth();
        int height = getHeight();
        // Obtener el radio de esquina redondeada para la esquina inferior izquierda
        int roundX = Math.min(width, roundBottomLeft);
        int roundY = Math.min(height, roundBottomLeft);
        
        // Crear un objeto Area para almacenar la forma de la esquina inferior izquierda con esquinas redondeadas
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        // Agregar un área rectangular para crear una esquina inferior izquierda suavizada
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        // Agregar un área rectangular para crear una esquina inferior izquierda suavizada
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        // Devolver la forma resultante de la esquina inferior izquierda con esquinas redondeadas
        return area;
    }

    /**
     * Crea la forma de la esquina inferior derecha con esquinas redondeadas.
     * @return la forma de la esquina inferior derecha
     */
    private Shape createRoundBottomRight() {
        int width = getWidth();
        int height = getHeight();
        // Obtener el radio de esquina redondeada para la esquina inferior derecha
        int roundX = Math.min(width, roundBottomRight);
        int roundY = Math.min(height, roundBottomRight);

        // Crear un objeto Area para almacenar la forma de la esquina inferior derecha con esquinas redondeadas
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        // Agregar un área rectangular para crear una esquina inferior derecha suavizada
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        // Agregar un área rectangular para crear una esquina inferior derecha suavizada
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        // Devolver la forma resultante de la esquina inferior derecha con esquinas redondeadas
        return area;
    }

}
