package com.lucasribeiro.apiestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasribeiro.apiestoque.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Agora temos:
    // - save(Customer customer)
    // - findById(Long id)
    // ...e todos os outros métodos CRUD para Clientes.
}