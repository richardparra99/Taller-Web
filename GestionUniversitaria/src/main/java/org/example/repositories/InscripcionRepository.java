package org.example.repositories;

import org.example.interfaces.InscripcionStorage;
import org.example.models.InscripcionModel;

import java.util.List;

public class InscripcionRepository {

    private final InscripcionStorage storage;


    public InscripcionRepository(InscripcionStorage storage) {
        this.storage = storage;
    }


    public void save(InscripcionModel inscripcion) {
        storage.saveInscripcion(inscripcion);
    }


    public List<InscripcionModel> getAll() {
        return storage.getAllInscripciones();
    }


    public InscripcionModel findById(int id) {
        return storage.findInscripcionById(id);
    }


    public List<InscripcionModel> findByEstudianteId(int estudianteId) {
        return storage.findInscripcionesByEstudianteId(estudianteId);
    }


    public List<InscripcionModel> findByMateriaId(int materiaId) {
        return storage.findInscripcionesByMateriaId(materiaId);
    }


    public void update(InscripcionModel inscripcion) {
        storage.updateInscripcion(inscripcion);
    }


    public void delete(int id) {
        storage.deleteInscripcion(
                id
        );
    }
}