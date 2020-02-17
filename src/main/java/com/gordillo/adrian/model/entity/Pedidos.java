package com.gordillo.adrian.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date fechaPedido;
    @OneToMany(mappedBy = "productos", fetch = FetchType.LAZY)
    private List<Producto> productos;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    @JsonIgnore
    private Usuario usuario;

    public Pedidos() {
    }

    public Pedidos(Date fechaPedido, List<Producto> productos, Usuario usuario) {
        this.fechaPedido = fechaPedido;
        this.productos = productos;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
