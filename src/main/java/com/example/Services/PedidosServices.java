package com.example.Services;

import java.util.List;

import com.example.Model.Pedidos;
import com.example.Model.PedidosDetalle;

public interface PedidosServices {
	public List<Pedidos> listAllPedidos();
	public Pedidos getPedido(long id);
	
	public Pedidos createPedido(Pedidos pedido);
	public Pedidos updatePedido(Pedidos pedido);
	public Pedidos deletePedido(Long id);
	public List<Pedidos> findByPedidosDetalle(PedidosDetalle pedidosDet);
	public Pedidos updateStock(Long id,Double Quantity);
}
