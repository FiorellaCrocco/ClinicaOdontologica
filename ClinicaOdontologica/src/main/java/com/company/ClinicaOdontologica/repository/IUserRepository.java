package com.company.ClinicaOdontologica.repository;

import com.company.ClinicaOdontologica.entity.AppUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional  // Se utiliza para asegurar la consistencia de los datos en las operaciones de lectura y escritura.
public interface IUserRepository extends JpaRepository<AppUsuarios, Long> {
    Optional<AppUsuarios> findByEmail(String email);  // Busca un usuario por su dirección de correo electrónico.
}
