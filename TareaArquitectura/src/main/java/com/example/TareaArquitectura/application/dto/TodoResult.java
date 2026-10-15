package com.example.TareaArquitectura.application.dto;

import com.example.TareaArquitectura.domain.model.Todo;
import com.example.TareaArquitectura.domain.model.TodoStatus;

import java.time.LocalDateTime;

public record TodoResult(Long id, String title, String description, TodoStatus status, LocalDateTime createdAt,
                         LocalDateTime updatedAt) {
    public static TodoResult from(Todo todo) {
        return new TodoResult(todo.getId(), todo.getTitle(), todo.getDescription(), todo.getStatus(), todo.getCreatedAt(), todo.getUpdatedAt());
    }
}
