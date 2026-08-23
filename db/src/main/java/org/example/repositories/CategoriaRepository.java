package org.example.repositories;

import lombok.AllArgsConstructor;
import org.example.managers.StorageManager;
import org.example.models.CategoriaModel;

import java.util.List;

@AllArgsConstructor
public class CategoriaRepository {
    private final StorageManager storageManager;

    public void save(CategoriaModel categoria) {
        storageManager.saveCategoria(categoria);
    }

    public List<CategoriaModel> getAll() {
        return storageManager.getAllCategorias();
    }

    public CategoriaModel findById(int id) {
        return storageManager.findCategoriaById(id);
    }

    public void update(CategoriaModel categoria) {
        storageManager.updateCategoria(categoria);
    }

    public void delete(int id) {
        storageManager.deleteCategoria(id);
    }
}
