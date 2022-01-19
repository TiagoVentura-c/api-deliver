package com.example.demo.com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.com.example.demo.dto.OrderDTO;
import com.example.demo.com.example.demo.dto.ProductDTO;
import com.example.demo.com.example.demo.enties.Order;
import com.example.demo.com.example.demo.enties.Product;
import com.example.demo.com.example.demo.repositories.OrderRepository;
import com.example.demo.com.example.demo.repositories.ProductRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
}
 