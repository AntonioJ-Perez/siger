package dto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Cofradia
 */
public class Fila implements Serializable {

  private String credito;
  private String nombre;
  private String refCobro;
  private String linea;
  private String tipoCredito;
  private String estatus;
  private String mesesVencidos;
  private String despacho;
  private String fechaInicioCredito;
  private String fechaVencimientoCred;
  private String disposicion;
  private String mensualidad;
  private String saldoInsoluto;
  private String saldoVencido;
  private String tasa;
  private String cuenta;
  private String fechaUltimoPago;
  private String fechaUltimoVencimientoPagado;
  private String idCliente;
  private String rfc;
  private String calle;
  private String colonia;
  private String estado;
  private String municipio;
  private String cp;

  private ArrayList<String> anio;
  private ArrayList<String> mes;
  private ArrayList<String> facMes;
  private ArrayList<String> monto;

  private ArrayList<String> refsAdicionales;

  private ArrayList<String> correos;

  private ArrayList<String> telsAdicionales;

  private ArrayList<String> direcsAdicionales;

  private String marcaje;
  private String fechaQuebranto;

  public Fila(String credito, String nombre, String refCobro, String linea, String tipoCredito, String estatus, String mesesVencidos, String despacho, String fechaInicioCredito, String fechaVencimientoCred, String disposicion, String mensualidad, String saldoInsoluto, String saldoVencido, String tasa, String cuenta, String fechaUltimoPago, String fechaUltimoVencimientoPagado, String idCliente, String rfc, String calle, String colonia, String estado, String municipio, String cp, ArrayList<String> anio, ArrayList<String> mes, ArrayList<String> facMes, ArrayList<String> monto, ArrayList<String> refsAdicionales, ArrayList<String> correos, ArrayList<String> telsAdicionales, ArrayList<String> direcsAdicionales, String marcaje, String fechaQuebranto) {
    this.credito = credito;
    this.nombre = nombre;
    this.refCobro = refCobro;
    this.linea = linea;
    this.tipoCredito = tipoCredito;
    this.estatus = estatus;
    this.mesesVencidos = mesesVencidos;
    this.despacho = despacho;
    this.fechaInicioCredito = fechaInicioCredito;
    this.fechaVencimientoCred = fechaVencimientoCred;
    this.disposicion = disposicion;
    this.mensualidad = mensualidad;
    this.saldoInsoluto = saldoInsoluto;
    this.saldoVencido = saldoVencido;
    this.tasa = tasa;
    this.cuenta = cuenta;
    this.fechaUltimoPago = fechaUltimoPago;
    this.fechaUltimoVencimientoPagado = fechaUltimoVencimientoPagado;
    this.idCliente = idCliente;
    this.rfc = rfc;
    this.calle = calle;
    this.colonia = colonia;
    this.estado = estado;
    this.municipio = municipio;
    this.cp = cp;
    this.anio = anio;
    this.mes = mes;
    this.facMes = facMes;
    this.monto = monto;
    this.refsAdicionales = refsAdicionales;
    this.correos = correos;
    this.telsAdicionales = telsAdicionales;
    this.direcsAdicionales = direcsAdicionales;
    this.marcaje = marcaje;
    this.fechaQuebranto = fechaQuebranto;
  }

  public static void main(String[] args) {
    // CREAMOS NUEVO OBJETO FILA
    Fila filaPrueba = new Fila("12345", "Detectives Privados Sabueso S. de R.L. de C.V.", "55511001", "CT EXPRESS", "CT EXPRESS PF", "VE", "1", "DELRIO", "2014-09-07", "2020-09-07", "50000", "798.61", "38020.85", "798.61", "15.0", "5224963287414727", "2015-07-07", "2015-07-07", "563987", "EEN150830F49", "Avenida 513 211-Local A", "San Juan de Aragón", "Distrito Federal", "Gustavo A. Madero", "07969", null, null, null, null, null, null, null, null, "13", "2015-07-07");
    // PREPARAMOS LOS ARRAYLIST PARA INSERTAR EN UN NUEVO OBJETO FILA
    // AÑOS
    filaPrueba.anio = new ArrayList<>();
    filaPrueba.anio.add("2015");
    filaPrueba.anio.add("2015");
    filaPrueba.anio.add("2015");
    // MESES
    filaPrueba.mes = new ArrayList<>();
    filaPrueba.mes.add("JUNIO");
    filaPrueba.mes.add("JULIO");
    filaPrueba.mes.add("AGOSTO");
    // CORREOS
    filaPrueba.correos = new ArrayList<>();
    filaPrueba.correos.add("el92@el92.com.mx");
    // TELEFONOS
    filaPrueba.telsAdicionales = new ArrayList<>();
    filaPrueba.telsAdicionales.add("5519331782");
    // IMPRIMIMOS LO QUE RETORNA FUNCION PARA CREAR CONSULTA SQL
    System.out.println(filaPrueba.crearSQL());
  }

