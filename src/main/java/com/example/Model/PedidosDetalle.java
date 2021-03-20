package com.example.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDOS_DETALLE_W")
public class PedidosDetalle implements Comparable<PedidosDetalle>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long id_pedido;
	private String sku;
	private double amount;
	private double price;

	public PedidosDetalle() {
		super();
	}

	public PedidosDetalle(long id, long id_pedido, String sku, double amount, double price) {
		super();
		this.id = id;
		this.id_pedido = id_pedido;
		this.sku = sku;
		this.amount = amount;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(long id_pedido) {
		this.id_pedido = id_pedido;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int compareTo(PedidosDetalle o) {
		return (int) (getId() - o.getId());
	}
}
