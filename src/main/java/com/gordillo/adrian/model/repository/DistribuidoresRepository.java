package com.gordillo.adrian.model.repository;

import com.gordillo.adrian.model.entity.Distribuidor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DistribuidoresRepository extends CrudRepository<Distribuidor, Integer> {

    List<Distribuidor> findByLocalidad(String localidad);
}
