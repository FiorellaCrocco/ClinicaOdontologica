package com.company.ClinicaOdontologica.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEnconder {

    //  Crea y devuelve un bean de tipo BCryptPasswordEncoder. Se utilizará para codificar las contraseñas de los usuarios.
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
