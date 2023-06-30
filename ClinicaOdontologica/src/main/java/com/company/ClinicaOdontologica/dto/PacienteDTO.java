package com.company.ClinicaOdontologica.dto;

import com.company.ClinicaOdontologica.entity.Domicilio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter  // Creamos los Getters y Setters
@NoArgsConstructor // Constructor sin parámetros
@JsonIgnoreProperties(ignoreUnknown = true)   // Definimos que se ignoren los atributos que no están detallados abajo (El ID)

public class PacienteDTO {
    private Long id;
    private String nombre, apellido, DNI, email;
    private Domicilio domicilio;
    private LocalDate fechaAlta;

}
