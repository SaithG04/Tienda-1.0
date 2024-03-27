package com.tienda.entity;

import java.sql.Date;

/**
 *
 * @author isai_
 */
public class Proveedor {

    private int id;
    private String ruc;
    private String razon_social;
    private String descripcion;
    private String direccion;
    private String telefono;
    private String correo;
    private String web;
    private String contacto;
    private String categoria;
    private String estado;
    private Date fecha_registro;
    private String observaciones;

    public Proveedor(int id, String ruc, String razon_social, String descripcion, String direccion, String telefono, String correo, String web, String contacto, String categoria, String estado, Date fecha_registro, String observaciones) {
        this.id = id;
        this.ruc = ruc;
        this.razon_social = razon_social;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.web = web;
        this.contacto = contacto;
        this.categoria = categoria;
        this.estado = estado;
        this.fecha_registro = fecha_registro;
        this.observaciones = observaciones;
    }

    public Proveedor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "id=" + id + ", ruc=" + ruc + ", razon_social=" + razon_social + ", descripcion=" + descripcion + ", direccion=" + direccion + ", telefono=" + telefono + ", correo=" + correo + ", web=" + web + ", contacto=" + contacto + ", categoria=" + categoria + ", estado=" + estado + ", fecha_registro=" + fecha_registro + ", observaciones=" + observaciones + '}';
    }

}
