package org.example.managers;

import org.example.models.CategoriaModel;
import org.example.models.ProductoModel;

import java.util.ArrayList;
import java.util.List;

public class LocalStorageManager implements StorageManager {
    private final List<ProductoModel> productos = new ArrayList<>();
    private final List<CategoriaModel> categorias = new ArrayList<>();

    private int productoId = 1;
    private int categoriaId = 1;

    @Override
    public void saveProducto(ProductoModel producto) {
        producto.setId(productoId++);
        productos.add(producto);
    }

    @Override
    public List<ProductoModel> getAllProductos() {
        return productos;
    }

    @Override
    public ProductoModel findProductoById(int id) {
        return productos
                .stream()
                .filter(producto -> producto.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateProducto(ProductoModel producto) {
        ProductoModel productoEncontrado = findProductoById(producto.getId());
        if (productoEncontrado == null) {
            System.out.println("Producto no encontrado en cache");
            return;
        }
        productoEncontrado.setName(producto.getName());
        productoEncontrado.setPrice(producto.getPrice());
        productoEncontrado.setCategoria(producto.getCategoria());
    }

    @Override
    public void deleteProducto(int id) {
        productos.removeIf(producto -> producto.getId() == id);
    }

    @Override
    public void saveCategoria(CategoriaModel categoria) {
        categoria.setId(categoriaId++);
        categorias.add(categoria);
    }

    @Override
    public List<CategoriaModel> getAllCategorias() {
        return categorias;
    }

    @Override
    public CategoriaModel findCategoriaById(int id) {
        return categorias
                .stream()
                .filter(categoria -> categoria.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateCategoria(CategoriaModel categoria) {
        CategoriaModel categoriaEncontrada = findCategoriaById(categoria.getId());
        if (categoriaEncontrada == null) {
            System.out.println("Categoria no encontrada en cache");
            return;
        }
        categoriaEncontrada.setName(categoria.getName());
    }

    @Override
    public void deleteCategoria(int id) {
        categorias.removeIf(categoria -> categoria.getId() == id);
    }
}
