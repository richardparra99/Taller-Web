package com.example.demoPractica.domain.model;

import com.example.demoPractica.domain.vo.NombreVO;
import lombok.Getter;

@Getter
public class PersonaModel {
    private Long id;
    private NombreVO nombre;
    private String celular;

    public PersonaModel(Long id, String nombre, String celular) {
        this.id = id;
        this.nombre = new NombreVO(nombre);
        this.celular = celular;
    }

}
