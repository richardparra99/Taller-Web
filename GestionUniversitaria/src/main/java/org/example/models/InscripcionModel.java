package org.example.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(
        name = "inscripciones", uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "estudiante_id", "materia_id"
                })
})
public class InscripcionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private EstudianteModel estudiante;

    @ManyToOne
    @JoinColumn(name = "materia_id", nullable = false)
    private MateriaModel materia;
}
