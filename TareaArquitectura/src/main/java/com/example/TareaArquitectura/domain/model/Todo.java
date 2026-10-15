package com.example.TareaArquitectura.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Todo {
    private final Long id;
    private String title;
    private String description;
    private TodoStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Todo(Long id, String title, String description, TodoStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        validate(title);
        if (description != null && description.length() > 1000)
            throw new IllegalArgumentException("La descripción admite hasta 1000 caracteres");
        this.id = id;
        this.title = title.trim();
        this.description = description == null ? "" : description.trim();
        this.status = status == null ? TodoStatus.PENDING : status;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
        this.updatedAt = updatedAt == null ? this.createdAt : updatedAt;
    }

    public static Todo create(String title, String description) {
        return new Todo(null, title, description, TodoStatus.PENDING, null, null);
    }

    private static void validate(String title) {
        if (title == null || title.isBlank() || title.trim().length() > 120)
            throw new IllegalArgumentException("El título debe tener entre 1 y 120 caracteres");
    }

    public void update(String title, String description) {
        validate(title);
        if (description != null && description.length() > 1000)
            throw new IllegalArgumentException("La descripción admite hasta 1000 caracteres");
        this.title = title.trim();
        this.description = description == null ? "" : description.trim();
        this.updatedAt = LocalDateTime.now();
    }

    public void complete() {
        status = TodoStatus.COMPLETED;
        updatedAt = LocalDateTime.now();
    }
}
