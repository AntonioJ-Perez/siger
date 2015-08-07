package dao;

import dto.Gestor;

/**
 * La interfaz {@code GestorDAO} contiene los prototipos de las 
 * funciones que se realizarán sobre la tabla _ de la base de datos.
 *
 * @author
 * @author
 * @author brionvega
 * @since SigerWeb2.0
 */
public interface GestorDAO {

    /**
     *
     * @param gestor
     * @return
     */
    public boolean insertar(Gestor gestor);
}
