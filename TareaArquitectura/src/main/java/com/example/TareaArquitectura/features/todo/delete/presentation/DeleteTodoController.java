package com.example.TareaArquitectura.features.todo.delete.presentation;

import com.example.TareaArquitectura.features.todo.delete.application.DeleteTodoHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class DeleteTodoController {
    private final DeleteTodoHandler handler;

    public DeleteTodoController(DeleteTodoHandler handler) {
        this.handler = handler;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        handler.handle(id);
    }
}
