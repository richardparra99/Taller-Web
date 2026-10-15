package com.example.TareaArquitectura;
import com.example.TareaArquitectura.domain.model.*;
import com.example.TareaArquitectura.domain.repository.TodoRepository;
import com.example.TareaArquitectura.features.todo.create.application.CreateTodoHandler;
import com.example.TareaArquitectura.features.todo.complete.application.CompleteTodoHandler;
import com.example.TareaArquitectura.features.todo.delete.application.DeleteTodoHandler;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
class ArchitectureAndHandlerTests {
 static class MemoryRepo implements TodoRepository {
  Map<Long,Todo> data=new HashMap<>();long current=0;
  public Todo save(Todo t){var saved=new Todo(t.getId()==null?++current:t.getId(),t.getTitle(),t.getDescription(),t.getStatus(),t.getCreatedAt(),t.getUpdatedAt());data.put(saved.getId(),saved);return saved;}
  public List<Todo> findAll(){return new ArrayList<>(data.values());}
  public Optional<Todo> findById(long id){return Optional.ofNullable(data.get(id));}
  public void deleteById(long id){data.remove(id);}
 }
 @Test void handlersNoNecesitanJpa(){
  var repo=new MemoryRepo();var created=new CreateTodoHandler(repo).handle("Comprar leche", "");
  assertEquals(1L,created.id());assertEquals(TodoStatus.PENDING,created.status());
  var completed=new CompleteTodoHandler(repo).handle(created.id());assertEquals(TodoStatus.COMPLETED,completed.status());
  new DeleteTodoHandler(repo).handle(created.id());assertTrue(repo.findAll().isEmpty());
 }
 @Test void dependenciasDelCodigoRespetanCapas() throws Exception {
  var folder=java.nio.file.Path.of("src/main/java/com/example/TareaArquitectura");
  try(var paths=java.nio.file.Files.walk(folder)){
   for(var file:paths.filter(f->f.toString().endsWith(".java")).toList()){
    var s=java.nio.file.Files.readString(file);var normalized=file.toString().replace('\\','/');
    if(normalized.contains("/domain/")){assertFalse(s.contains("import com.example.TareaArquitectura.infrastructure."),file.toString());assertFalse(s.contains("import org.springframework."),file.toString());}
    if(normalized.contains("/presentation/"))assertFalse(s.contains("import com.example.TareaArquitectura.infrastructure."),file.toString());
    if(normalized.contains("/application/")||normalized.contains("/domain/"))assertFalse(s.contains("import com.example.TareaArquitectura.features.todo.")&&s.contains(".presentation."),file.toString());
    if(normalized.contains("/application/"))assertFalse(s.contains("import com.example.TareaArquitectura.infrastructure."),file.toString());
   }
  }
 }
}
