package com.tienda.entity;

import java.sql.Date;

/**
 *
 * @author isai_
 */
public class DetallePedido {

    private int id;
    private String estado;
    private int id_proveedor;
    private int id_producto;
    private Date fecha_pedido;
    private Date fecha_entrega;
    private double cantidad;
    private String unidad;
    private double monto_total;
    private String observaciones;

    public DetallePedido(int id, String estado, int id_proveedor, int id_producto, Date fecha_pedido, Date fecha_entrega, double cantidad, String unidad, double monto_total, String observaciones) {
        this.id = id;
        this.estado = estado;
        this.id_proveedor = id_proveedor;
        this.id_producto = id_producto;
        this.fecha_pedido = fecha_pedido;
        this.fecha_entrega = fecha_entrega;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.monto_total = monto_total;
        this.observaciones = observaciones;
    }

    public DetallePedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "DetallePedido{" + "id=" + id + ", estado=" + estado + ", id_proveedor=" + id_proveedor + ", id_producto=" + id_producto + ", fecha_pedido=" + fecha_pedido + ", fecha_entrega=" + fecha_entrega + ", cantidad=" + cantidad + ", unidad=" + unidad + ", monto_total=" + monto_total + ", observaciones=" + observaciones + '}';
    }
    
    
}
