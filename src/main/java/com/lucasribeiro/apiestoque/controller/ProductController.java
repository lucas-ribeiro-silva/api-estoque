package com.lucasribeiro.apiestoque.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.lucasribeiro.apiestoque.model.Product;
import com.lucasribeiro.apiestoque.service.ProductService;

import java.util.List;

@RestController // Define que esta classe é um Controller REST
@RequestMapping("/api/products") // Prefixo da URL para todos os métodos nesta classe
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Endpoint para CRIAR um Produto
    // POST /api/products
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.createProduct(product);
        // Retorna 201 CREATED com o produto criado no corpo
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    // Endpoint para LISTAR TODOS os Produtos
    // GET /api/products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Endpoint para BUSCAR UM Produto por ID
    // GET /api/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // Endpoint para ATUALIZAR um Produto
    // PUT /api/products/1
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // Endpoint para DELETAR um Produto
    // DELETE /api/products/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        // Retorna 204 NO CONTENT (sem corpo)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}