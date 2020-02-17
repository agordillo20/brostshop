package com.gordillo.adrian.model.repository;

import com.gordillo.adrian.model.entity.Pedidos;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PedidosRepository extends CrudRepository<Pedidos, Integer> {
    @Modifying
    @Query(value = "insert into pedidos_productos(pedidos_id,productos_id) values(?1,?2)", nativeQuery = true)
    void insertPedido(int idPedido, int idProducto);

    @Query("SELECT a.id from Pedidos a where a.id=(SELECT max(id) from Pedidos)")
    int lastId();

    @Query("select a.id from Pedidos a where a.codigoPedido=?1")
    int getByCod(String codPedido);

    Pedidos getById(int id);

    @Query("SELECT a from Pedidos a where a.usuario.id=?1")
    List<Pedidos> getByUsuario(int id);
}
