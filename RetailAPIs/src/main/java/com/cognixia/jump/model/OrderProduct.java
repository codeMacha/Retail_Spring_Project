package com.cognixia.jump.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderProduct {
	
	
	@Id
	private Integer id;
	
	@ManyToOne
	@JoinColumn( name = "order_id", referencedColumnName= "id")
	private Order order;
	
	@ManyToOne
	@JoinColumn( name = "product_id", referencedColumnName= "id")
	private Product product;
	
	private int quantity;
	
	
	public OrderProduct() {
		
	}

	public OrderProduct(Integer id, Order order, Product product, int quantity) {
		super();
		this.id = id;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, order, product, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderProduct other = (OrderProduct) obj;
		return Objects.equals(id, other.id) && Objects.equals(order, other.order)
				&& Objects.equals(product, other.product) && quantity == other.quantity;
	}
	
	
	
	
}
