package com.company.ClinicaOdontologica.controller;

import com.company.ClinicaOdontologica.dto.TurnoDTO;
import com.company.ClinicaOdontologica.entity.Turno;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.company.ClinicaOdontologica.service.impl.OdontologoService;
import com.company.ClinicaOdontologica.service.impl.PacienteService;
import com.company.ClinicaOdontologica.service.impl.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {

    TurnoService turnoService;

    PacienteService pacienteService;

    OdontologoService odontologoService;

    // Constructor de TurnoController que permite la inyección de dependencias.
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    // En la url "/turno/listar" retorno una lista de TurnoDTO
    @GetMapping("/listar")
    public List<TurnoDTO> buscarTurnos(){
        List<TurnoDTO> listarTurnos = turnoService.buscarTodos();
        return listarTurnos;
    }

    // En la url "/turno/{id}" retorno el turnoDTO deseado (segun el ID) y si no lo encuentra se dispara una Exception
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarUnTurno(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(turnoService.buscarPorId(id));
    }

    // En la url "/turno/guardar" hacemos un POST para guardar el Turno
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarTurno(@RequestBody Turno turno) {
        turnoService.guardar(turno);
        return ResponseEntity.ok("Turno agregado");
    }

    // En la url "/turno/modificar" actualizamos un turno ya existente
    @PutMapping("/modificar")
    public ResponseEntity<Turno> actualizarUnTurnos(@RequestBody Turno turno){
        return ResponseEntity.ok(turnoService.actualizar(turno));
    }

    // En la url "/turno/eliminar/{id}" utilizamos el metodo DELETE para eliminar un turno segun su ID, si no existe lanzo ResourceNotFoundException.
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Eliminado");
    }

}
