package org.example.managers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.interfaces.CalificacionStorage;
import org.example.interfaces.EstudianteStorage;
import org.example.interfaces.InscripcionStorage;
import org.example.interfaces.MateriaStorage;
import org.example.models.CalificacionModel;
import org.example.models.EstudianteModel;
import org.example.models.InscripcionModel;
import org.example.models.MateriaModel;

import java.util.List;

public class MysqlManager implements EstudianteStorage, MateriaStorage, CalificacionStorage, InscripcionStorage {
    private final EntityManager entityManager;

    public MysqlManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveEstudiante(EstudianteModel estudiante) {
        ejecutarTransaccion(() -> entityManager.persist(estudiante));
    }

    @Override
    public List<EstudianteModel> getAllEstudiantes() {
        return entityManager.createQuery("SELECT e FROM EstudianteModel e ORDER BY e.id", EstudianteModel.class).getResultList();
    }

    @Override
    public EstudianteModel findEstudianteById(int id) {
        return entityManager.find(EstudianteModel.class, id);
    }

    @Override
    public EstudianteModel findEstudianteByCodigo(String codigo) {
        return entityManager .createQuery(
                        """
                        SELECT e
                        FROM EstudianteModel e
                        WHERE e.codigo = :codigo
                        """,
                        EstudianteModel.class
                )
                .setParameter(
                        "codigo",
                        codigo
                )
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateEstudiante(EstudianteModel estudiante) {
        ejecutarTransaccion(() ->
                entityManager.merge(estudiante)
        );
    }

    @Override
    public void deleteEstudiante(int id) {
        EstudianteModel estudiante =
                findEstudianteById(id);

        if (estudiante == null) {

            System.out.println(
                    "Estudiante no encontrado."
            );

            return;
        }

        ejecutarTransaccion(() ->
                entityManager.remove(estudiante)
        );
    }

    private void ejecutarTransaccion(Runnable accion) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            accion.run();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void saveMateria(MateriaModel materia) {
        ejecutarTransaccion(() ->
                entityManager.persist(materia)
        );
    }

    @Override
    public List<MateriaModel> getAllMaterias() {
        return entityManager
                .createQuery(
                        "SELECT m FROM MateriaModel m ORDER BY m.id",
                        MateriaModel.class
                )
                .getResultList();
    }

    @Override
    public MateriaModel findMateriaById(int id) {
        return entityManager.find(
                MateriaModel.class,
                id
        );
    }

    @Override
    public void updateMateria(MateriaModel materia) {
        ejecutarTransaccion(() ->
                entityManager.merge(materia)
        );
    }

    @Override
    public void deleteMateria(int id) {
        MateriaModel materia =
                findMateriaById(id);

        if (materia == null) {

            System.out.println(
                    "Materia no encontrada."
            );

            return;
        }

        ejecutarTransaccion(() ->
                entityManager.remove(materia)
        );
    }

    @Override
    public void saveCalificacion(CalificacionModel calificacion) {
        ejecutarTransaccion(() ->
                entityManager.persist(calificacion)
        );
    }

    @Override
    public List<CalificacionModel> getAllCalificaciones() {
        return entityManager
                .createQuery(
                        """
                        SELECT c
                        FROM CalificacionModel c
                        ORDER BY c.id
                        """,
                        CalificacionModel.class
                )
                .getResultList();
    }

    @Override
    public CalificacionModel findCalificacionById(int id) {
        return entityManager.find(
                CalificacionModel.class,
                id
        );
    }

    @Override
    public List<CalificacionModel> findCalificacionesByInscripcionId(int inscripcionId) {
        return entityManager
                .createQuery(
                        """
                        SELECT c
                        FROM CalificacionModel c
                        WHERE c.inscripcion.id = :inscripcionId
                        ORDER BY c.id
                        """,
                        CalificacionModel.class
                )
                .setParameter(
                        "inscripcionId",
                        inscripcionId
                )
                .getResultList();
    }

    @Override
    public void updateCalificacion(CalificacionModel calificacion) {
        ejecutarTransaccion(() ->
                entityManager.merge(calificacion)
        );
    }

    @Override
    public void deleteCalificacion(int id) {
        CalificacionModel calificacion =
                findCalificacionById(id);

        if (calificacion == null) {

            System.out.println(
                    "Calificacion no encontrada."
            );

            return;
        }

        ejecutarTransaccion(() ->
                entityManager.remove(calificacion)
        );
    }

    @Override
    public void saveInscripcion(InscripcionModel inscripcion) {
        ejecutarTransaccion(() ->
                entityManager.persist(inscripcion)
        );
    }

    @Override
    public List<InscripcionModel> getAllInscripciones() {
        return entityManager
                .createQuery(
                        """
                        SELECT i
                        FROM InscripcionModel i
                        ORDER BY i.id
                        """,
                        InscripcionModel.class
                )
                .getResultList();
    }

    @Override
    public InscripcionModel findInscripcionById(int id) {
        return entityManager.find(
                InscripcionModel.class,
                id
        );
    }

    @Override
    public List<InscripcionModel> findInscripcionesByEstudianteId(int estudianteId) {
        return entityManager
                .createQuery(
                        """
                        SELECT i
                        FROM InscripcionModel i
                        WHERE i.estudiante.id = :estudianteId
                        ORDER BY i.id
                        """,
                        InscripcionModel.class
                )
                .setParameter(
                        "estudianteId",
                        estudianteId
                )
                .getResultList();
    }

    @Override
    public List<InscripcionModel> findInscripcionesByMateriaId(int materiaId) {
        return entityManager
                .createQuery(
                        """
                        SELECT i
                        FROM InscripcionModel i
                        WHERE i.materia.id = :materiaId
                        ORDER BY i.id
                        """,
                        InscripcionModel.class
                )
                .setParameter(
                        "materiaId",
                        materiaId
                )
                .getResultList();
    }

    @Override
    public void updateInscripcion(InscripcionModel inscripcion) {
        ejecutarTransaccion(() ->
                entityManager.merge(inscripcion)
        );
    }

    @Override
    public void deleteInscripcion(int id) {
        InscripcionModel inscripcion =
                findInscripcionById(id);

        if (inscripcion == null) {

            System.out.println(
                    "Inscripcion no encontrada."
            );

            return;
        }

        ejecutarTransaccion(() ->
                entityManager.remove(inscripcion)
        );
    }
}
