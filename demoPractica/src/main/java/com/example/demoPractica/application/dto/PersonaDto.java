package com.example.demoPractica.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PersonaDto {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
}
