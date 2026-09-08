package org.example.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "calificaciones")
public class CalificacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "inscripcion_id", nullable = false)
    private IncripcionModel inscripcion;

    @Column(name = "nombre_evaluacion", nullable = false)
    private String nombreEvaluacion;

    @Column(name = "nota", nullable = false)
    private double nota;

    @Column(name = "ponderacion")
    private double ponderacion;
}
