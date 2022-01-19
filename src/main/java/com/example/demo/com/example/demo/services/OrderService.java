package com.example.demo.com.example.demo.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.com.example.demo.dto.OrderDTO;
import com.example.demo.com.example.demo.dto.ProductDTO;
import com.example.demo.com.example.demo.enties.Order;
import com.example.demo.com.example.demo.enties.OrderStatus;
import com.example.demo.com.example.demo.enties.Product;
import com.example.demo.com.example.demo.repositories.OrderRepository;
import com.example.demo.com.example.demo.repositories.ProductRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repository;

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}

	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLatitude(), Instant.now(),
				OrderStatus.PENDING);

		for (ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}

		order = repository.save(order);

		return new OrderDTO(order);
	}

	@Transactional
	public OrderDTO setDeliverd(Long id) {
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = repository.save(order);

		return new OrderDTO(order);
	}

}
