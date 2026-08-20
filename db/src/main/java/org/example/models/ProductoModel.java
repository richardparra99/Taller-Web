package org.example.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "productos")
public class ProductoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "precio", nullable = false)
    private int price;
}