  // METODO QUE CREA LA CONSULTA SQL CON LOS PARAMETROS DEL DTO
  public String crearSQL(){
    // CREAMOS LA CADENA QUE GUARDARA LA CONSULTA
    String consulta;
    // PRIMERO CREAMOS EL SUJETO
    consulta = "INSERT INTO sujeto (nombre_razon_social, rfc, eliminado) VALUES ('" + nombre + "', '" + rfc + "', 1); ";
    // GUARDAMOS EL ID DEL SUJETO INSERTADO
    consulta = consulta + "SET @idSujeto = (SELECT MAX(id_sujeto) from sujeto); ";
    // CREAMOS AL CLIENTE QUE SERA DUEÑO DEL CREDITO
    consulta = consulta + "INSERT INTO cliente (numero_cliente, sujetos_id_sujeto) VALUES ('" + idCliente + "', @idSujeto); ";
    // BUSCAMOS AL CLIENTE
    consulta = consulta + "SET @idCliente = (SELECT id_cliente FROM cliente WHERE sujetos_id_sujeto = @idSujeto); ";
    // BUSCAMOS A LA EMPRESA
    consulta = consulta + "SET @idEmpresa = (SELECT id_empresa FROM empresa WHERE nombre_corto = '" + despacho + "'); ";
    // BUSCAMOS EL PRODUCTO
    consulta = consulta + "SET @idProducto = (SELECT id_producto FROM producto WHERE nombre = '" + linea + "'); ";
    // BUSCAMOS EL SUBPRODUCTO
    consulta = consulta + "SET @idSubproducto = (SELECT id_subproducto FROM subproducto WHERE nombre = '" + tipoCredito + "'); ";
    // CREAMOS EL CREDITO
    consulta = consulta + "INSERT INTO credito (numero_credito, fecha_inicio, fecha_fin, fecha_quebranto, monto, mensualidad, tasa_interes, dias_mora, numero_cuenta, tipo_credito, empresas_id_empresa, productos_id_producto, subproductos_id_subproducto, clientes_id_cliente, gestores_id_gestor) VALUES ('" + credito + "', '" + fechaInicioCredito + "', '" + fechaVencimientoCred + "', '" + fechaQuebranto + "', " + disposicion + ", " + mensualidad + ", " + tasa + ", 0, " + cuenta + ", 1, @idEmpresa, @idProducto, @idSubproducto, @idCliente, 7); ";
    // BUSCAMOS EL ESTADO DE LA REPUBLICA DONDE SE ENCUENTRA EL DEUDOR
    consulta = consulta + "SET @idEstado = (SELECT id_estado FROM estado_republica WHERE nombre LIKE '%" + estado + "%'); ";
    // BUSCAMOS EL MUNICIPIO DONDE SE ENCUENTRA EL DEUDOR
    consulta = consulta + "SET @idMunicipio = (SELECT id_municipio FROM municipio WHERE nombre LIKE '%" + municipio + "%' AND id_estado_estados = @idEstado); ";
    // BUSCAMOS LA COLONIA DONDE SE ENCUENTRA EL DEUDOR SEGUN CODIGO POSTAL
    consulta = consulta + "SET @idColonia = (SELECT id_colonia FROM colonia WHERE codigo_postal =  " + cp + "  AND nombre LIKE '%" + colonia + "%' AND id_municipio_municipios = @idMunicipio LIMIT 1); ";
    // CREAMOS LA DIRECCION DE ESTE DEUDOR
    consulta = consulta + "INSERT INTO direccion (calle, sujetos_id_sujeto, municipio_id_municipio, estado_republica_id_estado, colonia_id_colonia) VALUES ('" + calle + "', @idSujeto, @idMunicipio, @idEstado, @idColonia); ";
    // CREAMOS EL TELEFONO PARA EL DEUDOR
    consulta = consulta + "INSERT INTO telefono (numero, tipo, sujetos_id_sujeto) VALUES ('" + refCobro + "', 'Referencia', @idSujeto); ";
    // CREAMOS EL CORREO ELECTRONICO DEL DEUDOR
    consulta = consulta + "INSERT INTO email (direccion, tipo, sujetos_id_sujeto) VALUES ('" + correos.get(0) + "', 'Referencia', @idSujeto);";
    // CREAMOS UN ARCHIVO DE TEXTO PLANO PARA GUARDAR LAS CONSULTAS
    crearArchivoSQL(consulta, "cargaPrueba");
    // SE RETORNA LA CONSULTA
    return consulta;
  }

