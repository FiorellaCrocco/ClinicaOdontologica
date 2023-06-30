package com.company.ClinicaOdontologica.service;

import com.company.ClinicaOdontologica.dto.TurnoDTO;
import com.company.ClinicaOdontologica.entity.Turno;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {

    Turno actualizar (Turno turno);

    TurnoDTO buscarPorId(Long id) throws Exception;

    List<TurnoDTO> buscarTodos();

    Turno guardar(Turno turno);

    void eliminar(Long id) throws ResourceNotFoundException;

    List<TurnoDTO> buscarPorIdOdontologo(Long idOdontologo);
}
