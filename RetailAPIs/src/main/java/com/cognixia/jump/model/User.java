package com.cognixia.jump.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class User {

	public static enum Roles {
		USER, ADMIN
	}
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (nullable = false)
	private String first_name;
	
	@Column (nullable = false)
	private String last_name;
	
	private String username;
	
	@Size(min=6)
	private String password;
	
	@Size(min=9)
	private char phone_number;
	
	@Enumerated(EnumType.STRING)
	private Roles role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> orders;
	
	public User() {
		
	}

	public User(Integer id, String first_name, String last_name, String username, String password, char phone_number,
			Roles role, List<Order> orders) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
		this.password = password;
		this.phone_number = phone_number;
		this.role = role;
		this.orders = orders;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public char getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(char phone_number) {
		this.phone_number = phone_number;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", username=" + username
				+ ", password=" + password + ", phone_number=" + phone_number + ", role=" + role + ", orders=" + orders
				+ "]";
	}
	
	
}
