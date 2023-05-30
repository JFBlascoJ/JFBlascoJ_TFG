package assets.objects;

import components.atoms.txt.RoundTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import assets.resources.Operaciones;

/**
 * Clase que contiene diferentes listeners utilizados en la aplicación. Estos
 * listeners se encargan de realizar acciones en respuesta a eventos
 * específicos.
 *
 * @author José Francisco Blasco Jiménez
 */
public class Listeners {

    private final String REGEXP_FECHA;
    private final String REGEXP_MAIL;
    private final DocumentListener LISTENER_MAIL;
    private final DocumentListener LISTENER_FECHA;
    private final DocumentListener LISTENER_REQUERIDO;
    private final KeyAdapter LISTENER_NUMEROS;
    private Comunes comunes;

    public Listeners(Comunes c) {
        comunes = c;
        REGEXP_FECHA = "^\\d{2}/\\d{2}/\\d{4}$";
        REGEXP_MAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+\\.[A-Za-z]+$";

        // Valida el formato de un correo electrónico.
        LISTENER_MAIL = new DocumentListener() {
            /**
             * Método que se ejecuta cada vez que se introduce texto en el
             * campo.
             *
             * @param e Evento de documento.
             */
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                JTextField txt = (JTextField) e.getDocument().getProperty("textField");
                RoundTextField panel = (RoundTextField) e.getDocument().getProperty("panel");

                if (panel != null) {
                    // Mientras el texto no coincida con la expresión regular, no será correcto.
                    panel.setCorrecto(txt.getText().matches(REGEXP_MAIL) || panel.getText().equals(""));
                }
            }
            /**
             * Método que se ejecuta cada vez que se elimina texto en el campo.
             * Llama a insertUpdate(), ya que sirve para lo mismo.
             *
             * @param e Evento de documento.
             */
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                insertUpdate(e);
            }

            /**
             * Se invoca cuando se producen cambios en el documento que no
             * afectan directamente al contenido del texto, como cambios en los
             * atributos del estilo del documento.
             *
             * @param e Evento de documento.
             */
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
            }
        };

        // Se asegura de que la fecha no coincidad con ningún evento ya almacenado, así como de que no sea anterior a la fecha actual.
        LISTENER_FECHA = new DocumentListener() {
            /**
             * Método que se ejecuta cada vez que se introduce texto en el
             * campo.
             *
             * @param e Evento de documento.
             */
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                JTextField txt = (JTextField) e.getDocument().getProperty("textField");
                RoundTextField panel = (RoundTextField) e.getDocument().getProperty("panel");

                if (panel != null) {
                    if (!txt.getText().equals("")) {
                        LocalDate fecha = Operaciones.deCadenaAFecha(txt.getText()), fechaEvento;
                        boolean fechaDisponible = comunes.getDAOEV().comprobarDisponibilidad(fecha),
                                fechaCorrecta = fecha.isAfter(LocalDate.now());
                        if (!fechaDisponible && panel.isResultado()) {
                            fechaEvento = panel.getEVento().getFecha();
                            fechaDisponible = fecha.equals(fechaEvento);
                        }
                        panel.setCorrecto(fechaDisponible && fechaCorrecta);
                    } else {
                        panel.setCorrecto(false);
                    }
                }
            }
            
            /**
             * Método que se ejecuta cada vez que se elimina texto en el campo.
             * Llama a insertUpdate(), ya que sirve para lo mismo.
             *
             * @param e Evento de documento.
             */
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                JTextField textField = (JTextField) e.getDocument().getProperty("owner");
                insertUpdate(e);
            }

            /**
             * Se invoca cuando se producen cambios en el documento que no
             * afectan directamente al contenido del texto, como cambios en los
             * atributos del estilo del documento.
             *
             * @param e Evento de documento.
             */
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
            }
        };

        // Se asegura de que un campo requerido no esté vacío.
        LISTENER_REQUERIDO = new DocumentListener() {
            /**
             * Método que se ejecuta cada vez que se introduce texto en el
             * campo.
             *
             * @param e Evento de documento.
             */
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                JTextField txt = (JTextField) e.getDocument().getProperty("textField");
                RoundTextField panel = (RoundTextField) e.getDocument().getProperty("panel");

                if (panel != null) {
                    panel.coloreaComponente(txt.getText().equals(""), false);
                }
            }

            /**
             * Método que se ejecuta cada vez que se elimina texto en el campo.
             * Llama a insertUpdate(), ya que sirve para lo mismo.
             *
             * @param e Evento de documento.
             */
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                JTextField textField = (JTextField) e.getDocument().getProperty("owner");
                insertUpdate(e);
            }

            /**
             * Se invoca cuando se producen cambios en el documento que no
             * afectan directamente al contenido del texto, como cambios en los
             * atributos del estilo del documento.
             *
             * @param e Evento de documento.
             */
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
            }
        };

        // Permite únicamente la entrada de números.
        LISTENER_NUMEROS = new KeyAdapter() {
            /**
             * Se ejectua cada vez qe se pulsa una tecla en el campo en cuestión.
             * @param e Evento de teclado.
             */
            public void keyTyped(KeyEvent e) {
                JTextField aux = (JTextField) e.getComponent();
                char caracter = e.getKeyChar();
                int cod = e.getKeyCode();

                /* Ignora la pulsación de la tecla en los siguientes casos:
                 * - Si la fecha está completa (a no ser que queramos borrar).
                 * - Si no se introduce un número.
                 */
                if (aux.getText().length() > 8 || ((cod != KeyEvent.VK_BACK_SPACE || cod != KeyEvent.VK_DELETE)
                        && (caracter < '0' || caracter > '9'))) {
                    e.consume();
                }
            }
        };
    }

    public DocumentListener getMAIL() {
        return LISTENER_MAIL;
    }

    public DocumentListener getFECHA() {
        return LISTENER_FECHA;
    }

    public DocumentListener getREQUERIDO() {
        return LISTENER_REQUERIDO;
    }

    public KeyAdapter getNUMEROS() {
        return LISTENER_NUMEROS;
    }

}
