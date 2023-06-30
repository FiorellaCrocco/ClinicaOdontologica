package com.company.ClinicaOdontologica.dto;


import com.company.ClinicaOdontologica.entity.Odontologo;
import com.company.ClinicaOdontologica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter  // Creamos los Getters y Setters
@NoArgsConstructor // Constructor sin parámetros
@JsonIgnoreProperties(ignoreUnknown = true)   // Definimos que se ignoren los atributos que no están detallados abajo (El ID)

public class TurnoDTO {

    private Long id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDateTime fecha;

}
