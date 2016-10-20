/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import dao.QuincenaDAO;
import dto.Quincena;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.log.Logs;

/**
 *
 * @author Eduardo
 */
public class QuincenaIMPL implements QuincenaDAO{

  @Override
  public boolean insertar(Quincena quincena) {
    boolean ok;
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    try {
      sesion.save(quincena);
      tx.commit();
      ok = true;
    } catch (HibernateException he) {
      ok = false;
      if (tx != null) {
        tx.rollback();
      }
      Logs.log.error("No se pudo insertar la quincena " + quincena.getNombre() + " automaticamente.");
      Logs.log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return ok;
  }

  @Override
  public Quincena obtenerQuincenaActual() {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Quincena quincena;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
    String consulta = "SELECT * FROM quincena WHERE '" + df.format(new Date()) + "' BETWEEN fecha_inicio AND fecha_fin;";
    try {
      quincena = (Quincena) sesion.createSQLQuery(consulta).addEntity(Quincena.class).uniqueResult();
    } catch (HibernateException he) {
      quincena = null;
      Logs.log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return quincena;
  }
  
  private void cerrar(Session sesion) {
    if (sesion.isOpen()) {
      sesion.close();
    }
  }
  
}
