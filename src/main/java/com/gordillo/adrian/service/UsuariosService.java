package com.gordillo.adrian.service;


import com.gordillo.adrian.model.entity.Usuario;

import java.util.List;

public interface UsuariosService {

    public List<Usuario> findAll();

    public void save(Usuario usuario) throws Exception;

    public Usuario findOne(Integer id);

    public void delete(Integer id);

    public Usuario findByUsername(String username);


}
