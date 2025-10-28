package com.lucasribeiro.apiestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasribeiro.apiestoque.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Agora temos:
    // - save(Order order)
    // - findById(Long id)
    // ...e todos os outros métodos CRUD para Pedidos.
}