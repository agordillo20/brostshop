package com.gordillo.adrian.model.repository;

import com.gordillo.adrian.model.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuariosRepository extends CrudRepository<Usuario, Integer> {
    List<Usuario> findByNifIgnoreCase(String nif);

    @Query("select u from Usuario u where u.nif=?1")
    Usuario findByUsername(String username);
}
