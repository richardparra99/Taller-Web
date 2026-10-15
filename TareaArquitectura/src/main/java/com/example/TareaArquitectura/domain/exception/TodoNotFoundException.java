package com.example.TareaArquitectura.domain.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(long id) {
        super("No existe la tarea con ID " + id);
    }
}
