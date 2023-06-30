package com.company.ClinicaOdontologica.exceptions;

public class ResourceNotFoundException extends Exception{

    // Creo un constructor que acepta un mensaje como parámetro.
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
