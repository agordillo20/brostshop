package com.gordillo.adrian.service.impl;

import com.gordillo.adrian.model.entity.Producto;
import com.gordillo.adrian.model.repository.ProductosRepository;
import com.gordillo.adrian.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductosServiceImpl implements ProductosService {

    @Autowired
    private ProductosRepository productoRepository;

    @Override
    @Transactional
    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Producto producto) throws Exception {
        productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Producto findOne(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> findByCodProducto(String codPro) {
        return productoRepository.findByCodProducto(codPro);
    }

    @Override
    public List<Producto> findByDescripcion(String Descripcion) {
        return productoRepository.findByDescripcion(Descripcion);
    }

    @Override
    public List<Producto> findProductoByCodArticuloByDescripcion(String codProducto, String descripcion) {
        return productoRepository.findArticuloByFiltroCodArticuloByFiltroDescripcion(codProducto, descripcion);
    }
}
