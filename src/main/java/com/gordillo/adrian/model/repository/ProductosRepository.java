package com.gordillo.adrian.model.repository;

import com.gordillo.adrian.model.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductosRepository extends CrudRepository<Producto, Integer> {

    @Query("select a from Producto a where a.distribuidor.id=?1")
    List<Producto> findByDistribuidor(Integer id);

    @Query("select a from Producto a where a.codProducto =?1")
    Producto findByCodProducto(String CodProducto);

    @Query("select a from Producto a left join fetch a.distribuidor where a.codProducto like :CodProducto%  order by a.codProducto")
    List<Producto> findArticuloByFiltroCodArticulo(String CodProducto);

    @Query("select distinct a.procesador from Producto a")
    List<String> findProcesadores();

    @Query("select distinct a.memoria_ram from Producto a")
    List<String> findRam();

    @Query("select a from Producto a where a.memoria_ram=?1")
    List<Producto> filtroRam(int ram);

    @Query("select a from Producto a where a.sistemaOperativo=?1")
    List<Producto> filtroSo(String so);

    @Query("select a from Producto a where a.almacenamientoInterno=?1")
    List<Producto> filtroAli(int ali);

    @Query("select a from Producto a where a.procesador=?1")
    List<Producto> filtroProcesador(String procesador);

    @Query("select distinct a.almacenamientoInterno from Producto a")
    List<String> findMemoria();

    @Query("select distinct a.sistemaOperativo from Producto a")
    List<String> findSO();

    @Query("SELECT a.id from Producto a where a.id=(SELECT max(id) from Producto)")
    int lastId();

    @Query("SELECT a from Producto a where a.valoracion>=4")
    List<Producto> best();

    Producto findById(int id);

}
