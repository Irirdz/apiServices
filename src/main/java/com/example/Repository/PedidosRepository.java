package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.Pedidos;
import com.example.Model.PedidosDetalle;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos,Long> {
	
	public List<Pedidos> findByTotal(Long total);
	public List<Pedidos> findByPedidosDetalle(PedidosDetalle pedidosDet);
}
