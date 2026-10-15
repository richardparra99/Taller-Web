package com.example.TareaArquitectura.features.todo.list.presentation;

import com.example.TareaArquitectura.features.todo.list.application.ListTodoHandler;
import com.example.TareaArquitectura.application.dto.TodoResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ListTodoController {
    private final ListTodoHandler handler;

    public ListTodoController(ListTodoHandler handler) {
        this.handler = handler;
    }

    @GetMapping
    public List<TodoResult> list() {
        return handler.handle();
    }
}
