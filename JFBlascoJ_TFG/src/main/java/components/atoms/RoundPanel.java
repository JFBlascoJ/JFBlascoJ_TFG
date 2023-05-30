package components.atoms;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 * JPanel personalizado que permite configurar esquinas redondeadas en cada uno
 * de los cuatro lados.
 */
public class RoundPanel extends JPanel {

    private int roundTopLeft;
    private int roundTopRight;
    private int roundBottomLeft;
    private int roundBottomRight;

    /**
     * Crea un nuevo RoundPanel. Configura la opacidad del panel en falso para
     * asegurar que el panel tenga una apariencia redondeada.
     */
    public RoundPanel() {
        setOpaque(false);
    }

    /**
     * Establece el radio de esquina redondeada en la esquina superior
     * izquierda.
     *
     * @param roundTopLeft el radio de esquina redondeada en la esquina superior
     * izquierda
     */
    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    /**
     * Establece el radio de esquina redondeada en la esquina superior derecha.
     *
     * @param roundTopRight el radio de esquina redondeada en la esquina
     * superior derecha
     */
    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    /**
     * Establece el radio de esquina redondeada en la esquina inferior
     * izquierda.
     *
     * @param roundBottomLeft el radio de esquina redondeada en la esquina
     * inferior izquierda
     */
    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    /**
     * Establece el radio de esquina redondeada en la esquina inferior derecha.
     *
     * @param roundBottomRight el radio de esquina redondeada en la esquina
     * inferior derecha
     */
    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }

    /**
     * Sobrescribe el método paintComponent para pintar el componente en la
     * pantalla.
     *
     * @param grphcs El objeto Graphics utilizado para pintar el componente.
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        // Crear un contexto Graphics2D a partir del objeto Graphics recibido
        Graphics2D g2 = (Graphics2D) grphcs.create();
        // Configurar el suavizado del renderizado para obtener bordes más suaves
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Establecer el color del componente como el color de fondo
        g2.setColor(getBackground());
        // Crear una región "area" utilizando el método createRoundTopLeft()
        Area area = new Area(createRoundTopLeft());

        // Intersectar la región "area" con una región "createRoundTopRight()" si roundTopRight es mayor que 0
        if (roundTopRight > 0) {
            area.intersect(new Area(createRoundTopRight()));
        }

        // Intersectar la región "area" con una región "createRoundBottomLeft()" si roundBottomLeft es mayor que 0
        if (roundBottomLeft > 0) {
            area.intersect(new Area(createRoundBottomLeft()));
        }

        // Intersectar la región "area" con una región "createRoundBottomRight()" si roundBottomRight es mayor que 0
        if (roundBottomRight > 0) {
            area.intersect(new Area(createRoundBottomRight()));
        }

        // Rellenar la región "area" con el color de fondo
        g2.fill(area);
        // Liberar los recursos del contexto gráfico
        g2.dispose();
        // Llamar al método paintComponent de la superclase para realizar el pintado adicional
        super.paintComponent(grphcs);
    }

    /**
     * Crea y devuelve una forma (Shape) para la esquina superior izquierda
     * redondeada.
     * @return La forma (Shape) que representa la esquina superior izquierda
     * redondeada.
     */
    private Shape createRoundTopLeft() {
        int width = getWidth();
        int height = getHeight();
        // Calcular el valor mínimo entre el ancho y la altura para obtener el radio de esquina adecuado
        int roundX = Math.min(width, roundTopLeft);
        int roundY = Math.min(height, roundTopLeft);

        // Crear un objeto Area que representa un rectángulo redondeado con esquina superior izquierda redondeada
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        // Agregar un área adicional representada por un rectángulo a la mitad del ancho para obtener un efecto de esquina redondeada
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        // Agregar un área adicional representada por un rectángulo a la mitad de la altura para obtener un efecto de esquina redondeada
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        // Devolver la forma (Shape) resultante
        return area;
    }

    /**
     * Crea y devuelve una forma (Shape) para la esquina superior derecha
     * redondeada.
     *
     * @return La forma (Shape) que representa la esquina superior derecha
     * redondeada.
     */
    private Shape createRoundTopRight() {
        int width = getWidth();
        int height = getHeight();
        // Calcular el valor mínimo entre el ancho y la altura para obtener el radio de esquina adecuado
        int roundX = Math.min(width, roundTopRight);
        int roundY = Math.min(height, roundTopRight);

        // Crear un objeto Area que representa un rectángulo redondeado con esquina superior derecha redondeada
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        // Agregar un área adicional representada por un rectángulo desde la esquina superior izquierda hasta el ancho restante
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        // Agregar un área adicional representada por un rectángulo desde la mitad de la altura hasta el alto restante
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));

        // Devolver la forma (Shape) resultante
        return area;
    }

    /**
     * Crea y devuelve una forma (Shape) para la esquina inferior izquierda
     * redondeada.
     *
     * @return La forma (Shape) que representa la esquina inferior izquierda
     * redondeada.
     */
    private Shape createRoundBottomLeft() {
        int width = getWidth();
        int height = getHeight();
        // Calcular el valor mínimo entre el ancho y la altura para obtener el radio de esquina adecuado
        int roundX = Math.min(width, roundBottomLeft);
        int roundY = Math.min(height, roundBottomLeft);

        // Crear un objeto Area que representa un rectángulo redondeado con esquina inferior izquierda redondeada
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        // Agregar un área adicional representada por un rectángulo desde la esquina superior izquierda hasta el ancho restante
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        // Agregar un área adicional representada por un rectángulo desde la esquina superior izquierda hasta el alto restante
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));

        // Devolver la forma (Shape) resultante
        return area;
    }

    /**
     * Crea y devuelve una forma (Shape) para la esquina inferior derecha
     * redondeada.
     *
     * @return La forma (Shape) que representa la esquina inferior derecha
     * redondeada.
     */
    private Shape createRoundBottomRight() {
        int width = getWidth();
        int height = getHeight();

        // Calcular el valor mínimo entre el ancho y la altura para obtener el radio de esquina adecuado
        int roundX = Math.min(width, roundBottomRight);
        int roundY = Math.min(height, roundBottomRight);

        // Crear un objeto Area que representa un rectángulo redondeado con esquina inferior derecha redondeada
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));

        // Agregar un área adicional representada por un rectángulo desde la esquina superior izquierda hasta el ancho restante
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));

        // Agregar un área adicional representada por un rectángulo desde la esquina superior izquierda hasta el alto restante
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));

        // Devolver la forma (Shape) resultante
        return area;
    }
}
