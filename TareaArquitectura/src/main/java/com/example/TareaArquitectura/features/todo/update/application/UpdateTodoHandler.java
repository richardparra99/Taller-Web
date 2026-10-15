package com.example.TareaArquitectura.features.todo.update.application;

import com.example.TareaArquitectura.domain.repository.TodoRepository;
import com.example.TareaArquitectura.domain.exception.TodoNotFoundException;
import com.example.TareaArquitectura.application.dto.TodoResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateTodoHandler {
    private final TodoRepository repository;

    public UpdateTodoHandler(TodoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TodoResult handle(long id, String title, String description) {
        var todo = repository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
        todo.update(title, description);
        return TodoResult.from(repository.save(todo));
    }
}
