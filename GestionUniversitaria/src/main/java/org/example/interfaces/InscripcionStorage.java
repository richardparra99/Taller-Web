package org.example.interfaces;

import org.example.models.InscripcionModel;

import java.util.List;

public interface InscripcionStorage {
    void saveInscripcion(InscripcionModel inscripcion);
    List<InscripcionModel> getAllInscripciones();
    InscripcionModel findInscripcionById(int id);
    List<InscripcionModel> findInscripcionesByEstudianteId(int estudianteId);
    List<InscripcionModel> findInscripcionesByMateriaId(int materiaId);
    void updateInscripcion(InscripcionModel inscripcion);
    void deleteInscripcion(int id);
}
