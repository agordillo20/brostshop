package com.gordillo.adrian.service;

import com.gordillo.adrian.model.entity.Factura;

import java.util.List;

public interface FacturasService {

    void save(Factura factura) throws Exception;

    List<Factura> getByIdPedido(int id);
}