  // METODO QUE CREA UN ARCHIVO DE TEXTO CON TODAS LAS CONSULTAS
  public void crearArchivoSQL(String consulta, String nombre){
    // RUTA DONDE SE GUARDARAN LAS CARGAS PROVISIONALMENTE
    String ruta = "C:\\\\NuevoSiger\\" + nombre + ".txt";
    // SE CREA UN ARCHIVO "VIRTUAL" PARA COMPROBAR SU EXISTENCIA
    File fichero = new File(ruta);
    // VERIFICAMOS SI EL ARCHIVO YA EXISTE
    if (fichero.exists()) {
      // SE ESCRIBE AL FINAL DEL ARCHIVO
      try{
        // SE CREA UN ARCHIVO DEL TIPO FILEWRITER CON LA OPCION TRUE PARA PODER AGREGAR DATOS AL FINAL DEL ARCHIVO Y NO SOBREESCRIBIRLO
        FileWriter archivo = new FileWriter(ruta, true);
        // SE ENVIA EL TEXTO PARA AGREGAR
        archivo.append("\n" + consulta);
        // CIERRE DEL ARCHIVO
        archivo.close();
      }
      catch (IOException ioe){
        ioe.printStackTrace();
      }
      
    } else {
      // SE CREA EL ARCHIVO
      try {
        // SE CREA EL ARCHIVO DE TEXTO
        fichero.createNewFile();
        // SE CREAN VARIABLES (ESCRITOR Y BUFFER) PARA PODER ESCRIBIR EN EL NUEVO FICHERO
        FileWriter w = new FileWriter(fichero);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);
        // SE ESCRIBE EN EL ARCHIVO
        wr.write(consulta);
        // CIERRE DEL ARCHIVO
        wr.close();
        bw.close();
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }

  }

  /**
   * @return {@code credito} el campo de la columna 1 en el archivo estándar de
   * excel para la carga de remesas en SigerWeb1
   */
  public String getCredito() {
    return credito;
  }

  public void setCredito(String credito) {
    this.credito = credito;
  }

  /**
   * @return {@code nombre} el campo de la columna 2 en el archivo estándar de
   * excel para la carga de remesas en SigerWeb1
   */
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * @return {@code refCobro} el campo de la columna 3 en el archivo estándar de
   * excel para la carga de remesas en SigerWeb1
   */
  public String getRefCobro() {
    return refCobro;
  }

  public void setRefCobro(String refCobro) {
    this.refCobro = refCobro;
  }

  /**
   * @return {@code linea} el campo de la columna 4 en el archivo estándar de
   * excel para la carga de remesas en SigerWeb1
   */
  public String getLinea() {
    return linea;
  }

  public void setLinea(String linea) {
    this.linea = linea;
  }

  /**
   * @return {@code tipoCredito} el campo de la columna 5 en el archivo estándar
   * de excel para la carga de remesas en SigerWeb1
   */
  public String getTipoCredito() {
    return tipoCredito;
  }

  public void setTipoCredito(String tipoCredito) {
    this.tipoCredito = tipoCredito;
  }

  /**
   * @return {@code estatus} el campo de la columna 6 en el archivo estándar de
   * excel para la carga de remesas en SigerWeb1
   */
  public String getEstatus() {
    return estatus;
  }

  public void setEstatus(String estatus) {
    this.estatus = estatus;
  }

  /**
   * @return {@code mesesVencidos} el campo de la columna 7 en el archivo
   * estándar de excel para la carga de remesas en SigerWeb1
   */
  public String getMesesVencidos() {
    return mesesVencidos;
  }

  public void setMesesVencidos(String mesesVencidos) {
    this.mesesVencidos = mesesVencidos;
  }

  /**
   * @return {@code despacho} el campo de la columna 8 en el archivo estándar de
   * excel para la carga de remesas en SigerWeb1
   */
  public String getDespacho() {
    return despacho;
  }

  public void setDespacho(String despacho) {
    this.despacho = despacho;
  }

  /**
   * @return {@code fechaInicioCredito} el campo de la columna 9 en el archivo
   * estándar de excel para la carga de remesas en SigerWeb1
   */
  public String getFechaInicioCredito() {
    return fechaInicioCredito;
  }

