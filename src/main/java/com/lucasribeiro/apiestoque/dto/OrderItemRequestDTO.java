package com.lucasribeiro.apiestoque.dto;

import lombok.Data;

@Data // Anotação do Lombok que já inclui @Getter, @Setter, @ToString, etc.
public class OrderItemRequestDTO {
    private Long productId;
    private Integer quantity;
}