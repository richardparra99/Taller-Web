package org.example.managers;

import org.example.models.CategoriaModel;
import org.example.models.ProductoModel;

import java.util.List;

public interface StorageManager {
    void saveProducto(ProductoModel producto);
    List<ProductoModel> getAllProductos();
    ProductoModel findProductoById(int id);

    void updateProducto(ProductoModel producto);
    void deleteProducto(int id);

    void saveCategoria(CategoriaModel categoria);
    List<CategoriaModel> getAllCategorias();
    CategoriaModel findCategoriaById(int id);

    void updateCategoria(CategoriaModel categoria);
    void deleteCategoria(int id);
}
