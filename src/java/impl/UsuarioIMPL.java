package impl;

import dao.UsuarioDAO;
import dto.Usuario;
import java.util.List;
//import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.constantes.Perfiles;
import util.log.Logs;

/**
 * La clase {@code UsuarioIMPL} contiene la implementación de las operaciones
 * sobre la tabla usuarios, requeridas por la interfaz {@code UsuarioDAO}.
 * Dichas operaciones son realizadas por Hibernate.
 *
 * @author
 * @author
 * @author brionvega
 * @since SigerWeb2.0
 */
public class UsuarioIMPL implements UsuarioDAO {
  //   private static Logger log = Logger.getLogger(Logs.class);

  /**
   *
   *
   * @param usuario
   * @return
   */
  @Override
  public int insertar(Usuario usuario) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    int id;

    try {
      sesion.save(usuario);
      tx.commit();
      id = usuario.getIdUsuario();
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
    return id;
  }

  /**
   *
   *
   * @param usuario
   * @return
   */
  @Override
  public boolean editar(Usuario usuario) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    boolean ok;

    try {
      sesion.update(usuario);
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

  /**
   *
   *
   * @param usuario
   * @return
   */
  @Override
  public boolean eliminar(Usuario usuario) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    boolean ok;

    try {
      usuario.setPerfil(Perfiles.ELIMINADO);
      sesion.update(usuario);
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

  /**
   *
   *
   * @param idUsuario
   * @return
   */
  @Override
  public Usuario buscar(int idUsuario) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    Usuario usuario;

    try {
      usuario = (Usuario) sesion.get(Usuario.class, idUsuario);
      // obtuvo el usuario, solo se muestra si no ha sido eliminado:
      if (usuario != null) {
        if (usuario.getPerfil() == Perfiles.ELIMINADO) {
          usuario = null;
        }
      }
    } catch (HibernateException he) {
      usuario = null;
      he.printStackTrace();
    } finally {
      cerrar(sesion);
    }

    return usuario;
  }

  /**
   * Busca a todos los usuarios que no hayan sido eliminados y cuyo perfil haya
   * sido confirmado, en su caso. Un usuario eliminado tiene perfil = 0.
   *
   * @param nombreLogin
   * @param password
   * @return
   */
  @Override
  public Usuario buscar(String nombreLogin, String password) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    Usuario usuario;

    try {
      usuario = (Usuario) sesion.createQuery("from Usuario u where "
              + "u.perfil != " + Perfiles.ELIMINADO + " and u.perfil != "
              + Perfiles.GESTOR_NO_CONFIRMADO + " and u.nombreLogin = '"
              + nombreLogin + "' and u.password = '"
              + password + "'").uniqueResult();
    } catch (HibernateException he) {
      usuario = null;
      he.printStackTrace();
    } finally {
      cerrar(sesion);
    }
    return usuario;
  }

  /**
   * Busca a todos los usuarios que no hayan sido eliminados, un usuario
   * eliminado tiene perfil = 0.
   *
   * @param nombreLogin
   * @param correo
   * @return
   */
  @Override
  public Usuario buscarPorCorreo(String nombreLogin, String correo) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    Usuario usuario;

    try {
      usuario = (Usuario) sesion.createQuery("from Usuario u where "
              + "u.perfil != " + Perfiles.ELIMINADO + " and u.nombreLogin = '"
              + nombreLogin + "' and u.correo = '"
              + correo + "'").uniqueResult();

    } catch (HibernateException he) {
      usuario = null;
      he.printStackTrace();
    } finally {
      cerrar(sesion);
    }

    return usuario;
  }

  /**
   * Busca a todos los usuarios que no hayan sido eliminados. Un usuario
   * eliminado tiene perfil = 0.
   *
   * @return
   */
  @Override
  public List<Usuario> buscarTodo() {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    List<Usuario> listaUsuario;

    try {
      listaUsuario = sesion.createQuery("from Usuario u"
              + " where u.perfil != " + Perfiles.ELIMINADO
              + " and u.perfil != " + Perfiles.GESTOR_NO_CONFIRMADO).list();
    } catch (HibernateException he) {
      listaUsuario = null;
      he.printStackTrace();
    } finally {
      cerrar(sesion);
    }

    return listaUsuario;
  }

  /**
   * Busca únicamente a los usuarios no confirmados.
   *
   * @return
   */
  @Override
  public List<Usuario> buscarUsuariosNoConfirmados() {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    List<Usuario> listaUsuarioNoConfirmados;

    try {
      listaUsuarioNoConfirmados = sesion.createQuery("from Usuario u"
              + " where u.perfil = " + Perfiles.GESTOR_NO_CONFIRMADO).list();
    } catch (HibernateException he) {
      listaUsuarioNoConfirmados = null;
      he.printStackTrace();
    } finally {
      cerrar(sesion);
    }

    return listaUsuarioNoConfirmados;
  }

  /**
   *
   *
   * @param nombreLogin
   * @return
   */
  @Override
  public Usuario buscarNombreLogin(String nombreLogin) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    Usuario usuario;

    try {
      usuario = (Usuario) sesion.createQuery("from Usuario u where "
              + "u.perfil != " + Perfiles.ELIMINADO
              + " and u.nombreLogin = '" + nombreLogin + "'").uniqueResult();
    } catch (HibernateException he) {
      usuario = null;
      he.printStackTrace();
    } finally {
      cerrar(sesion);
    }

    return usuario;
  }

  /**
   *
   *
   * @param correo
   * @return
   */
  @Override
  public Usuario buscarCorreo(String correo) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    Usuario usuario;

    try {
      usuario = (Usuario) sesion.createQuery("from Usuario u where "
              + "u.perfil != " + Perfiles.ELIMINADO
              + " and u.correo = '" + correo + "'").uniqueResult();
    } catch (HibernateException he) {
      usuario = null;
      he.printStackTrace();
    } finally {
      cerrar(sesion);
    }

    return usuario;
  }

  @Override
  public Usuario buscarUsuarioPorIdGestor(int idGestor) {
    Session sesion = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = sesion.beginTransaction();
    Usuario usuario;
    try {
      usuario = (Usuario) sesion.createSQLQuery("SELECT * FROM usuario WHERE id_usuario = (SELECT id_usuario FROM gestor WHERE id_gestor = " + idGestor + ");").addEntity(Usuario.class).uniqueResult();
    } catch (HibernateException he) {
      usuario = null;
      he.printStackTrace();
    } finally {
      cerrar(sesion);
    }
    return usuario;
  }

  /**
   *
   *
   * @param
   */
  private void cerrar(Session sesion) {
    if (sesion.isOpen()) {
      sesion.close();
    }
  }
}
