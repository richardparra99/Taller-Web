package com.example.TareaArquitectura.domain.repository;

import com.example.TareaArquitectura.domain.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    Todo save(Todo todo);

    List<Todo> findAll();

    Optional<Todo> findById(long id);

    void deleteById(long id);
}
