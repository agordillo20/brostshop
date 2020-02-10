package com.gordillo.adrian.service.impl;

import com.gordillo.adrian.model.entity.Marca;
import com.gordillo.adrian.model.entity.Producto;
import com.gordillo.adrian.model.repository.MarcasRepository;
import com.gordillo.adrian.service.MarcasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MarcasServiceImpl implements MarcasService {

    @Autowired
    private MarcasRepository marcasRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Marca> findAll() {
        return (List<Marca>) marcasRepository.findAll();
    }


    @Override
    @Transactional(readOnly = false)
    public void save(Marca marca) {
        marcasRepository.save(marca);
    }

    @Override
    @Transactional(readOnly = true)
    public Marca findOne(Integer id) {
        return marcasRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        marcasRepository.deleteById(id);
    }

    @Override
    public List<Producto> findAllByRazonSocial(String razonSocial) {
        return marcasRepository.findAllByRazonSocial(razonSocial);
    }
}
