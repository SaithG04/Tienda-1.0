package com.tienda.data_transfer_layer;

/**
 * La clase UserDTO representa un objeto de transferencia de datos para la
 * información del usuario.
 */
public class ProductoDTO {

    private String producto; // Nombre de usuario
    private String proveedor; // Contraseña del usuario
    private int cantidad;
    private double precio;

    public ProductoDTO(String producto, String proveedor, int cantidad, double precio) {
        this.producto = producto;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public ProductoDTO() {
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
   
}
