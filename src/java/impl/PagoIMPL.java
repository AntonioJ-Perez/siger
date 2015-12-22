/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import dao.PagoDAO;
import dto.Pago;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.constantes.Convenios;
import util.constantes.Pagos;
import util.log.Logs;

/**
 *
 * @author Eduardo
 */
public class PagoIMPL implements PagoDAO{

  @Override
  public boolean insertar(Pago pago) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    boolean ok;
    try {
      sesion.save(pago);
      tx.commit();
      Logs.log.info("Se agrego un pago");
      ok = true;
    } catch (HibernateException he) {
      pago = null;
      if (tx != null) {
        tx.rollback();
      }
      Logs.log.error("No se pudo insertar pago");
      Logs.log.error(he.getMessage());
      ok = false;
    } finally {
      cerrar(sesion);
    }
    return ok;
  }

  @Override
  public boolean editar(Pago pago) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    boolean ok;
    try {
      sesion.update(pago);
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
  public List<Pago> buscarPagosPorConvenioActivo(int idConvenio) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    List<Pago> pagos;
    try {
      pagos = sesion.createSQLQuery("SELECT * FROM pago WHERE id_convenio_pago = " + idConvenio + ";").addEntity(Pago.class).list();
    } catch (HibernateException he) {
      pagos = null;
      Logs.log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return pagos;
  }

  @Override
  public List<Pago> buscarPagosPorCredito(int idCredito) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    List<Pago> pagos;
    try {
      pagos = sesion.createSQLQuery("SELECT * FROM pago WHERE id_convenio_pago IN (SELECT id_convenio_pago FROM convenio_pago WHERE id_credito = " + idCredito + " AND estatus = " + Convenios.FINALIZADO + ");").addEntity(Pago.class).list();
    } catch (HibernateException he) {
      pagos = null;
      Logs.log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return pagos;
  }

  @Override
  public List<Pago> pagosPorDespacho(int idDespacho) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    List<Pago> pagos;
    try {
      pagos = sesion.createSQLQuery("SELECT * FROM pago WHERE id_gestor IN (SELECT id_gestor FROM gestor WHERE id_usuario IN (SELECT id_usuario FROM usuario WHERE id_despacho = " + idDespacho + ")) AND estatus != " + Pagos.PENDIENTE + ";").addEntity(Pago.class).list();
    } catch (HibernateException he) {
      pagos = null;
      Logs.log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return pagos;
  }

  @Override
  public List<Pago> pagosPorRevisarPorDespacho(int idDespacho) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    List<Pago> pagos;
    try {
      pagos = sesion.createSQLQuery("SELECT * FROM pago WHERE id_gestor IN (SELECT id_gestor FROM gestor WHERE id_usuario IN (SELECT id_usuario FROM usuario WHERE id_despacho = " + idDespacho + ")) AND estatus = " + Pagos.PENDIENTE + ";").addEntity(Pago.class).list();
    } catch (HibernateException he) {
      pagos = null;
      Logs.log.error(he.getMessage());
    } finally {
      cerrar(sesion);
    }
    return pagos;
  }
  
  private void cerrar(Session sesion) {
    if (sesion.isOpen()) {
      sesion.close();
    }
  }
  
}