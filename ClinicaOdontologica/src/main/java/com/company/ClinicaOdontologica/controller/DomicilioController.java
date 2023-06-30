package com.company.ClinicaOdontologica.controller;

import com.company.ClinicaOdontologica.entity.Domicilio;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.company.ClinicaOdontologica.service.impl.DomicilioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilio")
public class DomicilioController {

    DomicilioService domicilioService;

    // Constructor de DomicilioController que permite la inyección de dependencias.
    public DomicilioController(DomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    // En la url "/domicilio/listar" retorno una lista de Domicilio
    @GetMapping("/listar")
    public List<Domicilio> buscarDomicilios(){
        List<Domicilio> listarDomicilios = domicilioService.buscarTodos();
        return listarDomicilios;
    }

    // En la url "/domicilio/{id}" retorno el domicilio deseado (segun el ID) y si no lo encuentra se dispara una Exception
    @GetMapping("/{id}")
    public ResponseEntity<Domicilio> buscarUnDomicilio(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(domicilioService.buscarPorId(id));
    }

    // En la url "/domicilio/guardar" hacemos un POST para guardar el Domicilio
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarDomicilio(@RequestBody Domicilio domicilio){
        domicilioService.guardar(domicilio);
        return ResponseEntity.status(HttpStatus.OK).body(domicilio.getId());
    }

    // En la url "/domicilio/modificar" actualizamos un domicilio ya existente
    @PutMapping("/modificar")
    public ResponseEntity<?> actualizarUnDomicilio(@RequestBody Domicilio domicilio){
        domicilioService.actualizar(domicilio);
        return ResponseEntity.ok().body("Se modifico el domicilio.");
    }

    // En la url "/domicilio/eliminar/{id}" utilizamos el metodo DELETE para eliminar un domicilio segun su ID, si no se encuentra lanzo la exception ResourceNotFoundException
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDomicilio(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<?>response = null;
        domicilioService.eliminar(id);
        response = ResponseEntity.status(HttpStatus.OK).body("Domicilio eliminado.");
        return response;
    }
}
