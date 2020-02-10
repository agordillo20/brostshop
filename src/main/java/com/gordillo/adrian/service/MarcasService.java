package com.gordillo.adrian.service;

import com.gordillo.adrian.model.entity.Marca;
import com.gordillo.adrian.model.entity.Producto;

import java.util.List;

public interface MarcasService {
    public List<Marca> findAll();

    public void save(Marca marca) throws Exception;

    public Marca findOne(Integer id);

    public void delete(Integer id);

    public List<Producto> findAllByRazonSocial(String razonSocial);
}
