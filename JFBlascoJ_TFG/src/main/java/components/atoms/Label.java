package components.atoms;

import assets.lookandfeel.Colores;
import assets.lookandfeel.Fuentes;
import javax.swing.JLabel;

/**
 * Clase personalizada para las Labels de la aplicación.
 * Hereda de JLabel.
 * @author José Francisco Blasco Jiménez
 */
public class Label extends JLabel {
    
    /**
     * Crea una nueva instancia de Label con las siguientes propiedades:
     *  - Roboto, negrita, tamaño: 14.
     *  - Color azul.
     */
    public Label() {
        setFont(Fuentes.LABEL);
        setForeground(Colores.AZUL);
    }
}
