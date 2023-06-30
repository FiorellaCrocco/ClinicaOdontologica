package com.company.ClinicaOdontologica.service;



import com.company.ClinicaOdontologica.entity.Domicilio;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IDomicilioService {

    void actualizar (Domicilio domicilio);

    Domicilio buscarPorId (Long id) ;

    List<Domicilio> buscarTodos();

    Long guardar(Domicilio domicilio);

    void eliminar(Long id) throws ResourceNotFoundException;
}
