package com.example.TareaArquitectura.features.todo.list.application;

import com.example.TareaArquitectura.domain.repository.TodoRepository;
import com.example.TareaArquitectura.application.dto.TodoResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListTodoHandler {
    private final TodoRepository repository;

    public ListTodoHandler(TodoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<TodoResult> handle() {
        return repository.findAll().stream().map(TodoResult::from).toList();
    }
}
