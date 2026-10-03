package com.example.demoPractica.application.port.in;

import com.example.demoPractica.application.dto.PersonaDto;

public interface PersonaGetById {
    public PersonaDto getById(int id);
}
