package components.atoms.scrollbar;

import assets.lookandfeel.Colores;
import java.awt.Dimension;
import javax.swing.JScrollBar;

/**
 * Barra de scroll personalizada.
 * Hereda de JScrollBar.
 */
public class ScrollBarCustom extends JScrollBar {

    /**
     * Constructor de la clase ScrollBarCustom. Configura la apariencia y las
     * propiedades de la barra de desplazamiento personalizada.
     */
    public ScrollBarCustom() {
        // Establecer la interfaz de usuario (UI) personalizada para la barra de desplazamiento
        setUI(new ModernScrollBarUI());
        // Establecer el tamaño preferido de la barra de desplazamiento
        setPreferredSize(new Dimension(10, 10));
        // Establecer el color del primer plano (foreground) de la barra de desplazamiento
        setForeground(Colores.NARANJA);
        // Establecer el color de fondo (background) de la barra de desplazamiento
        setBackground(Colores.GRISAZUL);
    }
}
