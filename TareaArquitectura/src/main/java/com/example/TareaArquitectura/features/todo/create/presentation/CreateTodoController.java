package com.example.TareaArquitectura.features.todo.create.presentation;

import com.example.TareaArquitectura.features.todo.create.application.CreateTodoHandler;
import com.example.TareaArquitectura.application.dto.TodoResult;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/todos")
public class CreateTodoController {
    private final CreateTodoHandler handler;

    public CreateTodoController(CreateTodoHandler handler) {
        this.handler = handler;
    }

    @PostMapping
    public ResponseEntity<TodoResult> create(@RequestBody CreateTodoRequest request) {
        if (request == null) throw new IllegalArgumentException("Debes enviar un JSON válido");
        TodoResult result = handler.handle(request.title(), request.description());
        return ResponseEntity.created(URI.create("/api/todos/" + result.id())).body(result);
    }
}
