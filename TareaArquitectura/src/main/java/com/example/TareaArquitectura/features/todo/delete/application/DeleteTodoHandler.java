package com.example.TareaArquitectura.features.todo.delete.application;

import com.example.TareaArquitectura.domain.repository.TodoRepository;
import com.example.TareaArquitectura.domain.exception.TodoNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteTodoHandler {
    private final TodoRepository repository;

    public DeleteTodoHandler(TodoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void handle(long id) {
        repository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
        repository.deleteById(id);
    }
}
