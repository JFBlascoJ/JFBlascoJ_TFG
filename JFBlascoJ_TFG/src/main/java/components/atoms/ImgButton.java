package components.atoms;

import assets.lookandfeel.Botones;
import java.awt.Cursor;
import java.awt.event.*;
import javax.swing.*;

/**
 * Clase personalizada para los botones utilizados en la aplicación.
 * Hereda de JLabel.
 * @author José Francisco Blasco Jiménez
 */
public class ImgButton extends JLabel {

    private ImageIcon normal, hover, pressed;
    private boolean flag;
    private int tipo;
    private MouseListener listener;
    private Botones botones;

    /**
     * Crea un nuevo ImgButton
     */
    public ImgButton() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setIcon(pressed);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setIcon(pressed);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setIcon(normal);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(hover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(normal);
            }
        });
    }

    /**
     * Devuelve la imagen asignada al botón para su estado natural.
     * @return Imagen del botón en estado natural.
     */
    public ImageIcon getNormal() {
        return normal;
    }
    
    /**
     * Setea la imagen que tendrá el botón en su estado normal.
     * @param ico Imagen para su estado natural.
     */
    public void setNormal(ImageIcon ico) {
        normal = ico;
        setIcon(normal);
    }

    /**
     * Setea la imagen que tendrá el botón al colocar el ratón sobre él.
     * @param ico Imagen para el estado "hover".
     */
    public void setHover(ImageIcon ico) {
        hover = ico;        
    }

    /**
     * Setea la imagen que tendrá el botón al ser pulsado.
     * @param ico Imagen para el estado "pressed"
     */
    public void setPressed(ImageIcon ico) {
        pressed = ico;
    }

    /**
     * Setea el tipo del botón y las imágenes que va a utilizar según el mismo
     * @param tipo Tipo del botón
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
        setButton();
    }
     
    /**
     * Setea, según el tipo, las tres imágenes que va a utilizar el evento para cada uno de sus tres estados:
     *  - Normal
     *  - Hover
     *  - Pressed
     */
    private void setButton() {
        switch (tipo) {
            case 0:
                setNormal(Botones.GUARDAR);
                setHover(Botones.GUARDAR_HOVER);
                setPressed(Botones.GUARDAR_PRESSED);
                break;
            case 1:
                setNormal(Botones.LIMPIAR);
                setHover(Botones.LIMPIAR_HOVER);
                setPressed(Botones.LIMPIAR_PRESSED);
                break;
            case 2:
                setNormal(Botones.MODIFICAR);
                setHover(Botones.MODIFICAR_HOVER);
                setPressed(Botones.MODIFICAR_PRESSED);
                break;
            case 3:
                setNormal(Botones.RESTABLECER);
                setHover(Botones.RESTABLECER_HOVER);
                setPressed(Botones.RESTABLECER_PRESSED);
                break;
            case 4:
                setNormal(Botones.ELIMINAR_BLANCO);
                setHover(Botones.ELIMINAR_BLANCO_HOVER);
                setPressed(Botones.ELIMINAR_BLANCO_PRESSED);
                break;
            case 5:
                setNormal(Botones.ELIMINAR_NARANJA);
                setHover(Botones.ELIMINAR_NARANJA_HOVER);
                setPressed(Botones.ELIMINAR_NARANJA_PRESSED);
                break;
            case 6:
                setNormal(Botones.VOLVER);
                setHover(Botones.VOLVER_HOVER);
                setPressed(Botones.VOLVER_PRESSED);
                break;
            case 7:
                setNormal(Botones.FILTRO_APLICAR);
                setHover(Botones.FILTRO_APLICAR_HOVER);
                setPressed(Botones.FILTRO_APLICAR_PRESSED);
                break;
            case 8:
                setNormal(Botones.FILTRO_LIMPIAR);
                setHover(Botones.FILTRO_LIMPIAR_HOVER);
                setPressed(Botones.FILTRO_LIMPIAR_PRESSED);
                break;
            case 9:
                setNormal(Botones.FILTRARID);
                setHover(Botones.FILTRARID_HOVER);
                setPressed(Botones.FILTRARID_PRESSED);
                break;
            case 10:
                setNormal(Botones.FLECHA_IZQ);
                setHover(Botones.FLECHA_IZQ_HOVER);
                setPressed(Botones.FLECHA_IZQ_PRESSED);
                break;
            case 11:
                setNormal(Botones.FLECHA_DCHA);
                setHover(Botones.FLECHA_DCHA_HOVER);
                setPressed(Botones.FLECHA_DCHA_PRESSED);
                break;
            case 12:
                setNormal(Botones.RIDER_ADD);
                setHover(Botones.RIDER_ADD_HOVER);
                setPressed(Botones.RIDER_ADD_PRESSED);
                break;
            case 13:
                setNormal(Botones.CALENDAR);
                setHover(Botones.CALENDAR_HOVER);
                setPressed(Botones.CALENDAR_PRESSED);
                break;
            case 14:
                setNormal(Botones.HOME_EVENTOS_ADD);
                setHover(Botones.HOME_EVENTOS_ADD_HOVER);
                setPressed(Botones.HOME_EVENTOS_ADD_PRESSED);
                break;
            case 15:
                setNormal(Botones.HOME_EVENTOS_LIST);
                setHover(Botones.HOME_EVENTOS_LIST_HOVER);
                setPressed(Botones.HOME_EVENTOS_LIST_PRESSED);
                break;
            case 16:
                setNormal(Botones.HOME_INFORMES);
                setHover(Botones.HOME_INFORMES_HOVER);
                setPressed(Botones.HOME_INFORMES_PRESSED);
                break;
            case 17:
                setNormal(Botones.MODAL_ACEPTAR);
                setHover(Botones.MODAL_ACEPTAR_HOVER);
                setPressed(Botones.MODAL_ACEPTAR_PRESSED);
                break;
            case 18:
                setNormal(Botones.MODAL_SI);
                setHover(Botones.MODAL_SI_HOVER);
                setPressed(Botones.MODAL_SI_PRESSED);
                break;
            case 19:
                setNormal(Botones.MODAL_NO);
                setHover(Botones.MODAL_NO_HOVER);
                setPressed(Botones.MODAL_NO_PRESSED);
                break;
            default:
        }
    }

}
