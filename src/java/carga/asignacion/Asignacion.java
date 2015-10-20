package carga.asignacion;

import dto.Fila;
import java.io.*;
import java.sql.*;
import java.util.List;
import util.constantes.Directorios;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import util.log.Logs;

/**
 *
 * @authors Pablo y el Cisne
 */
public class Asignacion {

  /**
   *
   */
  private ArrayList<ArrayList<String>> credsListaPrincipal;
  private ArrayList<Credito> asigListaPrincipal;

  /**
   * CREAMOS UNA LISTA AUXILIAR PARA GUARDAR LOS SIGUIENTES DATOS EN ESE ORDEN:
   * <ul>
   * <li>NUMERO DE CREDITO</li>
   * <li>SALDO VENCIDO</li>
   * <li>NUMERO DE CLIENTE</li>
   * <li>CLAVE DEL GESTOR ASIGNADO</li>
   * </ul>
   */
  private ArrayList<ArrayList<String>> credsNuevosTotales;
  private ArrayList<Credito> asigNuevosTotales;

  /**/
  private List<String> ordenGestores;
  private List<Gestor> gestores;

  public Asignacion() {
  }

  /**
   * METODO QUE RECIBE LAS FILAS DEL ARCHIVO DE EXCEL Y CREA UNA LISTA CON LOS
   * DATOS QUE NOS INTERESAN
   */
  public void recibeFilas(List<Fila> listaFilas) {
// CREAMOS UNA LISTA. PARA GUARDAR LAS LISTAS AUXILIARES
    credsListaPrincipal = new ArrayList<>();
    // LLENAMOS LA LISTA CON LOS DATOS 
    for (int i = 0; i < listaFilas.size(); i++) {
      // CREAMOS UNA LISTA AUXILIAR PARA GUARDAR LOS SIGUIENTES DATOS EN ESE ORDEN:
      //  - NUMERO DE CREDITO
      //  - SALDO VENCIDO
      //  - NUMERO DE CLIENTE
      //  - CLAVE DEL GESTOR ASIGNADO
      List<String> aux = new ArrayList<>();
      aux.add(listaFilas.get(i).getCredito());
      aux.add(listaFilas.get(i).getSaldoVencido());
      aux.add(listaFilas.get(i).getIdCliente());
      // LLENAMOS LA COLUMNA FALTANTE CON EL ID DEL GESTOR DEL CREDITO
      for (int j = 0; j < listaFilas.size(); j++) {
        // INICIAMOS UNA CONEXION A BASE DE DATOS
        try {
          DriverManager.registerDriver(new com.mysql.jdbc.Driver());
          Connection conexion = DriverManager.getConnection("jdbc:mysql://10.0.0.26:3306/sigerbd?zeroDateTimeBehavior=convertToNull", "cofradia", "cofradiaDB");
          Statement consulta = conexion.createStatement();
          // CONSULTA PARA OBTENER EL ID DEL GESTOR
          String query = "SELECT gestores_id_gestor FROM credito WHERE numero_credito = '" + aux.get(0) + "';";
          ResultSet r = consulta.executeQuery(query);
          // SI LA CONSULTA REGRESO ALGUN RESULTADO, LO ASIGNAMOS AL ARREGLO
          while (r.next()) {
            aux.add(r.getString("gestores_id_gestor"));
          }
          // AGREGAMOS LA LISTA AUXILIAR A LA LISTA
          credsListaPrincipal.add((ArrayList<String>) aux);
          // CERRAMOS LA CONEXION
          r.close();
          consulta.close();
          conexion.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * METODO QUE REALIZA LAS ASIGNACIONES AUTOMATICAS: CREDITOS CONSERVADOS Y
   * NUEVOS CREDITOS
   */
  public void asignacionPorDefecto() {
    /**
     * Duplicado
     */
    credsNuevosTotales = (ArrayList<ArrayList<String>>) credsListaPrincipal.clone();
    asigNuevosTotales = (ArrayList<Credito>) asigListaPrincipal.clone();

// OBTENEMOS EL ID DEL GESTOR DE CADA LISTA EN LA LISTA. ELIMINACION DE CONSERVADOS
    for (int i = 0; i < credsNuevosTotales.size(); i++) {
      //// SI EL VALOR ES DIFERENTE DE 0, ES QUE YA TIENE GESTOR ASIGNADO
      if (!credsNuevosTotales.get(i).get(3).equals("0")) {
        // ELIMINAMOS ESA FILA DE LA LISTA
        credsNuevosTotales.remove(i);
        // DISMINUIMOS LA VARIABLE i EN UNA UNIDAD PARA NO DESBALANCEAR EL FOR
        i--;
      }
    }
    // VERIFICAMOS SI LOS CREDITOS YA TIENEN UN CLIENTE ASOCIADO. ELIMINACION DE NUEVOS CREDITOS
    for (int i = 0; i < credsNuevosTotales.size(); i++) {
      // PONEMOS UN VALOR DEFAULT POR SI HAY NULL
      String idGestor = "0";
      // INICIAMOS UNA CONEXION A BASE DE DATOS
      try {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection conexion = DriverManager.getConnection("jdbc:mysql://10.0.0.26:3306/sigerbd?zeroDateTimeBehavior=convertToNull", "cofradia", "cofradiaDB");
        Statement consulta = conexion.createStatement();
        // CONSULTA PARA SABER SI UN CLIENTE YA TIENE CREDITOS EN EL SISTEMA, REGRESA EL ID DEL GESTOR
        String query = "SELECT gestores_id_gestor FROM credito WHERE clientes_id_cliente = (SELECT id_cliente FROM cliente WHERE numero_cliente = '" + credsNuevosTotales.get(i).get(2) + "' LIMIT 1) LIMIT 1;";
        ResultSet r = consulta.executeQuery(query);
        while (r.next()) {
          idGestor = r.getString("gestores_id_gestor");
        }
        // CREAMOS UNA VARIABLE PARA GUARDAR EL ID DEL CREDITO A ACTUALIZAR
        String idCredito = null;
        // BUSCAMOS EL ID PARA ESTE NUMERO DE CREDITO
        query = "SELECT id_credito FROM credito WHERE numero_credito = '" + credsNuevosTotales.get(i).get(2) + "' LIMIT 1;";
        r = consulta.executeQuery(query);
        while (r.next()) {
          idCredito = r.getString("id_credito");
        }
        // ACTUALIZAMOS EL ID DEL GESTOR EN LA BASE DE DATOS PARA ESTE CREDITO
        query = "UPDATE credito SET gestores_id_gestor = " + idGestor + " WHERE id_credito = " + idCredito + ";";
        consulta.executeUpdate(query);
        // ELIMINAMOS ESA FILA DE LA LISTA
        credsNuevosTotales.remove(i);
        // DISMINUIMOS LA VARIABLE i EN UNA UNIDAD PARA NO DESBALANCEAR EL FOR
        i--;
        // CERRAMOS LA CONEXION
        r.close();
        consulta.close();
        conexion.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * METODO QUE HACE LA ROTACION DE LOS GESTORES EN EL ARCHIVO Y PREPARA UN
   * ARREGLO CON EL ORDEN DE LOS MISMOS
   */
  public void rotacionGestores() {
    ordenGestores = new ArrayList<>();
    gestores = new ArrayList<>();
    // LEEMOS EL ARCHIVO PARA OBTENER EL ORDEN ACTUAL DE LOS GESTORES
    String archivo = Directorios.RUTA_ASIGNACION + "rotacion.txt";
    try {
      FileReader fr = new FileReader(archivo);
      BufferedReader bf = new BufferedReader(fr);
      String linea;
      // MIENTRAS LA LINEA NO ESTE VACIA, AGREGAREMOS EL NUMERO DE GESTOR AL ARREGLO
      while ((linea = bf.readLine()) != null) {
        ordenGestores.add(linea);
        gestores.add(new Gestor(Integer.parseInt(linea), 0, 0));
      }
      // CERREMOS EL ARCHIVO PARA LECTURA
      bf.close();
      fr.close();
      // AHORA HACEMOS LA ROTACION DE LOS GESTORES UN LUGAR HACIA ABAJO
      // GUARDAMOS EL ID DEL GESTOR EN EL ULTIMO ELEMENTO
      String ultimoGestor = ordenGestores.get(ordenGestores.size() - 1);
      System.out.println("ULTIMO GESTOR: " + ultimoGestor);
      // RECORREMOS LOS VALORES A PARTIR DEL ULTIMO ELEMENTO HASTA EL SEGUNDO
      for (int i = ordenGestores.size() - 1; i > 0; i--) {
        ordenGestores.set(i, ordenGestores.get(i - 1));
      }
      // RECORREMOS EL ULTIMO GESTOR QUE GUARDAMOS AL PRIMER LUGAR
      ordenGestores.set(0, ultimoGestor);
      // IMPRIMIMOS EL ID DE LOS GESTORES EN EL NUEVO ORDEN
      for (int i = 0; i < ordenGestores.size(); i++) {
        System.out.println(ordenGestores.get(i));
      }
      // ESCRIBIMOS ESTOS CAMBIOS EN EL ARCHIVO
      BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
      for (int i = 0; i < ordenGestores.size(); i++) {
        bw.write(ordenGestores.get(i) + "\n");
      }
      bw.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  /**
   * Ordena {@code nuevosTotales} de mayor a menor con base en el monto de su
   * saldo vencido.
   */
  public void ordenarDecreceiente() {
    Collections.sort(credsNuevosTotales, new ComparadorFila());
  }

  /**
   *
   */
  public void asignarNuevosTotales() {
    this.ordenarDecreceiente();
    int contAsignados = 0;
    /**
     * Calculamos el número de iteraciones. Por iteracion se reparten dos
     * créditos a cada gestor.
     */
    int iteraciones = this.credsNuevosTotales.size() / (2 * gestores.size());

    /**
     * Fase 1/3: Se reparten de a dos en dos los créditos entre los gestores,
     * dandole a cada uno el mayor y el menor disponibles en cada iteración.
     * Pueden sobrar hasta (2*n)-1 creditos por repartir.
     */
    for (int i = 0; i < iteraciones; i++) {
      /**
       * Duplicado
       */
      credsNuevosTotales.get(i).set(3, String.valueOf(gestores.get(i).getId()));
      credsNuevosTotales.get(credsNuevosTotales.size() - (i + 1)).set(3, String.valueOf(gestores.get(i).getId()));

      asigNuevosTotales.get(i).setIdGestor(gestores.get(i).getId());
      asigNuevosTotales.get(asigNuevosTotales.size() - (i + 1)).setIdGestor(gestores.get(i).getId());

      gestores.get(i).getCredsNuevosTotales().add(asigNuevosTotales.get(i));
      gestores.get(i).getCredsNuevosTotales().add(asigNuevosTotales.get(asigNuevosTotales.size() - (i + 1)));
      contAsignados += 2;
    }

    int restantes = this.credsNuevosTotales.size() % (2 * gestores.size());

    /**
     * Quedan a lo más (2*n)-1 créditos por repartir
     */
    if (restantes > 0) {
      ArrayList<ArrayList<String>> credsDisponibles = new ArrayList<>();
      ArrayList<Credito> asigNuevosTotalesDisponibles = new ArrayList<>();

      for (int i = 0; i < restantes; i++) {
        credsDisponibles.add(credsNuevosTotales.get((iteraciones * gestores.size()) + i));
        asigNuevosTotalesDisponibles.add(asigNuevosTotales.get((iteraciones * gestores.size()) + i));
      }

      /**
       * Fase 2/3: Se reparte una vez más entre todos los gestores, ahora
       * comenzando por el último en la lista y terminando por el primero.
       * Pueden sobrar hasta n-1 creditos por repartir.
       */
      for (int i = 0; credsDisponibles.size() >= gestores.size(); i++) {
        /**
         * Asignamos en el arreglo de creditos a reemplazar
         */
        credsDisponibles.get(0).set(3, String.valueOf(gestores.get(gestores.size() - ((i % gestores.size()) + 1)).getId()));
        /**
         * Asignamos en el arreglo de creditos nuevo
         */
        asigNuevosTotalesDisponibles.get(0).setIdGestor(gestores.get(gestores.size() - ((i % gestores.size()) + 1)).getId());

        gestores.get(gestores.size() - ((i % gestores.size()) + 1)).getCredsNuevosTotales().add(asigNuevosTotales.get(0));

        credsDisponibles.remove(0);
        asigNuevosTotalesDisponibles.remove(0);
        contAsignados++;
      }
      /**
       * Fase 3/3: Se reparten los hasta n-1 creditos restantes. Se reparten
       * otorgando al gestor que tenga el menor monto total por nuevos totales
       * el mayor crédito restante cada vez.
       */
      for (int i = 0; credsDisponibles.size() > 0; i++) {
        Collections.max(credsDisponibles, new ComparadorFila()).set(3, Collections.min(gestores, new Comparator<Gestor>() {
          @Override
          public int compare(Gestor g1, Gestor g2) {
            if (g1.getNuevoTotalAsignado() < g2.getNuevoTotalAsignado()) {
              return -1;
            } else if (g1.getNuevoTotalAsignado() == g2.getNuevoTotalAsignado()) {
              return 0;
            } else {
              return 1;
            }
          }
        }));
        contAsignados++;
      }

      if (contAsignados == credsNuevosTotales.size()) {
        Logs.log.debug("Se asigno el total (" + contAsignados + ") de créditos satisfactoriamente.");
      } else {
        Logs.log.debug("Solo se asignaron satisfactoriamente " + contAsignados + " de " + credsNuevosTotales.size() + " créditos.");
      }
    }
  }
}

class ComparadorFila implements Comparator {

  @Override
  public int compare(Object o1, Object o2) {
    if (Float.parseFloat(((ArrayList<String>) o1).get(2)) < Float.parseFloat(((ArrayList<String>) o2).get(2))) {
      return -1;
    } else if (Float.parseFloat(((ArrayList<String>) o1).get(2)) == Float.parseFloat(((ArrayList<String>) o2).get(2))) {
      return 0;
    } else {
      return 1;
    }
  }
}