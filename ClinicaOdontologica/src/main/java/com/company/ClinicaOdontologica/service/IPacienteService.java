package com.company.ClinicaOdontologica.service;

import com.company.ClinicaOdontologica.dto.PacienteDTO;
import com.company.ClinicaOdontologica.entity.Paciente;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {

    void actualizar (Paciente paciente);

    PacienteDTO buscarPorId(Long id) throws Exception;

    List<PacienteDTO> buscarTodos();

    void guardar(Paciente paciente);

    void eliminar(Long id) throws ResourceNotFoundException;
}