  public void setFechaInicioCredito(String fechaInicioCredito) {
    this.fechaInicioCredito = fechaInicioCredito;
  }

  /**
   * @return {@code fechaVencimientoCred} el campo de la columna 10 en el
   * archivo estándar de excel para la carga de remesas en SigerWeb1
   */
  public String getFechaVencimientoCred() {
    return fechaVencimientoCred;
  }

  public void setFechaVencimientoCred(String fechaVencimientoCred) {
    this.fechaVencimientoCred = fechaVencimientoCred;
  }

  /**
   * @return {@code disposicion} el campo de la columna 11 en el archivo
   * estándar de excel para la carga de remesas en SigerWeb1
   */
  public String getDisposicion() {
    return disposicion;
  }

  public void setDisposicion(String disposicion) {
    this.disposicion = disposicion;
  }

  /**
   * @return {@code mensualidad} el campo de la columna 12 en el archivo
   * estándar de excel para la carga de remesas en SigerWeb1
   */
  public String getMensualidad() {
    return mensualidad;
  }

  public void setMensualidad(String mensualidad) {
    this.mensualidad = mensualidad;
  }

  /**
   * @return {@code saldoInsoluto} el campo de la columna 13 en el archivo
   * estándar de excel para la carga de remesas en SigerWeb1
   */
  public String getSaldoInsoluto() {
    return saldoInsoluto;
  }

  public void setSaldoInsoluto(String saldoInsoluto) {
    this.saldoInsoluto = saldoInsoluto;
  }

  /**
   * @return {@code saldoVencido} el campo de la columna 14 en el archivo
   * estándar de excel para la carga de remesas en SigerWeb1
   */
  public String getSaldoVencido() {
    return saldoVencido;
  }

  public void setSaldoVencido(String saldoVencido) {
    this.saldoVencido = saldoVencido;
  }

  /**
   * @return {@code tasa} el campo de la columna 15 en el archivo estándar de
   * excel para la carga de remesas en SigerWeb1
   */
  public String getTasa() {
    return tasa;
  }

  public void setTasa(String tasa) {
    this.tasa = tasa;
  }

  /**
   * @return {@code cuenta} el campo de la columna 16 en el archivo estándar de
   * excel para la carga de remesas en SigerWeb1
   */
  public String getCuenta() {
    return cuenta;
  }

  public void setCuenta(String cuenta) {
    this.cuenta = cuenta;
  }

  /**
   * @return {@code fechaUltimoPago} el campo de la columna 17 en el archivo
   * estándar de excel para la carga de remesas en SigerWeb1
   */
  public String getFechaUltimoPago() {
    return fechaUltimoPago;
  }

  public void setFechaUltimoPago(String fechaUltimoPago) {
    this.fechaUltimoPago = fechaUltimoPago;
  }

  /**
   * @return {@code fechaUltimoVencimientoPagado} el campo de la columna 18 en
   * el archivo estándar de excel para la carga de remesas en SigerWeb1
   */
  public String getFechaUltimoVencimientoPagado() {
    return fechaUltimoVencimientoPagado;
  }

  public void setFechaUltimoVencimientoPagado(String fechaUltimoVencimientoPagado) {
    this.fechaUltimoVencimientoPagado = fechaUltimoVencimientoPagado;
  }

  /**
   * @return {@code idCliente} el campo de la columna 19 en el archivo estándar
   * de excel para la carga de remesas en SigerWeb1
   */
  public String getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(String idCliente) {
    this.idCliente = idCliente;
  }

  /**
   * @return {@code rfc} el campo de la columna 20 en el archivo estándar de
   * excel para la carga de remesas en SigerWeb1
   */
  public String getRfc() {
    return rfc;
  }

  public void setRfc(String rfc) {
    this.rfc = rfc;
  }

  /**
   * @return {@code calle} el campo de la columna 21 en el archivo estándar de
   * excel para la carga de remesas en SigerWeb1
   */
  public String getCalle() {
    return calle;
  }

  public void setCalle(String calle) {
    this.calle = calle;
  }

  /**
   * @return {@code colonia} el campo de la columna 22 en el archivo estándar de
   * excel para la carga de remesas en SigerWeb1
   */
  public String getColonia() {
    return colonia;
  }

  public void setColonia(String colonia) {
    this.colonia = colonia;
  }

