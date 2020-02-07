package com.gordillo.adrian.service;


import com.gordillo.adrian.model.entity.Producto;

import java.util.List;

public interface ProductosService {

    List<Producto> findAll();

    List<String> findProcesadores();

    List<String> findRam();

    List<String> findMemoria();

    List<String> findSO();

    void save(Producto producto) throws Exception;

    Producto findOne(Integer id);

    void delete(Integer id);

    List<Producto> findByCodProducto(String codPro);

    List<Producto> findProductoByCodArticulo(String codProducto);

    List<Producto> findProductosByMarca(Integer id);


}
