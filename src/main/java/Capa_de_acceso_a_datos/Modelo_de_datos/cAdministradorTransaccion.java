package Capa_de_acceso_a_datos.Modelo_de_datos;

import Capa_de_logica_de_negocio.LoginService;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author isai_
 */
public class cAdministradorTransaccion extends Conexion {

    private int idTransaccion;
    private String tipoTrans;
    private int idProducto;
    private BigDecimal monto;
    private BigDecimal cantidad;
    private String unidad;
    private int idProveedor;
    private String fechaCad;
    private java.sql.Timestamp fechaTrans;
    private int idUsuario;

    Connection con;//Conectar a la base de datos
    Statement st;
    ResultSet rs;
    PreparedStatement psInsertar = null;
    DefaultTableModel modelo;

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getTipoTrans() {
        return tipoTrans;
    }

    public void setTipoTrans(String tipoTrans) {
        this.tipoTrans = tipoTrans;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String producto) throws ClassNotFoundException, SQLException {
//        con = Conectar(LoginService.oL.getUsuario(), LoginService.oL.getContraseña());
        st = con.createStatement();
        String query = "SELECT IdProducto FROM productos WHERE Nombre = '" + producto + "'";
        rs = st.executeQuery(query);
        if (rs.next()) {
            idProducto = rs.getInt("IdProducto");
        }
        Finalize();
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String proveedor) throws ClassNotFoundException, SQLException {
        if (proveedor != null) {
//            con = Conectar(LoginService.oL.getUsuario(), LoginService.oL.getContraseña());
            st = con.createStatement();
            String query = "SELECT IdProveedor FROM proveedores WHERE Razon_Social = '" + proveedor + "'";
            rs = st.executeQuery(query);
            if (rs.next()) {
                idProveedor = rs.getInt("IdProveedor");
            }
            Finalize();
        } else {
            idProveedor = -1;
        }
    }

    public String getFechaCad() {
        return fechaCad;
    }

    public void setFechaCad(String fechaCad) {
        this.fechaCad = fechaCad;
    }

    public java.sql.Timestamp getFechaTrans() {
        return fechaTrans;
    }

    public void setFechaTrans(java.sql.Timestamp fechaTrans) {
        this.fechaTrans = fechaTrans;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String username) throws ClassNotFoundException, SQLException {
//        con = Conectar(LoginService.oL.getUsuario(), LoginService.oL.getContraseña());
        st = con.createStatement();
        String query = "SELECT id_usuario FROM usuarios WHERE username = '" + username + "'";
        rs = st.executeQuery(query);
        if (rs.next()) {
            idUsuario = rs.getInt("id_usuario");
        }
        Finalize();
    }

    public void InsertarTransaccion() throws ClassNotFoundException, SQLException {
//        con = Conectar(LoginService.oL.getUsuario(), LoginService.oL.getContraseña());

        psInsertar = con.prepareStatement("INSERT INTO transacciones(IdTransaccion,TipoTransaccion,Id_Producto,Monto,Cantidad,Unidad,Id_Proveedor,FechaCaducidad,FechaTransaccion,Id_Usuario_Transaccion) VALUES (?,?,?,?,?,?,?,?,?,?)");
        psInsertar.setInt(1, idTransaccion);
        psInsertar.setString(2, tipoTrans);
        psInsertar.setInt(3, idProducto);
        psInsertar.setBigDecimal(4, monto);
        psInsertar.setBigDecimal(5, cantidad);
        psInsertar.setString(6, unidad);
        if (idProveedor == -1) {
            psInsertar.setObject(7, null, Types.INTEGER);
        } else {
            psInsertar.setInt(7, idProveedor);
        }
        psInsertar.setString(8, fechaCad);
        psInsertar.setTimestamp(9, fechaTrans);
        psInsertar.setInt(10, idUsuario);
        psInsertar.executeUpdate();
    }

