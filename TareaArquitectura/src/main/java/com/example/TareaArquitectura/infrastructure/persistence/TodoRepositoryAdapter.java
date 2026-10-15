package com.example.TareaArquitectura.infrastructure.persistence;

import com.example.TareaArquitectura.domain.model.Todo;
import com.example.TareaArquitectura.domain.repository.TodoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class TodoRepositoryAdapter implements TodoRepository {
    private final SpringDataTodoRepository repository;
    private final TodoPersistenceMapper mapper;

    TodoRepositoryAdapter(SpringDataTodoRepository repository, TodoPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Todo save(Todo todo) {
        return mapper.toDomain(repository.save(mapper.toEntity(todo)));
    }

    @Override
    public List<Todo> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Todo> findById(long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
