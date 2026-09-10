package org.example.repositories;

import org.example.interfaces.MateriaStorage;
import org.example.models.MateriaModel;

import java.util.List;

public class MateriaRepository {
    private final MateriaStorage storage;

    public MateriaRepository(MateriaStorage storage) {
        this.storage = storage;
    }

    public void save(MateriaModel materia) {
        storage.saveMateria(materia);
    }

    public List<MateriaModel> getAll() {
        return storage.getAllMaterias();
    }


    public MateriaModel findById(int id) {
        return storage.findMateriaById(id);
    }


    public void update(MateriaModel materia) {
        storage.updateMateria(materia);
    }


    public void delete(int id) {
        storage.deleteMateria(id);
    }
}
