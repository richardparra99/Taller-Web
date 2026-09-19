package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.example.managers.MysqlManager;
import org.example.menus.MenuConsola;

import org.example.repositories.EstudianteRepository;
import org.example.repositories.MateriaRepository;
import org.example.repositories.InscripcionRepository;
import org.example.repositories.CalificacionRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        Scanner scanner = new Scanner(System.in);

        try {

            // ========================================
            // CONEXION MYSQL
            // ========================================

            entityManagerFactory =
                    Persistence.createEntityManagerFactory(
                            "config-mysql"
                    );

            entityManager =
                    entityManagerFactory.createEntityManager();

            System.out.println();
            System.out.println("================================");
            System.out.println(
                    "Conexión a la base de datos establecida correctamente."
            );
            System.out.println("Gestión Universitaria");
            System.out.println("================================");


            // ========================================
            // MYSQL MANAGER
            // ========================================

            MysqlManager mysqlManager =
                    new MysqlManager(entityManager);


            // ========================================
            // REPOSITORIES
            // ========================================

            EstudianteRepository estudianteRepository =
                    new EstudianteRepository(
                            mysqlManager
                    );

            MateriaRepository materiaRepository =
                    new MateriaRepository(
                            mysqlManager
                    );

            InscripcionRepository inscripcionRepository =
                    new InscripcionRepository(
                            mysqlManager
                    );

            CalificacionRepository calificacionRepository =
                    new CalificacionRepository(
                            mysqlManager
                    );


            // ========================================
            // CREAR MENU
            // ========================================

            MenuConsola menu =
                    new MenuConsola(
                            scanner,
                            estudianteRepository,
                            materiaRepository,
                            inscripcionRepository,
                            calificacionRepository
                    );


            // ========================================
            // EJECUTAR MENU
            // ========================================

            menu.iniciar();


        } catch (Exception e) {

            System.out.println(
                    "Error al establecer la conexión a la base de datos"
            );

            e.printStackTrace();

        } finally {

            if (entityManager != null) {
                entityManager.close();
            }

            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }

            scanner.close();
        }
    }
}