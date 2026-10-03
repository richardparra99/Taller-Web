package com.example.demoPractica.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaModel {
    private int id;
    private String nombre;
    private String email;
    private String password;
    private String celular;

}
