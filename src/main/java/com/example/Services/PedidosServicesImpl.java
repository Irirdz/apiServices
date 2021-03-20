package com.example.Services;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Model.Pedidos;
import com.example.Model.PedidosDetalle;
import com.example.Repository.PedidosRepository;

@Service
public class PedidosServicesImpl implements PedidosServices {

	@Autowired
	PedidosRepository pedidosRepository;

	@Override
	public List<Pedidos> listAllPedidos() {
		return pedidosRepository.findAll();
	}

	@Override
	public Pedidos getPedido(long id) {
		return pedidosRepository.findById(id).orElse(null);
	}

	@Override
	public Pedidos createPedido(Pedidos pedido) {
		return pedidosRepository.save(pedido);
	}

	@Override
	public Pedidos updatePedido(Pedidos pedido) {
		Pedidos getPedido = getPedido(pedido.getId());
		if (getPedido == null) {
			return null;
		}
		getPedido.setDate_sale(pedido.getDate_sale());
		getPedido.setTotal(pedido.getTotal());
		Set<PedidosDetalle> listPedDet = new TreeSet<PedidosDetalle>();
		for (PedidosDetalle pedDet : pedido.getPedidosDetalle()) {

			for (PedidosDetalle getPedDet : getPedido.getPedidosDetalle()) {

				PedidosDetalle pedidos = new PedidosDetalle();

				pedidos = getPedDet;
				pedidos.setPrice(pedDet.getPrice());
				pedidos.setSku(pedDet.getSku());
				pedidos.setAmount(pedDet.getAmount());

				listPedDet.add(pedidos);
			}
			
		}
		getPedido.setPedidosDetalle(listPedDet);
		

		return pedidosRepository.save(getPedido);
	}

	@Override
	public Pedidos deletePedido(Long id) {
		Pedidos getPedido = getPedido(id);
		if (getPedido == null) {
			return null;
		}
		pedidosRepository.delete(getPedido);
		return getPedido;
	}

	@Override
	public List<Pedidos> findByPedidosDetalle(PedidosDetalle pedidosDet) {
		return pedidosRepository.findByPedidosDetalle(pedidosDet);
	}

	@Override
	public Pedidos updateStock(Long id, Double Quantity) {
		Pedidos getPedidos = getPedido(id);
		if (getPedidos == null) {
			return null;
		}
		getPedidos.setTotal(Quantity);

		return pedidosRepository.save(getPedidos);
	}

}
