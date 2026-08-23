package org.example.repositories;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.example.managers.StorageManager;
import org.example.models.ProductoModel;

import java.util.List;

@AllArgsConstructor
public class ProductoRepository {
    private final StorageManager storageManager;

    public void save(ProductoModel producto){
        storageManager.saveProducto(producto);
    }

    public List<ProductoModel> getAll() {
        return storageManager.getAllProductos();
    }

    public ProductoModel findById(int id) {
        return storageManager.findProductoById(id);
    }

    public void update(ProductoModel producto) {
        storageManager.updateProducto(producto);
    }

    public void delete(int id) {
        storageManager.deleteProducto(id);
    }
}
