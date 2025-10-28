package com.lucasribeiro.apiestoque.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.lucasribeiro.apiestoque.model.Customer;
import com.lucasribeiro.apiestoque.repository.CustomerRepository;
import com.lucasribeiro.apiestoque.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // 1. Método para CRIAR um cliente
    public Customer createCustomer(Customer customer) {
        // No futuro, poderíamos adicionar uma lógica
        // para verificar se o email já existe.
        return customerRepository.save(customer);
    }

    // 2. Método para BUSCAR TODOS os clientes
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // 3. Método para BUSCAR UM cliente por ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    // 4. Método para ATUALIZAR um cliente
    public Customer updateCustomer(Long id, Customer customerDetails) {
        // Primeiro, verifica se o cliente existe
        Customer existingCustomer = getCustomerById(id);

        // Atualiza os campos
        existingCustomer.setName(customerDetails.getName());
        existingCustomer.setEmail(customerDetails.getEmail());

        // Salva o cliente atualizado
        return customerRepository.save(existingCustomer);
    }

    // 5. Método para DELETAR um cliente
    public void deleteCustomer(Long id) {
        // Primeiro, verifica se o cliente existe
        Customer existingCustomer = getCustomerById(id);

        // (Lógica futura: não permitir deletar se tiver pedidos associados)
        customerRepository.delete(existingCustomer);
    }
}