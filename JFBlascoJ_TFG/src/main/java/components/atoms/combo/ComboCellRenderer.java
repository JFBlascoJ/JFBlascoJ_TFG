package components.atoms.combo;

import assets.lookandfeel.Colores;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 * Clase personalizada para renderizar los elementos de un JComboBox.
 * Hereda de DefaultListCellRenderer.
 */
public class ComboCellRenderer extends DefaultListCellRenderer {

    /**
     * Constructor de la clase ComboCellRenderer.
     * Establece la propiedad "opaque" en true para asegurar que el fondo del componente se pinte correctamente.
     */
    public ComboCellRenderer() {
        setOpaque(true);
    }

    /**
     * Método para obtener el componente de representación de cada elemento en el JComboBox.
     *
     * @param list Lista a la que pertenece el elemento.
     * @param value Valor del elemento.
     * @param index Índice del elemento.
     * @param isSelected Indica si el elemento está seleccionado.
     * @param cellHasFocus  Indica si el elemento tiene el enfoque.
     * @return El componente de representación del elemento.
     */
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        // Establece el texto del elemento en el valor correspondiente. Si es nulo, se muestra una cadena vacía.
        setText(value == null ? "" : value.toString());
        // Establece el fondo del elemento según si está seleccionado o no.
        setBackground(isSelected ? Colores.NARANJA : Colores.BLANCO);
        // Establece el color del texto del elemento según si está seleccionado o no.
        setForeground(isSelected ? Colores.BLANCO : Colores.AZUL);
        // Establece el cursor del elemento según si está seleccionado o no.
        setCursor(isSelected ? new Cursor(Cursor.HAND_CURSOR) : new Cursor(Cursor.DEFAULT_CURSOR));
        // Devuelve el componente para que se muestre correctamente en el JComboBox.
        return this;
    }
}