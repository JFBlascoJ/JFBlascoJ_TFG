package assets.lookandfeel;

import java.awt.Font;

/**
 * Clase que contiene las fuentes utilizadas en la interfaz gráfica.
 * Las fuentes están definidas como variables estáticas y finales.
 * @author José Francisco Blasco Jiménez
 */
public class Fuentes {
    private static final String FOLDER = "assets/fonts/";
    /**
     * Fuente utilizada en labels y en el DateChooser.
     * Roboto, Negrita, Tamaño 14.
     */
    public static final Font LABEL = new Font(FOLDER + "Roboto-Bold.ttf", Font.BOLD, 14);
    /**
     * Fuente utilizada en el menú.
     * Roboto, Negrita, Tamaño 18.
     */
    public static final Font MENU = new Font(FOLDER + "Roboto-Bold.ttf", Font.BOLD, 18);
    /**
     * Fuente utilizada en el resto de componentes.
     * Roboto, Tamaño 14.
     */
    public static final Font REGULAR = new Font(FOLDER + "Roboto-Regular.ttf", Font.PLAIN, 14);

}
