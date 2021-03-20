package com.example.Model;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.*;
import javax.validation.constraints.*;



@Entity
@Table(name = "PEDIDOS_W")
public class Pedidos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull(message = "La categoria no puede ser vacia")
	private Double total;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_sale;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = PedidosDetalle.class)
	@JoinColumn(name = "ID_PEDIDO")
	private Set<PedidosDetalle> pedidosDetalle = new TreeSet<>();

	public Pedidos() {
		super();
	}

	public Pedidos(long id, Double total, Date date_sale, Set<PedidosDetalle> coachGroups) {
		super();
		this.id = id;
		this.total = total;
		this.date_sale = date_sale;
		this.pedidosDetalle = coachGroups;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getDate_sale() {
		return date_sale;
	}

	public void setDate_sale(Date date_sale) {
		this.date_sale = date_sale;
	}

	public Set<PedidosDetalle> getPedidosDetalle() {
		return pedidosDetalle;
	}

	public void setPedidosDetalle(Set<PedidosDetalle> pedidosDetalle) {
		this.pedidosDetalle = pedidosDetalle;
	}

	@Override
	public String toString() {
		return "Pedidos [id=" + id + ", total=" + total + ", date_sale=" + date_sale + ", pedidosDetalle="
				+ pedidosDetalle + "]";
	}

}
