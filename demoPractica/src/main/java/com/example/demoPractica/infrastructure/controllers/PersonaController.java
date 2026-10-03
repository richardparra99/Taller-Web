package com.example.demoPractica.infrastructure.controllers;

import com.example.demoPractica.application.dto.PersonaCreateRequest;
import com.example.demoPractica.application.dto.PersonaDto;
import com.example.demoPractica.application.port.in.PersonaCreate;
import com.example.demoPractica.application.port.in.PersonaGetAll;
import com.example.demoPractica.application.port.in.PersonaGetById;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/persona")
@Tag(name = "Persona", description = "fdjflajdsalfas")
public class PersonaController {
    private final PersonaGetAll personaGetAll;
    private final PersonaGetById personaGetById;
    private final PersonaCreate personaCreate;

    @GetMapping
    @Operation(summary = "un usuario")
    public PersonaDto getById(int id) {
        return personaGetById.getById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "todos los usuarios")
    public List<PersonaDto> getAll() {
        return personaGetAll.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonaDto createPersona(@RequestBody PersonaCreateRequest request) {
        return personaCreate.create(request);
    }
}
