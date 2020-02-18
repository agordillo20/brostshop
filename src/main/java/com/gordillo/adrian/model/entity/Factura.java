package com.gordillo.adrian.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int idPedido;
    int idProducto;

    public Factura() {
    }

    public Factura(int idPedido, int idProducto) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
}
