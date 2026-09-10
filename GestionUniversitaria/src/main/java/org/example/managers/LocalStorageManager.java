package org.example.managers;

import org.example.interfaces.CalificacionStorage;
import org.example.interfaces.EstudianteStorage;
import org.example.interfaces.InscripcionStorage;
import org.example.interfaces.MateriaStorage;
import org.example.models.CalificacionModel;
import org.example.models.EstudianteModel;
import org.example.models.InscripcionModel;
import org.example.models.MateriaModel;

import java.util.ArrayList;
import java.util.List;

public class LocalStorageManager implements EstudianteStorage, MateriaStorage, InscripcionStorage, CalificacionStorage {

    private final List<EstudianteModel> estudiantes =
            new ArrayList<>();

    private final List<MateriaModel> materias =
            new ArrayList<>();

    private final List<InscripcionModel> inscripciones =
            new ArrayList<>();

    private final List<CalificacionModel> calificaciones =
            new ArrayList<>();

    private int estudianteId = 1;

    private int materiaId = 1;

    private int inscripcionId = 1;

    private int calificacionId = 1;


    @Override
    public void saveEstudiante(EstudianteModel estudiante) {
        estudiante.setId(
                estudianteId++
        );

        estudiantes.add(
                estudiante
        );
    }

    @Override
    public List<EstudianteModel> getAllEstudiantes() {
        return new ArrayList<>(
                estudiantes
        );
    }

    @Override
    public EstudianteModel findEstudianteById(int id) {
        return estudiantes
                .stream()
                .filter(
                        estudiante ->
                                estudiante.getId() == id
                )
                .findFirst()
                .orElse(null);
    }

    @Override
    public EstudianteModel findEstudianteByCodigo(String codigo) {
        return estudiantes
                .stream()
                .filter(
                        estudiante ->
                                estudiante
                                        .getCodigo()
                                        .equalsIgnoreCase(
                                                codigo
                                        )
                )
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateEstudiante(EstudianteModel estudiante) {
        EstudianteModel encontrado =
                findEstudianteById(
                        estudiante.getId()
                );

        if (encontrado == null) {

            System.out.println(
                    "Estudiante no encontrado."
            );

            return;
        }

        encontrado.setCodigo(
                estudiante.getCodigo()
        );

        encontrado.setNombre(
                estudiante.getNombre()
        );

        encontrado.setCorreo(
                estudiante.getCorreo()
        );

        encontrado.setCarrera(
                estudiante.getCarrera()
        );
    }

    @Override
    public void deleteEstudiante(int id) {
        estudiantes.removeIf(
                estudiante ->
                        estudiante.getId() == id
        );
    }

    @Override
    public void saveMateria(MateriaModel materia) {
        materia.setId(
                materiaId++
        );

        materias.add(
                materia
        );
    }

    @Override
    public List<MateriaModel> getAllMaterias() {
        return new ArrayList<>(
                materias
        );
    }

    @Override
    public MateriaModel findMateriaById(int id) {
        return materias
                .stream()
                .filter(
                        materia ->
                                materia.getId() == id
                )
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateMateria(MateriaModel materia) {
        MateriaModel encontrada =
                findMateriaById(
                        materia.getId()
                );

        if (encontrada == null) {

            System.out.println(
                    "Materia no encontrada."
            );

            return;
        }

        encontrada.setNombre(
                materia.getNombre()
        );

        encontrada.setTipoCalificacion(
                materia.getTipoCalificacion()
        );
    }

    @Override
    public void deleteMateria(int id) {
        materias.removeIf(
                materia ->
                        materia.getId() == id
        );
    }

    @Override
    public void saveInscripcion(InscripcionModel inscripcion) {
        inscripcion.setId(
                inscripcionId++
        );

        inscripciones.add(
                inscripcion
        );
    }

    @Override
    public List<InscripcionModel> getAllInscripciones() {
        return new ArrayList<>(
                inscripciones
        );
    }

    @Override
    public InscripcionModel findInscripcionById(int id) {
        return inscripciones
                .stream()
                .filter(
                        inscripcion ->
                                inscripcion.getId() == id
                )
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<InscripcionModel> findInscripcionesByEstudianteId(int estudianteId) {
        return inscripciones
                .stream()
                .filter(
                        inscripcion ->
                                inscripcion
                                        .getEstudiante()
                                        .getId()
                                        == estudianteId
                )
                .toList();
    }

    @Override
    public List<InscripcionModel> findInscripcionesByMateriaId(int materiaId) {
        return inscripciones
                .stream()
                .filter(
                        inscripcion ->
                                inscripcion
                                        .getMateria()
                                        .getId()
                                        == materiaId
                )
                .toList();
    }

    @Override
    public void updateInscripcion(InscripcionModel inscripcion) {
        InscripcionModel encontrada =
                findInscripcionById(
                        inscripcion.getId()
                );

        if (encontrada == null) {

            System.out.println(
                    "Inscripcion no encontrada."
            );

            return;
        }

        encontrada.setEstudiante(
                inscripcion.getEstudiante()
        );

        encontrada.setMateria(
                inscripcion.getMateria()
        );
    }

    @Override
    public void deleteInscripcion(int id) {
        inscripciones.removeIf(
                inscripcion ->
                        inscripcion.getId() == id
        );
    }

    @Override
    public void saveCalificacion(CalificacionModel calificacion) {
        calificacion.setId(
                calificacionId++
        );

        calificaciones.add(
                calificacion
        );
    }

    @Override
    public List<CalificacionModel> getAllCalificaciones() {
        return new ArrayList<>(
                calificaciones
        );
    }

    @Override
    public CalificacionModel findCalificacionById(int id) {
        return calificaciones
                .stream()
                .filter(
                        calificacion ->
                                calificacion.getId() == id
                )
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<CalificacionModel> findCalificacionesByInscripcionId(int inscripcionId) {
        return calificaciones
                .stream()
                .filter(
                        calificacion ->
                                calificacion
                                        .getInscripcion()
                                        .getId()
                                        == inscripcionId
                )
                .toList();
    }

    @Override
    public void updateCalificacion(CalificacionModel calificacion) {
        CalificacionModel encontrada =
                findCalificacionById(
                        calificacion.getId()
                );

        if (encontrada == null) {

            System.out.println(
                    "Calificacion no encontrada."
            );

            return;
        }

        encontrada.setInscripcion(
                calificacion.getInscripcion()
        );

        encontrada.setNombreEvaluacion(
                calificacion.getNombreEvaluacion()
        );

        encontrada.setNota(
                calificacion.getNota()
        );

        encontrada.setPonderacion(
                calificacion.getPonderacion()
        );
    }

    @Override
    public void deleteCalificacion(int id) {
        calificaciones.removeIf(
                calificacion ->
                        calificacion.getId() == id
        );
    }
}
