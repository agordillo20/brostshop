package com.gordillo.adrian.model.repository;

import com.gordillo.adrian.model.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductosRepository extends CrudRepository<Producto, Integer> {

    @Query("select a from Producto a where a.distribuidor.id=?1")
    List<Producto> findByDistribuidor(Integer id);

    @Query("select a.codProducto from Producto a where a.codProducto like :CodProducto% order by a.codProducto")
    List<Producto> findByCodProducto(String CodProducto);

    @Query("select a from Producto a left join fetch a.distribuidor where a.codProducto like :CodProducto%  order by a.codProducto")
    List<Producto> findArticuloByFiltroCodArticulo(String CodProducto);

    @Query("select a from Producto a where a.marca.razonSocial=?1")
    List<Producto> findProductosByMarca(String razonSocial);

    @Query("select distinct a.procesador from Producto a")
    List<String> findProcesadores();

    @Query("select distinct a.memoriaRam from Producto a")
    List<String> findRam();

    @Query("select distinct a.almacenamientoInterno from Producto a")
    List<String> findMemoria();

    @Query("select distinct a.sistemaOperativo from Producto a")
    List<String> findSO();

    //@Query()
    //List<Producto> findAllByAll();
}
