package com.gordillo.adrian.service.impl;


import com.gordillo.adrian.model.entity.Rol;
import com.gordillo.adrian.model.entity.Usuario;
import com.gordillo.adrian.model.repository.UsuariosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuariosRepository usuariosRepository;

    private Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuariosRepository.findByUsername(username);
        logger.info(usuario.toString());
        if (usuario == null) {
            logger.error("Error en el login: no existe el usuario '" + username + "'");
            throw new UsernameNotFoundException("Username '" + username + "' no encontrado");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Rol rol : usuario.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(rol.getRol()));
        }

        if (authorities.isEmpty()) {
            logger.error("Error en el login: el usuario '" + username + "' no tiene permisos asignados");
            throw new UsernameNotFoundException("Error en el login: el usuario '" + username + "' no tiene permisos asignados");
        }
        //usuario.isActivo() Es importante que el campo activo este a true
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isActivo(), true, true, true, authorities);
    }
}
