package com.example.TareaArquitectura;
import com.example.TareaArquitectura.domain.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TareaArquitecturaApplicationTests {
 @Test void crearYCompletarTarea() {
  Todo todo=Todo.create("Estudiar Spring", "Aprender arquitectura");
  assertEquals(TodoStatus.PENDING,todo.getStatus());
  todo.complete();assertEquals(TodoStatus.COMPLETED,todo.getStatus());
 }
 @Test void tituloVacioEsInvalido() {assertThrows(IllegalArgumentException.class,()->Todo.create("  ",""));}
}
