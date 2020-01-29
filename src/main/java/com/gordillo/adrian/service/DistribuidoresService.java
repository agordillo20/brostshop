package com.gordillo.adrian.service;

import com.gordillo.adrian.model.entity.Distribuidor;

import java.util.List;

public interface DistribuidoresService {
    public List<Distribuidor> findAll();

    public void save(Distribuidor distribuidor) throws Exception;

    public Distribuidor findOne(Integer id);

    public void delete(Integer id);

    public List<Distribuidor> findByLocalidad(String localidad);
}
