package com.example.TareaArquitectura.features.todo.update.presentation;

import com.example.TareaArquitectura.features.todo.update.application.UpdateTodoHandler;
import com.example.TareaArquitectura.application.dto.TodoResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class UpdateTodoController {
    private final UpdateTodoHandler handler;

    public UpdateTodoController(UpdateTodoHandler handler) {
        this.handler = handler;
    }

    @PutMapping("/{id}")
    public TodoResult update(@PathVariable long id, @RequestBody UpdateTodoRequest request) {
        if (request == null) throw new IllegalArgumentException("Debes enviar un JSON válido");
        return handler.handle(id, request.title(), request.description());
    }
}
