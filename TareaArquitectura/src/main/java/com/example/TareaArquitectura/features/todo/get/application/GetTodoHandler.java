package com.example.TareaArquitectura.features.todo.get.application;

import com.example.TareaArquitectura.domain.repository.TodoRepository;
import com.example.TareaArquitectura.domain.exception.TodoNotFoundException;
import com.example.TareaArquitectura.application.dto.TodoResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetTodoHandler {
    private final TodoRepository repository;

    public GetTodoHandler(TodoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public TodoResult handle(long id) {
        return TodoResult.from(repository.findById(id).orElseThrow(() -> new TodoNotFoundException(id)));
    }
}
