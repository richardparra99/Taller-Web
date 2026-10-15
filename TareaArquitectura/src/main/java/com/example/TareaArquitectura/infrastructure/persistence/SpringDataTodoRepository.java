package com.example.TareaArquitectura.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface SpringDataTodoRepository extends JpaRepository<TodoJpaEntity, Long> {
}
