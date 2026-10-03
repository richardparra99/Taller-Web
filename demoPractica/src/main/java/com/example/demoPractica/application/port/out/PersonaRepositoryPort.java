package com.example.demoPractica.application.port.out;

import com.example.demoPractica.domain.model.PersonaModel;

import java.util.List;

public interface PersonaRepositoryPort {
    public PersonaModel getById(int id);
    public List<PersonaModel> getAll();
    public PersonaModel create(PersonaModel persona);
}
