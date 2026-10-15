package com.example.TareaArquitectura.features.todo.get.presentation;

import com.example.TareaArquitectura.features.todo.get.application.GetTodoHandler;
import com.example.TareaArquitectura.application.dto.TodoResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class GetTodoController {
    private final GetTodoHandler handler;

    public GetTodoController(GetTodoHandler handler) {
        this.handler = handler;
    }

    @GetMapping("/{id}")
    public TodoResult get(@PathVariable long id) {
        return handler.handle(id);
    }
}
