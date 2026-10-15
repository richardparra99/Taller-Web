package com.example.TareaArquitectura.features.todo.complete.presentation;

import com.example.TareaArquitectura.features.todo.complete.application.CompleteTodoHandler;
import com.example.TareaArquitectura.application.dto.TodoResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class CompleteTodoController {
    private final CompleteTodoHandler handler;

    public CompleteTodoController(CompleteTodoHandler handler) {
        this.handler = handler;
    }

    @PatchMapping("/{id}/complete")
    public TodoResult complete(@PathVariable long id) {
        return handler.handle(id);
    }
}
