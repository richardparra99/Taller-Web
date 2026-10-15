package com.example.TareaArquitectura.features.todo.complete.application;

import com.example.TareaArquitectura.domain.repository.TodoRepository;
import com.example.TareaArquitectura.domain.exception.TodoNotFoundException;
import com.example.TareaArquitectura.application.dto.TodoResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompleteTodoHandler {
    private final TodoRepository repository;

    public CompleteTodoHandler(TodoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TodoResult handle(long id) {
        var todo = repository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
        todo.complete();
        return TodoResult.from(repository.save(todo));
    }
}
