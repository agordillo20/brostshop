package com.gordillo.adrian.service;

import com.gordillo.adrian.model.entity.Pedidos;

import java.util.List;

public interface PedidosService {

    void save(Pedidos pedido) throws Exception;

    int lastId();

    int getByCod(String codPedido);

    Pedidos getById(int id);

    List<Pedidos> getByUsuario(int id);
}
