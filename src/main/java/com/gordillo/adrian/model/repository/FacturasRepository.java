package com.gordillo.adrian.model.repository;

import com.gordillo.adrian.model.entity.Factura;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FacturasRepository extends CrudRepository<Factura, Integer> {
    List<Factura> getByIdPedido(int id);
}
