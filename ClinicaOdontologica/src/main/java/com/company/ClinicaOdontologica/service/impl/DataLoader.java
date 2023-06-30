package com.company.ClinicaOdontologica.service.impl;

import com.company.ClinicaOdontologica.entity.AppUsuarios;
import com.company.ClinicaOdontologica.entity.AppUsuariosRoles;
import com.company.ClinicaOdontologica.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    // Repositorio de User utilizado para acceder a la base de datos.
    private IUserRepository iUserRepository;

    // Constructor de DataLoader que permite la inyección de dependencias.
    @Autowired
    public DataLoader(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Codificar las contraseñas de los usuarios
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordAdmin = bCryptPasswordEncoder.encode("admin");
        String passwordUser = bCryptPasswordEncoder.encode("user");

        // Guardar usuarios en la base de datos
        iUserRepository.save(new AppUsuarios("Administrador", "admin", "admin@clinica.com", passwordAdmin, AppUsuariosRoles.ADMIN));
        iUserRepository.save(new AppUsuarios("Usuario", "user", "user@clinica.com", passwordUser, AppUsuariosRoles.USER));

    }
}
