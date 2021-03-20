package com.example.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.Greeting;
import com.example.Model.Pedidos;
import com.example.Model.PedidosDetalle;
import com.example.Services.PedidosServices;

@RestController
@RequestMapping(value = "/Pedidos")
public class ApiController {

	@Autowired
	PedidosServices pedidosServices;

	private static AtomicLong counter = new AtomicLong();
	private static final String template = "Hello %s";

	@GetMapping(value = "saludos")
	public Greeting Saludar(@RequestParam(value = "name", defaultValue = "World") String nombre) {
		return new Greeting(counter.incrementAndGet(), String.format(template, nombre));
	}

	@GetMapping(value = "listPedidos")
	public ResponseEntity<List<Pedidos>> listProduct(@RequestParam(name = "idPedidoDetalle",required = false) Long idPedidoDet) {
		List<Pedidos> pedidos = new ArrayList<>();
		if (idPedidoDet == null) {
			pedidos = pedidosServices.listAllPedidos();
			
			if (pedidos.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		} else {
			pedidos = pedidosServices.findByPedidosDetalle(new PedidosDetalle(idPedidoDet, 0, "ABC", 0, 0));
		}

		if (pedidos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(pedidos);
	}
	
	@PostMapping
	public ResponseEntity<Pedidos> createPedido(@RequestBody Pedidos pedido) {
			Pedidos pedidoCreate = pedidosServices.createPedido(pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCreate);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Pedidos>updatePedido(@PathVariable("id") long id,@RequestBody Pedidos pedido){
		pedido.setId(id);
		Pedidos pedidoDB = pedidosServices.updatePedido(pedido);
		
		if(pedidoDB==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pedidoDB);
	}
	
	@DeleteMapping
	public ResponseEntity<Pedidos>deletePedido(@PathVariable("id") long id){
		Pedidos pedidoDB = pedidosServices.deletePedido(id);
		return ResponseEntity.ok(pedidoDB);
	}
	

}
