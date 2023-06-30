package com.company.ClinicaOdontologica.controller;

import com.company.ClinicaOdontologica.dto.PacienteDTO;
import com.company.ClinicaOdontologica.entity.Paciente;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.company.ClinicaOdontologica.service.impl.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    PacienteService pacienteService;

    // Constructor de PacienteController que permite la inyección de dependencias.
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // En la url "/paciente/listar" retorno una lista de PacienteDTO
    @GetMapping("/listar")
    public List<PacienteDTO> obtenerPacientes(){
        List<PacienteDTO> listaPacientes = pacienteService.buscarTodos();
        return listaPacientes;
    }

    // En la url "/paciente/{id}" retorno el pacienteDTO deseado (segun el ID) y si no lo encuentra se dispara una Exception
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> obtenerUnPaciente(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    // En la url "/paciente/guardar" hacemos un POST para guardar el Paciente
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPaciente(@RequestBody Paciente paciente){
        pacienteService.guardar(paciente);
        return ResponseEntity.ok("Paciente guardado");
    }

    // En la url "/paciente/modificar" actualizamos un paciente ya existente
    @PutMapping("/modificar")
    public ResponseEntity<?> actualizarUnPaciente(@RequestBody Paciente paciente){
        pacienteService.actualizar(paciente);
        return ResponseEntity.ok("Paciente actualizado");
    }

    // En la url "/paciente/eliminar/{id}" utilizamos el metodo DELETE para eliminar un paciente segun su ID, si no existe lanzo ResourceNotFoundException
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUnPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        ResponseEntity<String>response = null;
        pacienteService.eliminar(id);
        response =ResponseEntity.status(HttpStatus.OK).body("Paciente eliminado.");
        return response;
    }

}
