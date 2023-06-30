package com.company.ClinicaOdontologica.service.impl;

import com.company.ClinicaOdontologica.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final IUserRepository iUserRepository;

    @Autowired
    public AppUserService(com.company.ClinicaOdontologica.repository.IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // return iUserRepository.findByEmail(email).get();
        return iUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No ha sido encontrado ese usuario en la base de datos"));
    }
}
