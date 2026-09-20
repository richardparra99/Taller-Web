package com.example.demoPractica.application;

import com.example.demoPractica.domain.model.PersonaModel;
import com.example.demoPractica.domain.repositories.PersonaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    private final PersonaRepositories personaRepositories;

    public PersonaService(PersonaRepositories personaRepositories) {
        this.personaRepositories = personaRepositories;
    }

    public PersonaModel crearPersona(String nombre, String celular) {
        PersonaModel persona = new PersonaModel(null, nombre, celular);
        return personaRepositories.save(persona);
    }

    public List<PersonaModel> listar() {
        return personaRepositories.getAll();
    }

    public PersonaModel buscarPorId(Long id) {
        return personaRepositories.findById(id);
    }

    public PersonaModel actualizarPersona(Long id, String nombre, String celular) {
        PersonaModel persona = new PersonaModel(id, nombre, celular);
        return personaRepositories.update(id, persona);
    }

    public boolean eliminarPersona(Long id) {
        return personaRepositories.delete(id);
    }
}
