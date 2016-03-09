/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import dao.CreditoDAO;
import dao.HistorialDAO;
import dto.Historial;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.log.Logs;

/**
 *
 * @author Eduardo
 */
public class HistorialIMPL implements HistorialDAO {

  @Override
  public boolean insertarHistorial(int idCredito, String evento) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    boolean ok;

    try {
      CreditoDAO creditoDao = new CreditoIMPL();
      Date fecha = new Date();
      Historial h = new Historial();
      h.setCredito(creditoDao.buscarCreditoPorId(idCredito));
      h.setEvento(evento);
      h.setFecha(fecha);
      sesion.save(h);
      tx.commit();
      ok = true;
    } catch (HibernateException he) {
      ok = false;
      if (tx != null) {
        tx.rollback();
      }
      he.printStackTrace();
    } finally {
      cerrar(sesion);
    }

    return ok;
  }

  @Override
  public List<Historial> buscarHistorialPorIdCredito(int idCredito) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    List<Historial> historial;
    try {
      historial = sesion.createSQLQuery("SELECT * FROM historial WHERE id_credito = " + idCredito + ";").addEntity(Historial.class).list();
    } catch (HibernateException he) {
      historial = null;
      Logs.log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return historial;
  }

  @Override
  public boolean verificarCampioCampañaCredito(int idCredito) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    List<Historial> historial;
    boolean ok = false;
    Date f = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String fecha = df.format(f);
    try {
      historial = sesion.createSQLQuery("SELECT * FROM historial WHERE id_credito = " + idCredito + " AND fecha = '" + fecha + "' AND evento = 'AUTOMATICO. CAMBIO DE CONVENIO.';").addEntity(Historial.class).list();
      if (historial.size() > 0) {
        ok = true;
      }
    } catch (HibernateException he) {
      historial = null;
      Logs.log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return ok;
  }

  private void cerrar(Session sesion) {
    if (sesion.isOpen()) {
      sesion.close();
    }
  }
}
