package com.company.ClinicaOdontologica.repository;


import com.company.ClinicaOdontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByOdontologoId(Long idOdontologo);  // Busca y devuelve una lista de turnos basada en el ID del odontólogo
}
