package com.lucasribeiro.apiestoque.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional; // MUITO IMPORTANTE

import com.lucasribeiro.apiestoque.dto.OrderRequestDTO;
import com.lucasribeiro.apiestoque.dto.OrderItemRequestDTO;
import com.lucasribeiro.apiestoque.exception.InsufficientStockException;
import com.lucasribeiro.apiestoque.exception.ResourceNotFoundException;
import com.lucasribeiro.apiestoque.model.*; // Importa todos os models
import com.lucasribeiro.apiestoque.repository.CustomerRepository;
import com.lucasribeiro.apiestoque.repository.OrderRepository;
import com.lucasribeiro.apiestoque.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    // Precisamos de todos os 3 repositórios aqui
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    // --- O MÉTODO MAIS IMPORTANTE ---

    // @Transactional garante que esta operação inteira é "atômica".
    // Se um erro (ex: InsufficientStock) acontecer no meio,
    // o banco de dados "desfaz" (rollback) todas as alterações.
    @Transactional
    public Order createOrder(OrderRequestDTO orderRequest) {

        // 1. Encontrar o Cliente
        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + orderRequest.getCustomerId()));

        // 2. Criar o objeto Order (Pedido)
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING); // Status inicial

        List<OrderItem> orderItems = new ArrayList<>();

        // 3. Loop para processar os itens, verificar estoque e dar baixa
        for (OrderItemRequestDTO itemDTO : orderRequest.getItems()) {

            // 3a. Encontrar o Produto
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + itemDTO.getProductId()));

            // 3b. Verificar o Estoque
            if (product.getStockQuantity() < itemDTO.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
            }

            // 3c. Dar baixa no Estoque
            int newStock = product.getStockQuantity() - itemDTO.getQuantity();
            product.setStockQuantity(newStock);
            // (Não precisamos do save() aqui, o @Transactional cuida disso no final)
            // productRepository.save(product); // <-- Não é necessário agora

            // 3d. Criar o OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setOrder(order); // Linka o item ao pedido principal

            orderItems.add(orderItem);
        }

        // 4. Salvar tudo
        order.setItems(orderItems); // Adiciona a lista de itens ao pedido
        return orderRepository.save(order); // Salva o Pedido (e os Itens, por causa do CascadeType.ALL)
    }

    // --- Outros métodos ---

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }
}