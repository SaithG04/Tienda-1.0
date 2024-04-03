package com.tienda.entity;

import java.sql.Date;

/**
 *
 * @author isai_
 */
public class Transaccion {

    private int id;
    private String tipo;
    private int id_detalle;
    private Date fecha_registro;
    private double monto;

    public Transaccion(int id, String tipo, int id_detalle, Date fecha_registro, double monto) {
        this.id = id;
        this.tipo = tipo;
        this.id_detalle = id_detalle;
        this.fecha_registro = fecha_registro;
        this.monto = monto;
    }

    public Transaccion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    
}
