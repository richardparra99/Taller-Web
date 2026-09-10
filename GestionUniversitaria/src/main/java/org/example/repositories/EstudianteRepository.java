package org.example.repositories;

import org.example.interfaces.EstudianteStorage;
import org.example.models.EstudianteModel;

import java.util.List;

public class EstudianteRepository {
    private final EstudianteStorage storage;

    public EstudianteRepository(EstudianteStorage storage) {
        this.storage = storage;
    }

    public void save(EstudianteModel estudiante) {
        storage.saveEstudiante(estudiante);
    }

    public List<EstudianteModel> getAll() {
        return storage.getAllEstudiantes();
    }

    public EstudianteModel findById(int id) {
        return storage.findEstudianteById(id);
    }

    public EstudianteModel findByCodigo(String codigo) {
        return storage.findEstudianteByCodigo(codigo);
    }

    public void update(EstudianteModel estudiante) {
        storage.updateEstudiante(estudiante);
    }

    public void delete(int id) {
        storage.deleteEstudiante(id);
    }
}
