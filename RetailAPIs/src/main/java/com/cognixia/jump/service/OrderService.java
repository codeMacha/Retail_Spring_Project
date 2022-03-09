package com.cognixia.jump.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Order;
import com.cognixia.jump.repository.OrderRepository;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.util.JwtUtil;

@Service
public class OrderService {

	@Autowired
	OrderRepository order_repo;

	@Autowired
	UserRepository user_repo;

	@Autowired
	JwtUtil jwtUtil;

	public List<Order> getOrders() {
		return order_repo.findAll();
	}

	public Order createOrder(Order order, HttpServletRequest request) {

		// this is the header that contains our jwt info from the request
		final String authorizationHeader = request.getHeader("Authorization");

		String jwt = null;
		String username = null;

		// if header was null, no jwt was sent
		// then if there was a jwt, it would be formatted like "Bearer <token>"
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

			// token is there, grab the actual token string, remove the "Bearer " part of
			// the string
			jwt = authorizationHeader.substring(7);

			// grab the user associated with this token
			// if token is not valid, will return a null
			username = jwtUtil.extractUsername(jwt);
		}

		order.setId(null);

		order.setUser(user_repo.findByUsername(username).get());

		return order_repo.save(order);

	}

}
