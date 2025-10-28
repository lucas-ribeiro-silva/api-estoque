package com.lucasribeiro.apiestoque.exception;

import java.time.LocalDateTime;

// Usamos @Data do Lombok para ter Getters, Setters, etc.
import lombok.Data;

@Data
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    // Construtor para facilitar
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}