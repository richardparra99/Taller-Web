package com.example.demoPractica.application.port.in;

import com.example.demoPractica.application.dto.PersonaCreateRequest;
import com.example.demoPractica.application.dto.PersonaDto;

public interface PersonaCreate {
    PersonaDto create(PersonaCreateRequest personaCreate);
}
