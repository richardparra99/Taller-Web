package com.example.TareaArquitectura.features.todo.create.application;

import com.example.TareaArquitectura.domain.model.Todo;
import com.example.TareaArquitectura.domain.repository.TodoRepository;
import com.example.TareaArquitectura.application.dto.TodoResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTodoHandler {
    private final TodoRepository repository;

    public CreateTodoHandler(TodoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TodoResult handle(String title, String description) {
        return TodoResult.from(repository.save(Todo.create(title, description)));
    }
}
