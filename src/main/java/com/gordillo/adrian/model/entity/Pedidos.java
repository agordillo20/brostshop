package com.gordillo.adrian.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pedidos")
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date fechaPedido;
    private String codigoPedido;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    @JsonIgnore
    private Usuario usuario;

    public Pedidos() {
    }

    public Pedidos(String codigoPedido, Date fechaPedido, Usuario usuario) {
        this.codigoPedido = codigoPedido;
        this.fechaPedido = fechaPedido;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
