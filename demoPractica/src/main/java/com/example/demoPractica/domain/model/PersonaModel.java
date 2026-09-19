package com.example.demoPractica.domain.model;

import com.example.demoPractica.domain.vo.NombreVO;
import lombok.Getter;

@Getter
public class PersonaModel {
    private NombreVO nombre;
    private String celular;

    public PersonaModel(String nombre, String celular) {
        this.nombre = new NombreVO(nombre);
        this.celular = celular;
    }

}
