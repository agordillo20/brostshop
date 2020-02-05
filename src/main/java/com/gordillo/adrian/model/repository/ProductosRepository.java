package com.gordillo.adrian.model.repository;

import com.gordillo.adrian.model.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductosRepository extends CrudRepository<Producto, Integer> {

    @Query("select a from Producto a where a.distribuidor.id=?1")
    List<Producto> findByDistribuidor(Integer id);

    @Query("select a from Producto a where a.descripcion like %:Descripcion%")
    List<Producto> findByDescripcion(String Descripcion);

    @Query("select a.codProducto from Producto a where a.codProducto like :CodProducto% order by a.codProducto")
    List<Producto> findByCodProducto(String CodProducto);

    @Query("select a from Producto a left join fetch a.distribuidor where a.codProducto like :CodProducto%  or a.descripcion like %:Descripcion% order by a.codProducto")
    List<Producto> findArticuloByFiltroCodArticuloByFiltroDescripcion(String CodProducto, String Descripcion);
}