  /**
   * @return {@code estado} el campo de la columna 23 en el archivo estándar de
   * excel para la carga de remesas en SigerWeb1
   */
  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  /**
   * @return {@code municipio} el campo de la columna 24 en el archivo estándar
   * de excel para la carga de remesas en SigerWeb1
   */
  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }

  /**
   * @return {@code cp} el campo de la columna 25 en el archivo estándar de
   * excel para la carga de remesas en SigerWeb1
   */
  public String getCp() {
    return cp;
  }

  public void setCp(String cp) {
    this.cp = cp;
  }

  /**
   * @return {@code anio} el ArrayList que contiene los hasta 3 registros
   * (columnas 26, 30 y 34 en el archivo estándar de excel) del año
   * correspondientes a las fechas de facturacion de los créditos que se
   * facturan por un tercero.
   */
  public ArrayList<String> getAnio() {
    return anio;
  }

  public void setAnio(ArrayList<String> anio) {
    this.anio = anio;
  }

  /**
   * @return {@code mes} el ArrayList que contiene los hasta 3 registros
   * (columnas 27, 31 y 35 en el archivo estándar de excel) del mes
   * correspondientes a las fechas de facturacion de los créditos que se
   * facturan por un tercero.
   */
  public ArrayList<String> getMes() {
    return mes;
  }

  public void setMes(ArrayList<String> mes) {
    this.mes = mes;
  }

  /**
   * @return {@code facMes} el ArrayList que contiene los hasta 3 registros
   * (columnas 28, 32 y 36 en el archivo estándar de excel) fac_mes
   * correspondientes a las fechas de facturacion de los créditos que se
   * facturan por un tercero.
   */
  public ArrayList<String> getFacMes() {
    return facMes;
  }

  public void setFacMes(ArrayList<String> facMes) {
    this.facMes = facMes;
  }

  /**
   * @return {@code facMes} el ArrayList que contiene los hasta 3 registros
   * (columnas 29, 33 y 37 en el archivo estándar de excel) del monto
   * correspondientes a las fechas de facturacion de los créditos que se
   * facturan por un tercero.
   */
  public ArrayList<String> getMonto() {
    return monto;
  }

  public void setMonto(ArrayList<String> monto) {
    this.monto = monto;
  }

  /**
   * @return {@code refsAdicionales} ArrayList que contiene los hasta 3
   * registros (columnas 38, 39 y 40) de las referencias.
   */
  public ArrayList<String> getRefsAdicionales() {
    return refsAdicionales;
  }

  public void setRefsAdicionales(ArrayList<String> refsAdicionales) {
    this.refsAdicionales = refsAdicionales;
  }

  /**
   * @return {@code correos} ArrayList que contiene los correos electrónicos de
   * contacto del deudor. <strong>Sólo existe una columna (la 41) en el archivo
   * estándar de excel para la carga de remesas en SigerWeb1.</strong>
   */
  public ArrayList<String> getCorreos() {
    return correos;
  }

  public void setCorreos(ArrayList<String> correos) {
    this.correos = correos;
  }

  /**
   * @return {@code telsAdicionales} ArrayList con los hasta 2 registros de
   * teléfonos de contacto adicionales del deudor, correspondientes a las
   * columnas 42 y 43 en el archivo estándar de excel para la carga de remesas
   * en SigerWeb1.
   */
  public ArrayList<String> getTelsAdicionales() {
    return telsAdicionales;
  }

  public void setTelsAdicionales(ArrayList<String> telsAdicionales) {
    this.telsAdicionales = telsAdicionales;
  }

  /**
   * @return {@code correos} ArrayList que contiene las direcciones adicionales
   * del deudor. <strong>Sólo existe una columna (la 44) en el archivo estándar
   * de excel para la carga de remesas en SigerWeb1.</strong>
   */
  public ArrayList<String> getDirecsAdicionales() {
    return direcsAdicionales;
  }

  public void setDirecsAdicionales(ArrayList<String> direcsAdicionales) {
    this.direcsAdicionales = direcsAdicionales;
  }

  /**
   * @return {@code marcaje} el campo de la columna 46 en el archivo estándar de
   * excel para la carga de remesas en SigerWeb1.
   */
  public String getMarcaje() {
    return marcaje;
  }

  public void setMarcaje(String marcaje) {
    this.marcaje = marcaje;
  }

  /**
   * @return {@code fechaQuebranto} el campo de la columna 47 en el archivo
   * estándar de excel para la carga de remesas en SigerWeb1
   */
  public String getFechaQuebranto() {
    return fechaQuebranto;
  }

  public void setFechaQuebranto(String fechaQuebranto) {
    this.fechaQuebranto = fechaQuebranto;
  }

}
