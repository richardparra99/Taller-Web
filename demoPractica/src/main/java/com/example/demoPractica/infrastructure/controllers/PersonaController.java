package com.example.demoPractica.infrastructure.controllers;

import com.example.demoPractica.application.PersonaService;
import com.example.demoPractica.domain.model.PersonaModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public PersonaModel crearPersona(@RequestBody PersonaRequest request) {
        return personaService.crearPersona(request.nombre(), request.celular());
    }

    @GetMapping
    public List<PersonaModel> listar() {
        return personaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaModel> buscarPorId(@PathVariable Long id) {
        PersonaModel persona = personaService.buscarPorId(id);
        if (persona == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(persona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaModel> actualizarPersona(@PathVariable Long id, @RequestBody PersonaRequest request) {
        PersonaModel persona = personaService.actualizarPersona(id, request.nombre(), request.celular());
        if (persona == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(persona);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        boolean eliminado = personaService.eliminarPersona(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
