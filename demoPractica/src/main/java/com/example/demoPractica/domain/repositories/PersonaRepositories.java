package com.example.demoPractica.domain.repositories;

import com.example.demoPractica.domain.model.PersonaModel;

import java.util.List;

public interface PersonaRepositories {
    PersonaModel save(PersonaModel persona);
    List<PersonaModel> getAll();
    PersonaModel findById(Long id);
    PersonaModel update(Long id, PersonaModel persona);
    boolean delete(Long id);
}
