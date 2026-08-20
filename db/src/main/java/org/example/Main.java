package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.models.ProductoModel;
import org.example.repositories.ProductoRepository;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        try {

            entityManagerFactory =
                    Persistence.createEntityManagerFactory(
                            "config-mysql"
                    );

            entityManager =
                    entityManagerFactory.createEntityManager();

            System.out.println(
                    "CONEXIÓN CON MYSQL EXITOSA"
            );

            ProductoRepository productoRepository =
                    new ProductoRepository(entityManager);


            // CREAR PRODUCTO
            ProductoModel producto =
                    new ProductoModel();

            producto.setName("Iphone 17 Pro Max");

            producto.setPrice(15000);


            // GUARDAR EN MYSQL
            productoRepository.save(producto);


            // MOSTRAR PRODUCTOS
            System.out.println();
            System.out.println("===== PRODUCTOS =====");

            productoRepository
                    .getAll()
                    .forEach(System.out::println);


        } catch (Exception e) {

            System.out.println(
                    "ERROR CONECTANDO CON MYSQL"
            );

            e.printStackTrace();

        } finally {

            if (entityManager != null) {
                entityManager.close();
            }

            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}