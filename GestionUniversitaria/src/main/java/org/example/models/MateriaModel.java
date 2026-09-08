package org.example.models;

import jakarta.persistence.*;
import lombok.*;
import org.example.enums.TipoCalificacion;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "materias")
public class MateriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_calificacion", nullable = false)
    private TipoCalificacion tipoCalificacion;
}
