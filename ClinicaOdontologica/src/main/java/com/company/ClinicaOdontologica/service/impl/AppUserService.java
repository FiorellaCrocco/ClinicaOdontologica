package com.company.ClinicaOdontologica.service.impl;

import com.company.ClinicaOdontologica.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    // Repositorio de User utilizado para acceder a la base de datos.
    private final IUserRepository iUserRepository;

    // Constructor de AppUserService que permite la inyección de dependencias.
    @Autowired
    public AppUserService(com.company.ClinicaOdontologica.repository.IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    // Se lanza la exception UsernameNotFoundException si el usuario no se encuentra en la base de datos
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return iUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No ha sido encontrado ese usuario en la base de datos"));
    }

}
