package org.example.interfaces;

import org.example.models.MateriaModel;

import java.util.List;

public interface MateriaStorage {
    void saveMateria(MateriaModel materia);
    List<MateriaModel> getAllMaterias();
    MateriaModel findMateriaById(int id);
    void updateMateria(MateriaModel materia);
    void deleteMateria(int id);
}
