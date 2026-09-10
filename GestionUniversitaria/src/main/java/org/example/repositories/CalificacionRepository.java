package org.example.repositories;

import org.example.interfaces.CalificacionStorage;
import org.example.models.CalificacionModel;

import java.util.List;

public class CalificacionRepository {

    private final CalificacionStorage storage;


    public CalificacionRepository(
            CalificacionStorage storage
    ) {

        this.storage = storage;
    }


    public void save(
            CalificacionModel calificacion
    ) {

        storage.saveCalificacion(
                calificacion
        );
    }


    public List<CalificacionModel> getAll() {

        return storage
                .getAllCalificaciones();
    }


    public CalificacionModel findById(
            int id
    ) {

        return storage
                .findCalificacionById(id);
    }


    public List<CalificacionModel> findByInscripcionId(
            int inscripcionId
    ) {

        return storage
                .findCalificacionesByInscripcionId(
                        inscripcionId
                );
    }


    public void update(
            CalificacionModel calificacion
    ) {

        storage.updateCalificacion(
                calificacion
        );
    }


    public void delete(
            int id
    ) {

        storage.deleteCalificacion(
                id
        );
    }
}