package com.gordillo.adrian.service.impl;

import com.gordillo.adrian.model.entity.Distribuidor;
import com.gordillo.adrian.model.repository.DistribuidoresRepository;
import com.gordillo.adrian.service.DistribuidoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DistribuidoresServiceImpl implements DistribuidoresService {

    @Autowired
    private DistribuidoresRepository distribuidoresRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Distribuidor> findAll() {
        return (List<Distribuidor>) distribuidoresRepository.findAll();
    }


    @Override
    @Transactional(readOnly = false)
    public void save(Distribuidor distribuidor) {
        distribuidoresRepository.save(distribuidor);
    }

    @Override
    @Transactional(readOnly = true)
    public Distribuidor findOne(Integer id) {
        return distribuidoresRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        distribuidoresRepository.deleteById(id);
    }

    @Override
    public List<Distribuidor> findByLocalidad(String localidad) {
        return distribuidoresRepository.findByLocalidad(localidad);
    }
}
