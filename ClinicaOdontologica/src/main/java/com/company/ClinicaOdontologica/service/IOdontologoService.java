package com.company.ClinicaOdontologica.service;

import com.company.ClinicaOdontologica.dto.OdontologoDTO;
import com.company.ClinicaOdontologica.entity.Odontologo;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {

    Odontologo actualizar (Odontologo odontologo);

    OdontologoDTO buscarPorId(Long id) throws Exception;

    List<OdontologoDTO> buscarTodos();

    Odontologo guardar(Odontologo odontologo);

    void eliminar(Long id) throws ResourceNotFoundException;

}
