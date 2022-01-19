package com.example.demo.com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.com.example.demo.enties.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
