package components.atoms;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

/**
 * Clase para crear bordes redondeados en componentes Swing.
 */
public class RoundedBorder extends AbstractBorder {

    /**
     * Color transparente completamente transparente.
     */
    private static final Color ALPHA_ZERO = new Color(0x0, true);

    /**
     * Radio de las esquinas del borde redondeado.
     */
    private int radius;

    /**
     * Grosor del borde hacia adentro.
     */
    private int innerThickness;

    /**
     * Grosor del borde hacia afuera.
     */
    private int outerThickness;

    /**
     * Color del borde.
     */
    private Color color;

    /**
     * Crea un borde redondeado con radio, grosor y color especificados.
     *
     * @param radius Radio de las esquinas.
     * @param innerThickness Grosor del borde hacia adentro.
     * @param outerThickness Grosor del borde hacia afuera.
     * @param color Color del borde.
     */
    public RoundedBorder(int radius, int innerThickness, int outerThickness, Color color) {
        this.radius = radius;
        this.innerThickness = innerThickness;
        this.outerThickness = outerThickness;
        this.color = color;
    }

    /**
     * Pinta el borde redondeado en el componente especificado.
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Crear forma para el borde interior
        Shape innerBorder = new RoundRectangle2D.Double(x + 1, y + 1, width - 2, height - 2, radius, radius);

        // Crear forma para el borde exterior
        // Shape outerBorder = new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius + 1, radius + 1);
        Shape outerBorder = new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius);

        // Dibujar el borde exterior con el grosor y color especificados
        g2.setColor(color);
        g2.setStroke(new BasicStroke(outerThickness));
        g2.draw(outerBorder);

        // Dibujar el borde interior con el grosor y color especificados
        g2.setStroke(new BasicStroke(innerThickness));
        g2.draw(innerBorder);

        g2.dispose();
    }
}