    public DefaultTableModel MostrarTransacciones(TableModel transacciones) throws ClassNotFoundException, SQLException {

//        con = Conectar(LoginService.oL.getUsuario(), LoginService.oL.getContraseña());
        st = con.createStatement();
        String query = "SELECT T.TipoTransaccion,T.Monto,T.Cantidad,T.Unidad,P.Razon_Social,T.FechaCaducidad,T.FechaTransaccion,"
                + "U.username FROM transacciones T LEFT JOIN proveedores P ON T.Id_Proveedor = P.IdProveedor"
                + " INNER JOIN usuarios U ON T.Id_Usuario_Transaccion = U.id_usuario WHERE T.Id_Producto =" + idProducto;
        rs = st.executeQuery(query);
        modelo = (DefaultTableModel) transacciones;
        modelo.setRowCount(0);

        while (rs.next()) {
            Object[] prec = new Object[8];
            prec[0] = rs.getString("TipoTransaccion");
            prec[1] = rs.getBigDecimal("Monto");
            prec[2] = rs.getBigDecimal("Cantidad");
            prec[3] = rs.getString("Unidad");
            prec[4] = rs.getString("Razon_Social");
            prec[5] = rs.getString("FechaCaducidad");
            prec[6] = rs.getString("FechaTransaccion");
            prec[7] = rs.getString("username");
            modelo.addRow(prec);
        }

        Finalize();
        return modelo;
    }

    public DefaultTableModel MonstrarHistorialTransacciones(TableModel transacciones) throws ClassNotFoundException, SQLException {

//        con = Conectar(LoginService.oL.getUsuario(), LoginService.oL.getContraseña());
        st = con.createStatement();
        String query = "SELECT T.IdTransaccion,P.Nombre,T.TipoTransaccion,T.Monto,T.Cantidad,T.Unidad,Pr.Razon_Social,T.FechaCaducidad,T.FechaTransaccion,U.username"
                + " FROM registro_eliminacion P"
                + " INNER JOIN historial_transacciones T ON P.IdProducto = T.Id_Producto"
                + " LEFT JOIN proveedores Pr ON T.Id_Proveedor = Pr.IdProveedor"
                + " INNER JOIN usuarios U ON T.Id_Usuario_Transaccion = U.id_usuario"
                + " UNION"
                + " SELECT T.IdTransaccion,Pd.Nombre,T.TipoTransaccion,T.Monto,T.Cantidad,T.Unidad,P.Razon_Social,T.FechaCaducidad,T.FechaTransaccion,U.username"
                + " FROM transacciones T"
                + " LEFT JOIN proveedores P ON T.Id_Proveedor = P.IdProveedor"
                + " INNER JOIN usuarios U ON T.Id_Usuario_Transaccion = U.id_usuario"
                + " INNER JOIN productos Pd ON T.Id_Producto = Pd.IdProducto"
                + " ORDER BY IdTransaccion";
        rs = st.executeQuery(query);
        modelo = (DefaultTableModel) transacciones;
        modelo.setRowCount(0);
        modelo.setColumnCount(0);
        String[] columnas = {"ID", "Producto", "TipoTransaccion", "Monto", "Cantidad", "Unidad", "Proveedor", "FechaCaducidad",
            "FechaTransaccion", "Usuario"};
        for (int i = 0; i < 10; i++) {
            modelo.addColumn(columnas[i]);
        }

        while (rs.next()) {
            Object[] prec = new Object[10];
            prec[0] = rs.getString("IdTransaccion");
            prec[1] = rs.getString("Nombre");
            prec[2] = rs.getString("TipoTransaccion");
            prec[3] = rs.getBigDecimal("Monto");
            prec[4] = rs.getBigDecimal("Cantidad");
            prec[5] = rs.getString("Unidad");
            prec[6] = rs.getString("Razon_Social");
            prec[7] = rs.getString("FechaCaducidad");
            prec[8] = rs.getString("FechaTransaccion");
            prec[9] = rs.getString("username");
            modelo.addRow(prec);
        }
        Finalize();
        return modelo;
    }

    void Finalize() throws SQLException {
        if (con != null) {
            con.close();
        }
        if (psInsertar != null) {
            psInsertar.close();
        }
        if (rs != null) {
            rs.close();
        }
    }
}
