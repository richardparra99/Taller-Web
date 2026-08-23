package org.example.managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.models.CategoriaModel;
import org.example.models.ProductoModel;

import java.util.List;

public class MysqlManager implements StorageManager{
    private final EntityManager entityManager;

    public MysqlManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveProducto(ProductoModel producto) {
        ejecutarTransaccion(() -> entityManager.persist(producto));
    }

    @Override
    public List<ProductoModel> getAllProductos() {
        return entityManager.createQuery("SELECT p FROM ProductoModel p ORDER BY p.id",
                ProductoModel.class).getResultList();
    }

    @Override
    public ProductoModel findProductoById(int id) {
        return entityManager.find(ProductoModel.class, id);
    }

    @Override
    public void updateProducto(ProductoModel producto) {
        ejecutarTransaccion(() -> entityManager.merge(producto));
    }

    @Override
    public void deleteProducto(int id) {
        ProductoModel producto = findProductoById(id);

        if (producto == null) {
            System.out.println("Producto no encotrado");
            return;
        }
        ejecutarTransaccion(() -> entityManager.remove(producto));
    }

    @Override
    public void saveCategoria(CategoriaModel categoria) {
        ejecutarTransaccion(() -> entityManager.persist(categoria));
    }

    @Override
    public List<CategoriaModel> getAllCategorias() {
        return entityManager.createQuery("SELECT c FROM CategoriaModel c ORDER BY c.id",
                CategoriaModel.class).getResultList();
    }

    @Override
    public CategoriaModel findCategoriaById(int id) {
        return entityManager.find(CategoriaModel.class, id);
    }

    @Override
    public void updateCategoria(CategoriaModel categoria) {
        ejecutarTransaccion(() -> entityManager.merge(categoria));
    }

    @Override
    public void deleteCategoria(int id) {
        CategoriaModel categoria = findCategoriaById(id);

        if (categoria == null) {
            System.out.println("Categoria no encotrada");
            return;
        }
        ejecutarTransaccion(() -> entityManager.remove(categoria));
    }

    private void ejecutarTransaccion(Runnable accion) {
        EntityTransaction transaccion = entityManager.getTransaction();
        try {

            transaccion.begin();

            accion.run();

            transaccion.commit();

        } catch (Exception e) {

            if (transaccion.isActive()) {
                transaccion.rollback();
            }

            throw e;
        }
    }
}
