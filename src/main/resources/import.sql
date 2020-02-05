insert into users (nombre, apellido1,nif, fecha_nacimiento,username, password, activo) values ('Administrador', 'Administrador1','75875859g',now(), 'admin','$2a$10$D8eheja.tpn/JXjCbyWj6eKcJ0L513yEMYs1ByAoQPM.oKg3khEz.', true);
insert into roles (id_usuario, rol) values('1','ROLE_ADMIN');
insert into roles (id_usuario, rol) values('1','ROLE_USER');
insert into distribuidor(activo, antiguedad, apellidos, localidad, nombre) value (1,5,'lopez perez','estepona','pablo');