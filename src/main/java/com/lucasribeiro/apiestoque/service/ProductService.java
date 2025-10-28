package com.lucasribeiro.apiestoque.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.lucasribeiro.apiestoque.model.Product;
import com.lucasribeiro.apiestoque.repository.ProductRepository;
import com.lucasribeiro.apiestoque.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service // Anotação que define a classe como um "Serviço" (componente de lógica)
public class ProductService {

    // 1. Injeção de Dependência
    // Estamos pedindo ao Spring para "injetar" (nos dar) uma instância
    // do ProductRepository que ele criou na Fase 3.
    private final ProductRepository productRepository;

    @Autowired // Injeção de dependência via construtor (melhor prática)
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 2. Método para CRIAR um produto
    public Product createProduct(Product product) {
        // Por enquanto, nossa regra de negócio é simples: apenas salvar.
        return productRepository.save(product);
    }

    // 3. Método para BUSCAR TODOS os produtos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 4. Método para BUSCAR UM produto por ID
    public Product getProductById(Long id) {
        // Aqui usamos nossa exceção!
        // O .orElseThrow() executa a exceção se o Optional estiver vazio.
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    // 5. Método para ATUALIZAR um produto
    public Product updateProduct(Long id, Product productDetails) {
        // Primeiro, verifica se o produto existe
        Product existingProduct = getProductById(id); // Reutiliza nosso método

        // Atualiza os campos
        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setStockQuantity(productDetails.getStockQuantity());

        // Salva o produto atualizado
        return productRepository.save(existingProduct);
    }

    // 6. Método para DELETAR um produto
    public void deleteProduct(Long id) {
        // Primeiro, verifica se o produto existe
        Product existingProduct = getProductById(id);

        // Se existir, deleta
        productRepository.delete(existingProduct);
    }
}