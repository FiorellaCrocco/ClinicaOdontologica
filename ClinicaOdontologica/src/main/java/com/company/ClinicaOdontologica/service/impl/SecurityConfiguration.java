package com.company.ClinicaOdontologica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private AppUserService appUserService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Constructor de SecurityConfiguration que permite la inyección de dependencias.
    @Autowired
    public SecurityConfiguration(AppUserService usuarioService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserService = usuarioService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // Configura la seguridad HTTP para la aplicación.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/turno/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/paciente/**", "/odontologo/**").hasRole("ADMIN")

                .anyRequest().authenticated().and().formLogin().and().logout();

        http
                .csrf().disable()
                .headers().frameOptions().disable();

    }

    // Configura el administrador de autenticación para la aplicación.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    // Configura el proveedor de autenticación con el codificador de contraseñas y el servicio de detalles de usuario.
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }


}