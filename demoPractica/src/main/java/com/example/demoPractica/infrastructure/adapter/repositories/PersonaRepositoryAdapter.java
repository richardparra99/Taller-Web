package com.example.demoPractica.infrastructure.adapter.repositories;

import com.example.demoPractica.application.mapper.PersonaMapper;
import com.example.demoPractica.application.port.out.PersonaRepositoryPort;
import com.example.demoPractica.domain.model.PersonaModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonaRepositoryAdapter implements PersonaRepositoryPort {
    private final PersonaRepositoryJpa personaRepositoryJpa;
    private final PersonaMapper<PersonaEntity> mapperEntity;

    public PersonaRepositoryAdapter(PersonaRepositoryJpa personaRepositoryJpa, PersonaMapper<PersonaEntity> mapperEntity) {
        this.personaRepositoryJpa = personaRepositoryJpa;
        this.mapperEntity = mapperEntity;
    }


    @Override
    public PersonaModel getById(int id) {
        return personaRepositoryJpa.findById(id)
                .map(mapperEntity::toDomain)
                .orElseThrow(() -> new NoSuchElementException(
                        "No existe una persona con id: " + id
                ));
    }

    @Override
    public List<PersonaModel> getAll() {
        return personaRepositoryJpa.findAll().stream().map(mapperEntity::toDomain).toList();
    }

    @Override
    public PersonaModel create(PersonaModel persona) {
        PersonaEntity entity = mapperEntity.toExternal(persona);
        PersonaEntity savedEntity = personaRepositoryJpa.save(entity);
        return mapperEntity.toDomain(savedEntity);
    }
}
