package com.example.TareaArquitectura.infrastructure.persistence;

import com.example.TareaArquitectura.domain.model.Todo;
import com.example.TareaArquitectura.domain.model.TodoStatus;
import org.springframework.stereotype.Component;

@Component
class TodoPersistenceMapper {
    TodoJpaEntity toEntity(Todo t) {
        return new TodoJpaEntity(t.getId(), t.getTitle(), t.getDescription(), t.getStatus().name(), t.getCreatedAt(), t.getUpdatedAt());
    }

    Todo toDomain(TodoJpaEntity e) {
        return new Todo(e.getId(), e.getTitle(), e.getDescription(), TodoStatus.valueOf(e.getStatus()), e.getCreatedAt(), e.getUpdatedAt());
    }
}
