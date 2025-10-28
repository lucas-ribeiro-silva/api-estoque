package com.lucasribeiro.apiestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasribeiro.apiestoque.model.Product;

@Repository // Anotação que indica ao Spring que esta é uma interface de repositório
public interface ProductRepository extends JpaRepository<Product, Long> {

    // JpaRepository<Product, Long>
    // 1º Parâmetro: 'Product' -> A entidade que este repositório gerencia.
    // 2º Parâmetro: 'Long' -> O tipo da Chave Primária (ID) da entidade Product.

    // Com apenas esta linha, nós já temos MÉTODOS PRONTOS como:
    // - save(Product product) -> Salva ou atualiza um produto
    // - findById(Long id) -> Busca um produto pelo ID
    // - findAll() -> Lista todos os produtos
    // - deleteById(Long id) -> Deleta um produto
}