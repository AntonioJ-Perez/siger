package impl;

import dao.ZonaDAO;
import dto.Municipio;
import dto.Zona;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.log.Logs;

/**
 *
 * @author Pablo
 */
public class ZonaIMPL implements ZonaDAO {

  @Override
  public Zona insertar(Zona zona) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    int id;

    try {
      sesion.save(zona);
      tx.commit();
      //log.info("Se insertó un nuevo usuaario");
    } catch (HibernateException he) {
      id = 0;
      if (tx != null) {
        tx.rollback();
      }
      he.printStackTrace();
      //         log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return zona;
  }

  @Override
  public List<Zona> buscarPorDespacho(int idDespacho) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    List<Zona> zonas = new ArrayList<>();
    String consulta = "SELECT * FROM zona WHERE id_despacho = " + idDespacho + ";";
    try {
      zonas = sesion.createSQLQuery(consulta).addEntity(Zona.class).list();
      Logs.log.info("Se ejecutó query: " + consulta);
    } catch (HibernateException he) {
      Logs.log.error(he.getStackTrace());
    } finally {
      cerrar(sesion);
    }
    return zonas;
  }

  @Override
  public List<String> buscarNombresZonas(int idDespacho) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    List<String> nombres = new ArrayList<>();
    String consulta = "SELECT nombre FROM zona WHERE id_despacho = " + idDespacho + ";";
    try {
      nombres = sesion.createSQLQuery(consulta).list();
      Logs.log.info("Se ejecutó query: " + consulta);
    } catch (HibernateException he) {
      Logs.log.error(he.getStackTrace());
    } finally {
      cerrar(sesion);
    }
    return nombres;
  }

  @Override
  public List<Integer> buscarIdsGestoresZonas() {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    List<Integer> gestores = new ArrayList<>();
    String consulta = "SELECT id_gestor FROM zona;";
    try {
      gestores = sesion.createSQLQuery(consulta).list();
      Logs.log.info("Se ejecutó query: " + consulta);
    } catch (HibernateException he) {
      Logs.log.error(he.getStackTrace());
    } finally {
      cerrar(sesion);
    }
    return gestores;
  }

  private void cerrar(Session sesion) {
    if (sesion.isOpen()) {
      sesion.close();
    }
  }
}
