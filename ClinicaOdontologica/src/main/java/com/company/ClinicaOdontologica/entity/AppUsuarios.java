package com.company.ClinicaOdontologica.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "USUARIOS") // Establecemos el nombre de la tabla en la BD
public class AppUsuarios implements UserDetails {

    // Establecemos el Id como Primary Key de tipo indentity
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)  // Define un generador de secuencia con nombre "user_sequence" que se utilizará para generar valores de ID
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")  // Especifica la estrategia de generación de valores de ID utilizando una secuencia y el generador "user_sequence"
    private Long id;

    private String nombre;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)  // Anotación utilizada para especificar cómo se debe mapear la propiedad enum a la base de datos
    private AppUsuariosRoles appUsuariosRoles;

    // Constructor sin parametros
    public AppUsuarios() {
    }

    public AppUsuarios(String nombre, String username, String email, String password, AppUsuariosRoles appUsuariosRoles) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
        this.appUsuariosRoles = appUsuariosRoles;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppUsuariosRoles getAppUsuariosRoles() {
        return appUsuariosRoles;
    }

    public void setAppUsuariosRoles(AppUsuariosRoles appUsuariosRoles) {
        this.appUsuariosRoles = appUsuariosRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + appUsuariosRoles.name());  // Crear una instancia de SimpleGrantedAuthority con el nombre del rol
        return Collections.singletonList(grantedAuthority);    // Devolver la lista de autoridades (roles)
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
