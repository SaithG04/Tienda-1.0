package com.tienda.entity;

/**
 *
 * @author VILLA
 */
public class Productos {
    
    private int id; //identificador unico del producto (no se podrá duplicar)
    private String nombre; //nombre del producto
    private String proveedor; //proveedor (vendedor) del producto
    private int cantidad; //Stock actual 
    private double precio; // precio actual

    //Constructor con todos los argumentos para inicializar el obj de manera literal
    public Productos(int id, String nombre, String proveedor, int cantidad, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    //Constructor solo para instanciar, para su luego set en cada atributo
    public Productos(){}

    //Gettes and Setters here
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
