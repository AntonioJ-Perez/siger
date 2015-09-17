package beans;

import dto.Fila;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import util.constantes.Directorios;
import util.log.Logs;
import util.Query;

/**
 *
 * @author Cofradía
 */
@ManagedBean
@ViewScoped
public class CargaBean implements Serializable {

  private String nombreArchivo;
  private int numeroDeFilas;
  private int numeroDeColumnas;
  private Workbook archivoExcel;
  private Sheet hojaExcel;
  private Fila fila;

  // Otro bean
  @ManagedProperty(value = "#{filaBean}")
  private FilaBean filaBean;

  public CargaBean() {
  }

  public boolean subirArchivo(FileUploadEvent e) throws IOException {
    UploadedFile archivoRecibido = e.getFile();

    nombreArchivo = nombrarArchivo(archivoRecibido.getFileName());

    byte[] bytes = null;

    boolean ok;

    try {
      if (archivoRecibido != null) {
        bytes = archivoRecibido.getContents();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(nombreArchivo)));
        stream.write(bytes);
        stream.close();
      }
      Logs.log.info("Se carga archivo al Servidor: " + nombreArchivo);
      ok = true;
    } catch (IOException ioe) {
      Logs.log.error("No se pudo cargar el archivo de la remesa: " + nombreArchivo);
      Logs.log.error(ioe.getMessage());
      ok = false;
    }

    return ok;
  }

  private String nombrarArchivo(String elArchivo) {
    Calendar calendario = new GregorianCalendar();

    return Directorios.RUTA_REMESAS + "remesa"
            + Integer.toString(calendario.get(Calendar.YEAR))
            + Integer.toString(1 + calendario.get(Calendar.MONTH))
            + Integer.toString(calendario.get(Calendar.DATE))
            + Integer.toString(calendario.get(Calendar.HOUR_OF_DAY))
            + Integer.toString(calendario.get(Calendar.MINUTE))
            + Integer.toString(calendario.get(Calendar.SECOND)) + "."
            + FilenameUtils.getExtension(elArchivo);
  }

  public int leerArchivoExcel() {
    try {
      archivoExcel = Workbook.getWorkbook(new File(nombreArchivo));
      hojaExcel = archivoExcel.getSheet(0);
      numeroDeFilas = hojaExcel.getRows();
      numeroDeColumnas = hojaExcel.getColumns();
      Logs.log.info("Se lee: " + nombreArchivo + ". Filas: " + numeroDeFilas + ". Columnas = " + numeroDeColumnas);
    } catch (Exception ioe) {
      Logs.log.error("No se pudo leer archivo: " + nombreArchivo);
      Logs.log.error(ioe.getMessage());
      numeroDeFilas = -1;
    }

    return numeroDeFilas - 1;
  }

  public boolean crearArchivoSql() {
    String archivoSql = FilenameUtils.getBaseName(nombreArchivo);
    archivoSql = Directorios.RUTA_REMESAS + archivoSql + ".sql";
    // Crea los objetos fila tomando los datos del archivo excel
    fila = new Fila();
    filaBean = new FilaBean();
    for (int i = 1; i < numeroDeFilas; i++) {
      crearFila(i);
      // Valida
      filaBean.setFilaActual(fila);
      if (validarFila(i + 1)) {
        guadarQueryEnArchivo(fila.crearSQL(), archivoSql);
      } else {
        return false;
      }
    }
    return true;
  }

  public boolean leerArchivoSql() {
    String archivoSql = FilenameUtils.getBaseName(nombreArchivo);
    archivoSql = Directorios.RUTA_REMESAS + archivoSql + ".sql";
    try {
      FileReader fr = new FileReader(archivoSql);
      int lineas = 0;
      BufferedReader bf = new BufferedReader(fr);
      String lineaSql = "";
      while ((lineaSql = bf.readLine()) != null) {
        lineas++;
        Query.ejecutaQuery(lineaSql);
        System.out.println(lineaSql);
      }
      return true;
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
      return false;
    } catch (IOException ioe) {
      ioe.printStackTrace();
      return false;
    }
  }

  private boolean validarFila(int fila) {
    try {
      //filaBean.validarNumCred();
      filaBean.validarNombreRazonSoc();
      //filaBean.validarRefCobro();
      //filaBean.validarIdProducto();
      //filaBean.validarIdSubproducto();
      //filaBean.validarEstatus();
      //filaBean.validarMesesVencidos();
      //filaBean.validarFechaInicio();
      //filaBean.validarFechaFin();
      return true;
    } catch (Exception e) {
      Logs.log.error("Ha ocurrido un error en la fila: " + fila);
      Logs.log.error(e.getMessage());
      Logs.log.error(filaBean.getFilaActual().toString());
      return false;
    }
  }

  private void crearFila(int numFila) {

    //fila.setCredito(hojaExcel.getCell(0, numFila).getContents());
    fila.setNombre(hojaExcel.getCell(1, numFila).getContents());
    //fila.setRefCobro(hojaExcel.getCell(2, numFila).getContents());
    //fila.setLinea(hojaExcel.getCell(3, numFila).getContents());
    //fila.setTipoCredito(hojaExcel.getCell(4, numFila).getContents());
    //fila.setEstatus(hojaExcel.getCell(5, numFila).getContents());
    //fila.setMesesVencidos(hojaExcel.getCell(6, numFila).getContents());
    //fila.setDespacho(hojaExcel.getCell(7, numFila).getContents());
    //fila.setFechaInicioCredito(hojaExcel.getCell(8, numFila).getContents());
    //fila.setFechaVencimientoCred(hojaExcel.getCell(9, numFila).getContents());
    //fila.setDisposicion(hojaExcel.getCell(10, numFila).getContents());
    //fila.setMensualidad(hojaExcel.getCell(11, numFila).getContents());
    //fila.setSaldoInsoluto(hojaExcel.getCell(12, numFila).getContents());
    //fila.setSaldoVencido(hojaExcel.getCell(13, numFila).getContents());
    //fila.setTasa(hojaExcel.getCell(14, numFila).getContents());
    //fila.setCuenta(hojaExcel.getCell(15, numFila).getContents());
    //fila.setFechaUltimoPago(hojaExcel.getCell(16, numFila).getContents());
    //fila.setFechaUltimoVencimientoPagado(hojaExcel.getCell(17, numFila).getContents());
    fila.setIdCliente(hojaExcel.getCell(18, numFila).getContents());
    fila.setRfc(hojaExcel.getCell(19, numFila).getContents());
    fila.setCalle(hojaExcel.getCell(20, numFila).getContents());
    fila.setColonia(hojaExcel.getCell(21, numFila).getContents());
    fila.setEstado(hojaExcel.getCell(22, numFila).getContents());
    fila.setMunicipio(hojaExcel.getCell(23, numFila).getContents());
    fila.setCp(hojaExcel.getCell(24, numFila).getContents());
  }

  private void guadarQueryEnArchivo(String query, String nombreArchivoSql) {
    // SE CREA UN ARCHIVO "VIRTUAL" PARA COMPROBAR SU EXISTENCIA
    File fichero = new File(nombreArchivoSql);
    // VERIFICAMOS SI EL ARCHIVO YA EXISTE
    if (fichero.exists()) {
      // SE ESCRIBE AL FINAL DEL ARCHIVO
      try {
        // SE CREA UN ARCHIVO DEL TIPO FILEWRITER CON LA OPCION TRUE PARA PODER AGREGAR DATOS AL FINAL DEL ARCHIVO Y NO SOBREESCRIBIRLO
        FileWriter fileWriter = new FileWriter(nombreArchivoSql, true);
        // SE ENVIA EL TEXTO PARA AGREGAR
        fileWriter.append("\n" + query);
        // CIERRE DEL ARCHIVO
        fileWriter.close();
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }

    } else {
      // SE CREA EL ARCHIVO
      try {
        // SE CREA EL ARCHIVO DE TEXTO
        fichero.createNewFile();
        // SE CREAN VARIABLES (ESCRITOR Y BUFFER) PARA PODER ESCRIBIR EN EL NUEVO FICHERO
        FileWriter fileWriter = new FileWriter(fichero);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        // SE ESCRIBE EN EL ARCHIVO
        printWriter.write(query);
        // CIERRE DEL ARCHIVO
        printWriter.close();
        bufferedWriter.close();
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }

  public int getNumeroDeFilas() {
    return numeroDeFilas;
  }

  public void setNumeroDeFilas(int numeroDeFilas) {
    this.numeroDeFilas = numeroDeFilas;
  }

  public int getNumeroDeColumnas() {
    return numeroDeColumnas;
  }

  public void setNumeroDeColumnas(int numeroDeColumnas) {
    this.numeroDeColumnas = numeroDeColumnas;
  }

  public Workbook getArhivoExcel() {
    return archivoExcel;
  }

  public void setArhivoExcel(Workbook arhivoExcel) {
    this.archivoExcel = arhivoExcel;
  }

  public FilaBean getFilaBean() {
    return filaBean;
  }

  public void setFilaBean(FilaBean filaBean) {
    this.filaBean = filaBean;
  }

}