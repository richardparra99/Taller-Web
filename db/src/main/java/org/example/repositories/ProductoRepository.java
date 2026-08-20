package org.example.repositories;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.example.models.ProductoModel;

import java.util.List;

@AllArgsConstructor
public class ProductoRepository {
    private final EntityManager entityManager;

    public void save(ProductoModel productoModel){
        entityManager.getTransaction().begin();
        entityManager.persist(productoModel);
        entityManager.getTransaction().commit();
    }

    public List<ProductoModel> getAll() {
        return entityManager.createQuery("from ProductoModel", ProductoModel.class)
                .getResultList();
    }
}
