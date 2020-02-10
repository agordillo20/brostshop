package com.gordillo.adrian.model.repository;

import com.gordillo.adrian.model.entity.Marca;
import com.gordillo.adrian.model.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarcasRepository extends CrudRepository<Marca, Integer> {
    @Query("select a.productos from Marca a where a.razonSocial=?1")
    List<Producto> findAllByRazonSocial(String razonSocial);
}
