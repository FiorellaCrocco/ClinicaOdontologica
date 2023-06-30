package com.company.ClinicaOdontologica.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "PACIENTES") // Establecemos el nombre de la tabla en la BD
@Getter @Setter  // Creamos los Getters y Setters
@NoArgsConstructor // Constructor sin parámetros
@AllArgsConstructor // Constructor con todos los parámetros
@ToString
public class Paciente {
    // Establecemos el Id como Primary Key de tipo indentity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull()
    @NotBlank()
    private String nombre;

    @NotNull()
    @NotBlank()
    private String apellido;

    @NotNull()
    @NotBlank()
    private String DNI;

    @NotNull()
    @NotBlank()
    private String email;

    private LocalDate fechaAlta;

    // Establecemos la relación con la tabla Domicilio del tipo uno a uno, y definimos como Foreign Key el Id de Domicilio
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="domicilio_id")
    private Domicilio domicilio;

    public Paciente(String nombre, String apellido, String DNI, String email, Domicilio domicilio, LocalDate fechaAlta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.email = email;
        this.domicilio = domicilio;
        this.fechaAlta = fechaAlta;
    }

}
