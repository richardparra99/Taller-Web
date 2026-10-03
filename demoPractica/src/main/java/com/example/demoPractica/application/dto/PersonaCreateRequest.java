package com.example.demoPractica.application.dto;

public record PersonaCreateRequest(
        String nombre,
        String email,
        String password,
        String celular
) {
}
