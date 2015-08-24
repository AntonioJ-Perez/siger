/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import dao.ColoniaDAO;
import dto.Colonia;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.log.Logs;

/**
 *
 * @author antonio
 */
public class ColoniaIMPL implements ColoniaDAO {

  @Override
  public List<Colonia> buscarColoniasPorMunicipio(int idMunicipio) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    List<Colonia> colonias;
    String consulta = "select * from colonia where id_municipio_municipios = " + idMunicipio + " order by nombre asc;";
    try {
      colonias = sesion.createSQLQuery(consulta).addEntity(Colonia.class).list();
      System.out.println(consulta);

    } catch (HibernateException he) {
      colonias = null;
      he.printStackTrace();
    } finally {
      cerrar(sesion);
    }
    return colonias;
  }

  @Override
  public Colonia buscar(int idColonia) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        Colonia colonia;
        
        try { 
            colonia = (Colonia) sesion.get(Colonia.class, idColonia);
        } catch(HibernateException he) {
            colonia = null;
            Logs.log.error("No se pudo ontener lista de objetos: Colonia");
            Logs.log.error(he.getMessage());
        } finally {
            cerrar(sesion);
        }
        
        return colonia;
  }
  
  

  private void cerrar(Session sesion) {
    if (sesion.isOpen()) {
      sesion.close();
    }
  }

}