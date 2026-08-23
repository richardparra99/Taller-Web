package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.managers.LocalStorageManager;
import org.example.managers.MysqlManager;
import org.example.models.CategoriaModel;
import org.example.models.ProductoModel;
import org.example.repositories.CategoriaRepository;
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

            entityManager = entityManagerFactory.createEntityManager();

            System.out.println("CONEXIÓN CON MYSQL EXITOSA");

            MysqlManager mysqlManager =
                    new MysqlManager(entityManager);

            ProductoRepository productoRepository =
                    new ProductoRepository(mysqlManager);

            CategoriaRepository categoriaRepository =
                    new CategoriaRepository(mysqlManager);

            CategoriaModel categoria = new CategoriaModel();
            categoria.setName("Celulares");
            categoriaRepository.save(categoria);


            // CREAR PRODUCTO
            ProductoModel producto = new ProductoModel();
            producto.setName("Iphone 17 Pro Max");
            producto.setPrice(15000);
            producto.setCategoria(categoria);
            productoRepository.save(producto);


            // MOSTRAR PRODUCTOS
            System.out.println();
            System.out.println("===== PRODUCTOS =====");

            productoRepository
                    .getAll()
                    .forEach(p -> {
                        System.out.println("ID: " + p.getId());
                        System.out.println("Producto: " + p.getName());
                        System.out.println("Precio: " + p.getPrice());
                        System.out.println("Categoria: " + p.getCategoria().getName());
                        System.out.println("-------------------------");
                    } );

            LocalStorageManager localStorageManager = new LocalStorageManager();
            ProductoRepository productoCacheRepository = new ProductoRepository(localStorageManager);
            CategoriaRepository categoriaCacheRepository = new CategoriaRepository(localStorageManager);

            CategoriaModel categoriaCache = new CategoriaModel();
            categoriaCache.setName("Computadoras");
            categoriaCacheRepository.save(categoriaCache);

            ProductoModel productoCache = new ProductoModel();
            productoCache.setName("Macbook Pro 2024");
            productoCache.setPrice(25000);
            productoCache.setCategoria(categoriaCache);
            productoCacheRepository.save(productoCache);

            System.out.println("===== PRODUCTOS EN CACHE =====");
            productoCacheRepository.getAll()
                    .forEach(p -> {
                        System.out.println("ID: " + p.getId());
                        System.out.println("Producto: " + p.getName());
                        System.out.println("Precio: " + p.getPrice());
                        System.out.println("Categoria: " + p.getCategoria().getName());
                        System.out.println("-------------------------");
                    });

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