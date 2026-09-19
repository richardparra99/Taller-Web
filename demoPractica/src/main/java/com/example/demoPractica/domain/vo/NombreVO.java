package com.example.demoPractica.domain.vo;

import lombok.Getter;

@Getter
public class NombreVO {
    private String valor;
    private int limiteMax = 50;
    private int limiteMin = 2;

    public NombreVO(String valor) {
        if (valor.length() < limiteMin) {
            throw new RuntimeException("El nombre debe tener al menos " + limiteMin + " caracteres");
        }
        if (valor.length() > limiteMax) {
            throw new RuntimeException("El nombre no puede tener más de " + limiteMax + " caracteres");
        }
        this.valor = valor;
    }
}
