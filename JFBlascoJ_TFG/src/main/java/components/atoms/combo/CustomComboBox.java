package components.atoms.combo;

import DAO.*;
import assets.lookandfeel.*;
import POJOs.Tecnico;
import POJOs.Artista;
import assets.objects.Comunes;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JComboBox;

/**
 * Combobox personalizado para artistas o técnicos
 * @author José Francisco Blasco Jiménez
 */
public class CustomComboBox extends JComboBox {
    private DAOTecnico dTec;
    private DAOArtista dArt;
    private DefaultComboBoxModel<Tecnico> modTecnico;
    private DefaultComboBoxModel<Artista> modArtista;
    private int tipo;
    private boolean listarCero;
    private Comunes comunes;

    public CustomComboBox() {
    }

    /**
     * Sete los objetos comunes
     * @param com Objeto de tipo Comunes con los objetos comunes para toda la aplicación
     */
    public void setComunes(Comunes com) {
        comunes = com;
        dArt = comunes.getDAOART();
        dTec = comunes.getDAOTEC();
        setBackground(Colores.BLANCO);
        setFont(Fuentes.REGULAR);
        setForeground(Colores.AZUL);
        setBorder(Bordes.AZUL20);
        setRenderer(new ComboCellRenderer());
    }
    
    /**
     * Setea el tipo del ComboBoz
     * @param tipo 
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
        
        switch (tipo) {
            case Comunes.TIPO_ARTISTA:
                dArt = comunes.getDAOART();
                modArtista = new DefaultComboBoxModel();
                setModel(modArtista);
                break;
            case Comunes.TIPO_TECNICO:
                dTec = comunes.getDAOTEC();
                modTecnico = new DefaultComboBoxModel();
                setModel(modTecnico);
                break;
        }
    }
    
    /**
     * Comprueba si se lista el elemento 0 ("Sin artista"/"Sin técnico") en la lista.
     * @return 
     */
    public boolean isListarCero() {
        return listarCero;
    }
    
    /**
     * Indica si se debe mostrar "Sin artistas"/"Sin técnico" en la listta desplegable.
     * @param listarCero True si debe añadir "Sin Artistas"/"Sin técnico" a la lista, False en caso contrario.
     */
    public void setListarCero(boolean listarCero) {
        this.listarCero = listarCero;
        
        switch (tipo) {
            case Comunes.TIPO_ARTISTA:
                modArtista.removeAllElements();
                modArtista.addAll(listarCero ? dArt.consultarTodos(true) : dArt.consultarTodosSinCero(true));
                break;
            case Comunes.TIPO_TECNICO:
                modTecnico.removeAllElements();
                modTecnico.addAll(listarCero ? dTec.consultarTodos(true) : dTec.consultarTodosSinCero(true));
                break;
        }
        
    }
    
    /**
     * Devuelve el objeto seleccionado según el tipo (Artista o técnico)
     * @return Artista o técnico seleccionado, dependiendo del tipo.
     */
    public Object getSelected() {
        switch (tipo) {
            case Comunes.TIPO_ARTISTA:
                return (Artista) modArtista.getSelectedItem();
            case Comunes.TIPO_TECNICO:
                return (Tecnico) modTecnico.getSelectedItem();
            default:
                return null;
        }
    }
    
    /**
     * Devuelve el ComboBox a su estado inicial, con ningún elemento seleccionado
     */
    public void resetear() {
        setSelectedIndex(-1);
    }
    
    /**
     * Habilita el ComboBox, para poder cambiar la selección a la hora de modificar un evento
     */
    public void habilitar() {
        setEnabled(true);
        setBackground(Colores.BLANCO);
        setBorder(Bordes.AZUL20);
    }
    
    /**
     * Deshabilita el ComboBox, para evitar modificaciones.
     */
    public void deshabilitar() {
        setEnabled(false);
        setBackground(Colores.GRISCLARO);
        setBorder(Bordes.GRIS20);
    }
}
