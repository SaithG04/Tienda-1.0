package com.tienda.entity;

/**
 *
 * @author VILLA
 */
public class Producto {
    
    private int id; //identificador unico del producto (no se podrá duplicar)
    private String nombre; //nombre del producto
    private int proveedor; //proveedor (vendedor) del producto
    private double cantidad; //Stock actual 
    private double precio;// precio actual
    private String medida;
    
    //Constructor con todos los argumentos para inicializar el obj de manera literal
    public Producto(int id, String nombre, int proveedor, double cantidad, double precio, String medida) {
        this.id = id;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
        this.precio = precio;
        this.medida = medida;
    }
    //Constructor solo para instanciar, para su luego set en cada atributo
    public Producto(){}

    //Gettes and Setters here
    
    public int getId() {
        return id;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
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

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
