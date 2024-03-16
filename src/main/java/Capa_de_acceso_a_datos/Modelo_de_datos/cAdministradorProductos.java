package Capa_de_acceso_a_datos.Modelo_de_datos;

import Capa_de_logica_de_negocio.LoginService;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author isai_
 */
public class cAdministradorProductos extends Conexion {

    private int idProducto;
    private String producto;
    private String categoria;
    private BigDecimal cantidad;
    private String unidad;

    final Class clase = cAdministradorProductos.class;

    Connection con;//Conectar a la base de datos
    Statement st;
    ResultSet rs;
    PreparedStatement psInsertar;
    DefaultTableModel modelo;

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public DefaultTableModel MostrarProductos(TableModel proveedores) throws ClassNotFoundException, SQLException {

//        con = Conectar(LoginService.oL.getUsuario(), LoginService.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM productos");

        modelo = (DefaultTableModel) proveedores;
        modelo.setRowCount(0);

        while (rs.next()) {
            Object[] prod = new Object[5];
            prod[0] = rs.getInt("IdProducto");
            prod[1] = rs.getString("Nombre");
            prod[2] = rs.getString("Categoria");
            prod[3] = rs.getBigDecimal("Stock");
            prod[4] = rs.getString("Unidad");
            modelo.addRow(prod);
        }
        Finalize();
        return modelo;

    }

    public List<String> ListarProveedores() throws ClassNotFoundException, SQLException {
        List<String> prov = new ArrayList<>();
//        con = Conectar(LoginService.oL.getUsuario(), LoginService.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from proveedores");
        while (rs.next()) {
            prov.add(rs.getString("Razon_Social"));
        }
        Finalize();
        return prov;
    }

    public List<String> ListarCategorias() throws ClassNotFoundException, SQLException {
        List<String> elementosUnicos = new ArrayList<>();

//        con = Conectar(LoginService.oL.getUsuario(), LoginService.oL.getContraseña());
        st = con.createStatement();
        String sql = "SELECT DISTINCT Categoria FROM productos";
        rs = st.executeQuery(sql);

        while (rs.next()) {
            String elemento = rs.getString("Categoria");
            elementosUnicos.add(elemento);
        }
        Finalize();
        return elementosUnicos;
    }

    public List<String> ListarProductosPorCategoria() throws ClassNotFoundException, SQLException {
        List<String> elementos = new ArrayList<>();
//        con = Conectar(LoginService.oL.getUsuario(), LoginService.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select * from productos where Categoria='" + categoria + "'");
        while (rs.next()) {
            elementos.add(rs.getString("Nombre"));
        }
        Finalize();
        return elementos;
    }

    public void InsertarNuevo() throws ClassNotFoundException, SQLException {

//        con = Conectar(LoginService.oL.getUsuario(), LoginService.oL.getContraseña());

        psInsertar = con.prepareStatement("INSERT INTO productos(IdProducto,Nombre,Categoria,Stock,Unidad) VALUES (?,?,?,?,?)");
        psInsertar.setInt(1, 0);
        psInsertar.setString(2, producto);
        psInsertar.setString(3, categoria);
        psInsertar.setBigDecimal(4, cantidad);
        psInsertar.setString(5, unidad);
        psInsertar.executeUpdate();
        Finalize();
    }

    public void AgregarStock() throws ClassNotFoundException, SQLException {
//        con = Conectar(LoginService.oL.getUsuario(), LoginService.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("SELECT Stock FROM productos WHERE Nombre= '" + producto + "'");
        while (rs.next()) {
            BigDecimal Oldstock = rs.getBigDecimal("Stock");
            if (Oldstock != null) {
                cantidad = Oldstock.add(cantidad);
            }
            System.out.println(cantidad);
            psInsertar = con.prepareStatement("UPDATE productos SET Stock=?,Unidad=? WHERE Nombre=?");
            psInsertar.setBigDecimal(1, cantidad);
            psInsertar.setString(2, unidad);
            psInsertar.setString(3, producto);
            psInsertar.executeUpdate();
        }
        Finalize();

    }

    public boolean DisminuirStock() throws ClassNotFoundException, SQLException {

//        con = Conectar(LoginService.oL.getUsuario(), LoginService.oL.getContraseña());
        st = con.createStatement();
        rs = st.executeQuery("Select Stock from productos where Nombre= '" + producto + "'");
        while (rs.next()) {
            BigDecimal stock = rs.getBigDecimal("Stock");
            if (stock != null) {
                if (stock.compareTo(cantidad) < 0) {
                    oA.mostrarError(clase, "Stock insuficiente.", null);
                    return false;
                } else {
                    cantidad = stock.subtract(cantidad);
                    psInsertar = con.prepareStatement("UPDATE productos SET Stock=? WHERE Nombre=?");
                    psInsertar.setBigDecimal(1, cantidad);
                    psInsertar.setString(2, producto);
                    psInsertar.executeUpdate();
                }
            }
        }
        Finalize();
        return true;
    }

    public void EliminarProducto() throws ClassNotFoundException, SQLException {

//        con = Conectar(LoginService.oL.getUsuario(), LoginService.oL.getContraseña());
        st = con.createStatement();
        st.executeUpdate("DELETE FROM productos WHERE IdProducto='" + idProducto + "'");
        Finalize();
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
