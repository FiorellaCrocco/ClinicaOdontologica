package com.company.ClinicaOdontologica.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS") // Establecemos el nombre de la tabla en la BD
@Getter @Setter  // Creamos los Getters y Setters
@NoArgsConstructor // Constructor sin parámetros
@AllArgsConstructor // Constructor con todos los parámetros
@ToString
public class Turno {

    // Establecemos el Id como Primary Key de tipo indentity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Establecemos la relación con la tabla Paciente del tipo uno a uno, y definimos como Foreign Key el Id de Paciente
    // Utilizamos el patrón LAZY para establecer la inicialización al momento de recibir una solicitud
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="paciente_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull()
    private Paciente paciente;

    // Establecemos la relación con la tabla Odontologo del tipo muchos a uno, y definimos como Foreign Key el Id de Odontologo
    // Utilizamos el patrón LAZY para establecer la inicialización al momento de recibir una solicitud
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="odontologo_id")
    @NotNull()
    private Odontologo odontologo;

    @NotNull()
    private LocalDateTime fecha;

    public Turno(Paciente paciente, Odontologo odontologo, LocalDateTime fecha) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

}
