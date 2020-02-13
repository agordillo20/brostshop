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
        return productoRepository.findRam();
    }

    @Override
    public List<String> findMemoria() {
        return productoRepository.findMemoria();
    }

    @Override
    public List<String> findSO() {
        return productoRepository.findSO();
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
    public Producto findByCodProducto(String codPro) {
        return productoRepository.findByCodProducto(codPro);
    }

    @Override
    public List<Producto> findProductoByCodArticulo(String codProducto) {
        return productoRepository.findArticuloByFiltroCodArticulo(codProducto);
    }

    @Override
    public List<Producto> filtroRam(int ram) {
        return productoRepository.filtroRam(ram);
    }

    @Override
    public List<Producto> filtroSo(String so) {
        return productoRepository.filtroSo(so);
    }

    @Override
    public List<Producto> filtroAli(int ali) {
        return productoRepository.filtroAli(ali);
    }

    @Override
    public List<Producto> filtroProcesador(String procesador) {
        return productoRepository.filtroProcesador(procesador);
    }

    @Override
    public int lastId() {
        return productoRepository.lastId();
    }

    @Override
    public List<Producto> best() {
        return productoRepository.best();
    }

    @Override
    public Producto findById(int id) {
        return productoRepository.findById(id);
    }
}
