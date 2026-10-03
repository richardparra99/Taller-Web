package com.example.demoPractica.application.port.in;

import com.example.demoPractica.application.dto.PersonaDto;

import java.util.List;

public interface PersonaGetAll {
    public List<PersonaDto> getAll();
}
