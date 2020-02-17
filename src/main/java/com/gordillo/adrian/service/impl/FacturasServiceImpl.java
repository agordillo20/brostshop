package com.gordillo.adrian.service.impl;

import com.gordillo.adrian.model.entity.Factura;
import com.gordillo.adrian.model.repository.FacturasRepository;
import com.gordillo.adrian.service.FacturasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturasServiceImpl implements FacturasService {
    @Autowired
    private FacturasRepository facturasRepository;


    @Override
    public void save(Factura factura) throws Exception {
        facturasRepository.save(factura);
    }

    @Override
    public List<Factura> getByIdPedido(int id) {
        return facturasRepository.getByIdPedido(id);
    }
}
