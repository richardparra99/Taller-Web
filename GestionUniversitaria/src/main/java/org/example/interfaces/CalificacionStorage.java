package org.example.interfaces;

import org.example.models.CalificacionModel;

import java.util.List;

public interface CalificacionStorage {
    void saveCalificacion(CalificacionModel calificacion);
    List<CalificacionModel> getAllCalificaciones();
    CalificacionModel findCalificacionById(int id);
    List<CalificacionModel> findCalificacionesByInscripcionId(int inscripcionId);
    void updateCalificacion(CalificacionModel calificacion);
    void deleteCalificacion(int id);
}
