package com.gordillo.adrian.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codProducto;
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMarca")
    private Marca marca;

    private double precio;
    private int cantidad;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idDistribuidor")
    private Distribuidor distribuidor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "especificaciones", referencedColumnName = "id")
    private Especificaciones especificaciones;

    private String foto;
    private boolean activo;

    public Producto() {
    }

    public Producto(String codProducto, String nombre, double precio, int cantidad) {
        this.codProducto = codProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
