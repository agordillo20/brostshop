package com.gordillo.adrian.service.impl;

import com.gordillo.adrian.model.entity.Pedidos;
import com.gordillo.adrian.model.repository.PedidosRepository;
import com.gordillo.adrian.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidosServiceImpl implements PedidosService {
    @Autowired
    private PedidosRepository pedidosRepository;

    @Override
    public void save(Pedidos pedido) throws Exception {
        pedidosRepository.save(pedido);
    }
}
