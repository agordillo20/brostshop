package com.gordillo.adrian.service.impl;

import com.gordillo.adrian.model.entity.Pedidos;
import com.gordillo.adrian.model.repository.PedidosRepository;
import com.gordillo.adrian.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidosServiceImpl implements PedidosService {
    @Autowired
    private PedidosRepository pedidosRepository;

    @Override
    public void save(Pedidos pedido) throws Exception {
        pedidosRepository.save(pedido);
    }

    @Override
    public int lastId() {
        return pedidosRepository.lastId();
    }

    @Override
    public int getByCod(String codPedido) {
        return pedidosRepository.getByCod(codPedido);
    }

    @Override
    public Pedidos getById(int id) {
        return pedidosRepository.getById(id);
    }

    @Override
    public List<Pedidos> getByUsuario(int id) {
        return pedidosRepository.getByUsuario(id);
    }
}
