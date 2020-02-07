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
    public List<String> findProcesadores() {
        return productoRepository.findProcesadores();
    }

    @Override
    public List<String> findRam() {
        return null;
    }

    @Override
    public List<String> findMemoria() {
        return null;
    }

    @Override
    public List<String> findSO() {
        return null;
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
    public List<Producto> findProductoByCodArticulo(String codProducto) {
        return productoRepository.findArticuloByFiltroCodArticulo(codProducto);
    }

    @Override
    public List<Producto> findProductosByMarca(Integer id) {
        return productoRepository.findProductosByMarca(id);
    }
}
