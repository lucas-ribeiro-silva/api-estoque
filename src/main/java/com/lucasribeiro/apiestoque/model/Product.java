package com.lucasribeiro.apiestoque.model;

import java.math.BigDecimal; // Importe para valores monetários

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Diz ao JPA que esta classe é uma tabela
@Table(name = "products") // Nome da tabela no banco
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id // Chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento
    private Long id;

    @Column(nullable = false) // Coluna não pode ser nula
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price; // Use BigDecimal para dinheiro

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;
}