package org.example.interfaces;

import org.example.models.EstudianteModel;

import java.util.List;

public interface EstudianteStorage {
    void saveEstudiante(EstudianteModel estudiante);
    List<EstudianteModel> getAllEstudiantes();
    EstudianteModel findEstudianteById(int id);
    EstudianteModel findEstudianteByCodigo(String codigo);
    void updateEstudiante(EstudianteModel estudiante);
    void deleteEstudiante(int id);
}
