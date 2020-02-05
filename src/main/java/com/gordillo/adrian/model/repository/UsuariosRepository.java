package com.gordillo.adrian.model.repository;

import com.gordillo.adrian.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuariosRepository extends CrudRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
}
