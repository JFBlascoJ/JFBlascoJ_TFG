package assets.lookandfeel;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.Icon;

/**
 * Clase que genera un icono con el padding izquierdo indicado por el usuario, para los elementos del menú.
 * Implementa la interfaz Icon.
 * @author José Francisco Blasco Jiménez.
 */
public class IconoPadding implements Icon {
    private Icon originalIcon;
    private int padding;
    
    /**
     * Constructor que crea un nuevo icono con padding.
     * @param originalIcon Icono al que se le queire añadir padding izquierdo.
     * @param padding Padding izquierdo a añadir (en píxeles).
     */
    public IconoPadding(Icon originalIcon, int padding) {
        this.originalIcon = originalIcon;
        this.padding = padding;
    }

    
    /**
     * Pinta el icono en el componente especificado, teniendo en cuenta el padding.
     * 
     * @param c Componente donde se va a pintar el icono.
     * @param g Contexto gráfico donde se va a realizar la pintura.
     * @param x Coordenada x en la que se va a pintar el icono.
     * @param y Coordenada y en la que se va a pintar el icono.
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        originalIcon.paintIcon(c, g, x + padding, y);
    }

    /**
     * Obtiene el ancho del icono, teniendo en cuenta el padding.
     * @return El ancho del icono, incluyendo el padding.
     */
    @Override
    public int getIconWidth() {
        return originalIcon.getIconWidth() + padding;
    }

    /**
     * Obtiene la altura del icono.
     * @return La altura del icono.
     */
    @Override
    public int getIconHeight() {
        return originalIcon.getIconHeight();
    }

    /**
     * Obtiene los insets del icono..
     * @return Los insets con el padding aplicado.
     */
    public Insets getInsets() {
        return new Insets(0, padding, 0, 0);
    }
}