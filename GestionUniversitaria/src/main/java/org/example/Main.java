package org.example;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.managers.LocalStorageManager;
import org.example.managers.MysqlManager;
import org.example.models.EstudianteModel;
import org.example.repositories.EstudianteRepository;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("config-mysql");
            entityManager = entityManagerFactory.createEntityManager();
            System.out.println();
            System.out.println("================================");
            System.out.println("Conexión a la base de datos establecida correctamente.");
            System.out.println("Gestión Universitaria");
            System.out.println("================================");

            MysqlManager mysqlManager = new MysqlManager(entityManager);
            EstudianteRepository mysqlRepository = new EstudianteRepository(mysqlManager);

            EstudianteModel estudianteMysql = new EstudianteModel();

            estudianteMysql.setCodigo("EST-MYSQL-001");

            estudianteMysql.setNombre("Pablo Arauz");

            estudianteMysql.setCorreo("pablo.mysql@universidad.com");

            estudianteMysql.setCarrera("Ingenieria de Sistemas");

            if (mysqlRepository.findByCodigo("EST-MYSQL-001") == null) {
                mysqlRepository.save(estudianteMysql);
                System.out.println("Estudiante guardado en MYSQL.");
            } else {
                System.out.println("El estudiante MYSQL ya existe.");
            }

            System.out.println("===== ESTUDIANTES MYSQL =====");
            mysqlRepository
                    .getAll()
                    .forEach(estudiante -> {
                                System.out.println("ID: " + estudiante.getId());
                                System.out.println("Codigo: " + estudiante.getCodigo());
                                System.out.println("Nombre: " + estudiante.getNombre());
                                System.out.println("Correo: " + estudiante.getCorreo());
                                System.out.println("Carrera: " + estudiante.getCarrera());
                                System.out.println("--------------------------");
                            });
            LocalStorageManager localStorageManager = new LocalStorageManager();
            EstudianteRepository localRepository = new EstudianteRepository(localStorageManager);
            EstudianteModel estudianteLocal = new EstudianteModel();
            estudianteLocal.setCodigo("EST-LOCAL-001");
            estudianteLocal.setNombre("Maria Lopez");
            estudianteLocal.setCorreo("maria.local@universidad.com");
            estudianteLocal.setCarrera("Ingenieria Comercial");
            localRepository.save(estudianteLocal);
            System.out.println();
            System.out.println("===== ESTUDIANTES LOCAL STORAGE =====");
            localRepository
                    .getAll()
                    .forEach(estudiante -> {
                        System.out.println("ID: " + estudiante.getId());
                        System.out.println("Codigo: " + estudiante.getCodigo());
                        System.out.println("Nombre: " + estudiante.getNombre());
                        System.out.println("Correo: " + estudiante.getCorreo());
                        System.out.println("Carrera: " + estudiante.getCarrera());
                        System.out.println("--------------------------");
                    });

        } catch (Exception e) {
            System.out.println("Error al establecer la conexión a la base de datos");
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
