package com.gordillo.adrian.service;

import com.gordillo.adrian.model.entity.Marca;

import java.util.List;

public interface MarcasService {
    public List<Marca> findAll();

    public void save(Marca marca) throws Exception;

    public Marca findOne(Integer id);

    public void delete(Integer id);
}
