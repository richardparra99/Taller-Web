package com.example.demoPractica.application.mapper;

import com.example.demoPractica.domain.model.PersonaModel;

public interface PersonaMapper<T> {
    public PersonaModel toDomain(T external);
    public T toExternal(PersonaModel model);
}
