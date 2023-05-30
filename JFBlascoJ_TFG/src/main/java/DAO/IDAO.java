package DAO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interfaz para los DAOs de los POJOs de la aplicación.
 * @author José Francisco Blasco Jiménez.
 * @param <T> Clase del objeto con el que se va a trabajar.
 */
public interface IDAO<T> {
    public boolean insertar (T o);
    public boolean borrar (String id);
    public boolean modificar (String id, T nuevo);
    public T consultarPorId (String id);
    public ArrayList<T> filtrar (HashMap<String, Object> datos);
    public ArrayList<T> consultarTodos(boolean ordenar);
    public Integer calculaId();
}
