package components.atoms.tblrenderers;

import assets.lookandfeel.Colores;
import assets.lookandfeel.Fuentes;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class HeaderRendererAzul extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(Colores.AZUL);
        c.setFont(Fuentes.LABEL);
        c.setForeground(Colores.BLANCO);
        return c;
    }
    
    
}
