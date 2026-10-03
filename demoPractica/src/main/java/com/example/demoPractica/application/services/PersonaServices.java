package com.example.demoPractica.application.services;

import com.example.demoPractica.application.dto.PersonaCreateRequest;
import com.example.demoPractica.application.dto.PersonaDto;
import com.example.demoPractica.application.mapper.PersonaMapper;
import com.example.demoPractica.application.port.in.PersonaCreate;
import com.example.demoPractica.application.port.in.PersonaGetAll;
import com.example.demoPractica.application.port.in.PersonaGetById;
import com.example.demoPractica.application.port.out.PersonaRepositoryPort;
import com.example.demoPractica.domain.model.PersonaModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServices implements PersonaGetById, PersonaGetAll, PersonaCreate {
    private final PersonaRepositoryPort personaRepositoryPort;
    private final PersonaMapper<PersonaDto> personaMapper;

    public PersonaServices(PersonaRepositoryPort personaRepositoryPort, PersonaMapper<PersonaDto> personaMapper) {
        this.personaRepositoryPort = personaRepositoryPort;
        this.personaMapper = personaMapper;
    }


    @Override
    public List<PersonaDto> getAll() {
        return personaRepositoryPort.getAll().stream().map(model -> (PersonaDto) personaMapper.toExternal(model)).toList();
    }

    @Override
    public PersonaDto getById(int id) {
        return (PersonaDto) personaMapper.toExternal(personaRepositoryPort.getById(id));
    }

    @Override
    public PersonaDto create(PersonaCreateRequest personaCreate) {
        PersonaModel persona = new PersonaModel(
                0,
                personaCreate.nombre(),
                personaCreate.email(),
                personaCreate.password(),
                personaCreate.celular()
        );
        PersonaModel createdPersona = personaRepositoryPort.create(persona);
        return personaMapper.toExternal(createdPersona);
    }
}
