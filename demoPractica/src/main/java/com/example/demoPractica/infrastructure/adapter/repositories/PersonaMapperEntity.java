package com.example.demoPractica.infrastructure.adapter.repositories;

import com.example.demoPractica.application.mapper.PersonaMapper;
import com.example.demoPractica.domain.model.PersonaModel;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapperEntity implements PersonaMapper<PersonaEntity> {
    @Override
    public PersonaModel toDomain(PersonaEntity external) {
        return new PersonaModel(
                external.getId(),
                external.getNombre(),
                external.getEmail(),
                external.getPassword(),
                external.getCelular()
        );
    }

    @Override
    public PersonaEntity toExternal(PersonaModel model) {
        return new PersonaEntity(
                model.getId(),
                model.getNombre(),
                model.getEmail(),
                model.getPassword(),
                model.getCelular()
        );
    }
}
