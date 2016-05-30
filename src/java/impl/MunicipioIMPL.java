/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import dao.MunicipioDAO;
import dto.Municipio;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;
import util.log.Logs;

/**
 *
 * @author Eduardo
 */
public class MunicipioIMPL implements MunicipioDAO {

  @Override
  public List<Municipio> buscarMunicipiosPorEstado(int idEstado) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    List<Municipio> municipios;
    String consulta = "SELECT * FROM municipio WHERE id_estado = " + idEstado + " ORDER BY nombre ASC;";
    try {
      municipios = sesion.createSQLQuery(consulta).addEntity(Municipio.class).list();

    } catch (HibernateException he) {
      municipios = null;
      Logs.log.error(consulta);
      Logs.log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return municipios;
  }

  @Override
  public List<Municipio> buscarTodo() {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    List<Municipio> municipios;
    String consulta = "SELECT * FROM municipio ORDER BY nombre ASC;";
    try {
      municipios = sesion.createSQLQuery(consulta).addEntity(Municipio.class).list();
    } catch (HibernateException he) {
      municipios = null;
      Logs.log.error(consulta);
      Logs.log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return municipios;
  }

  @Override
  public Municipio buscar(int idMunicipio) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Municipio municipio;
    try {
      municipio = (Municipio) sesion.get(Municipio.class, idMunicipio);
    } catch (HibernateException he) {
      municipio = null;
      Logs.log.error("No se pudo ontener lista de objetos: Municipio");
      Logs.log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return municipio;
  }

  @Override
  public Municipio buscar(String cadena) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Municipio municipio;
    try {
      municipio = (Municipio) sesion.createSQLQuery("SELECT * from municipio WHERE nombre LIKE '%" + cadena + "%';").addEntity(Municipio.class).uniqueResult();
    } catch (HibernateException he) {
      municipio = null;
      Logs.log.error("No se pudo obtener objeto: Municipio");
      Logs.log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return municipio;
  }

  @Override
  public Municipio buscarPorId(int idMunicipio) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Municipio municipio;
    try {
      municipio = (Municipio) sesion.get(Municipio.class, idMunicipio);
    } catch (HibernateException he) {
      municipio = null;
      Logs.log.error("No se pudo ontener lista de objetos: Municipio");
      Logs.log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return municipio;
  }

  @Override
  public Municipio buscarPorNombreMunicipioAbreviaturaEstado(String nombreMunicipio, String abreviaturaEstado) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Municipio municipio;
    try {
      municipio = (Municipio) sesion.createSQLQuery("SELECT * from municipio WHERE nombre = '" + nombreMunicipio + "' AND id_estado = (SELECT id_estado FROM estado_republica WHERE abreviatura = '" + abreviaturaEstado + "');").addEntity(Municipio.class).uniqueResult();
    } catch (HibernateException he) {
      municipio = null;
      Logs.log.error("No se pudo obtener objeto: Municipio");
      Logs.log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return municipio;
  }

  private void cerrar(Session sesion) {
    if (sesion.isOpen()) {
      sesion.close();
    }
  }
}
