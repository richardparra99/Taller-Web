package com.example.demoPractica.application.mapper;

import com.example.demoPractica.application.dto.PersonaDto;
import com.example.demoPractica.domain.model.PersonaModel;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapperDto implements PersonaMapper<PersonaDto>{
    @Override
    public PersonaModel toDomain(PersonaDto external) {
        return null;
    }

    @Override
    public PersonaDto toExternal(PersonaModel model) {
        return new PersonaDto(model.getId(), model.getNombre(), model.getEmail(), model.getCelular());
    }
}
