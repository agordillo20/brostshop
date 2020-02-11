package com.gordillo.adrian.service.impl;

import com.gordillo.adrian.model.entity.Usuario;
import com.gordillo.adrian.model.repository.UsuariosRepository;
import com.gordillo.adrian.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuariosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Usuario usuario) throws Exception {
        usuariosRepository.save(usuario);

//		System.out.println(usuariosRepository.findByEmail("javier@iescastelar.com").toString());
//		System.out.println(usuariosRepository.findByNif("123").toString());
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findOne(Integer id) {
        return usuariosRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        usuariosRepository.deleteById(id);

    }

    @Override
    public Usuario findByNif(String nif) {
        return null;
    }
}
