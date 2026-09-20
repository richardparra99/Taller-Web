package com.example.demoPractica.infrastructure;

import com.example.demoPractica.domain.model.PersonaModel;
import com.example.demoPractica.domain.repositories.PersonaRepositories;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonaRepositoryMemory implements PersonaRepositories {
    private final List<PersonaModel> personas = new ArrayList<>();
    private Long contadorId = 1L;

    @Override
    public PersonaModel save(PersonaModel persona) {
        PersonaModel personaNueva = new PersonaModel(contadorId++, persona.getNombre().getValor(), persona.getCelular());
        personas.add(personaNueva);
        return personaNueva;
    }

    @Override
    public List<PersonaModel> getAll() {
        return new ArrayList<>(personas);
    }

    @Override
    public PersonaModel findById(Long id) {
        return personas.stream()
                .filter(persona -> persona.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public PersonaModel update(Long id, PersonaModel persona) {
        PersonaModel encontrada = findById(id);

        if (encontrada == null) {
            return null;
        }
        PersonaModel personaActualizada = new PersonaModel(id, persona.getNombre().getValor(), persona.getCelular());
        int posicion = personas.indexOf(encontrada);
        personas.set(posicion, personaActualizada);
        return personaActualizada;
    }

    @Override
    public boolean delete(Long id) {
        return personas.removeIf(persona -> persona.getId().equals(id));
    }
}
