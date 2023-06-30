package com.company.ClinicaOdontologica.controller;

import com.company.ClinicaOdontologica.dto.OdontologoDTO;
import com.company.ClinicaOdontologica.entity.Odontologo;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.company.ClinicaOdontologica.service.impl.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    OdontologoService odontologoService;

    // Constructor de OdontologoController que permite la inyección de dependencias.
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    // En la url "/odontologo/listar" retorno una lista de OdontologoDTO
    @GetMapping("/listar")
    public List<OdontologoDTO> obtenerOdontologos(){
        List<OdontologoDTO> listaOdontologos = odontologoService.buscarTodos();
        return listaOdontologos;
    }

    // En la url "/odontologo/{id}" retorno el odontologoDTO deseado (segun el ID) y si no lo encuentra se dispara una Exception
    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> obtenerUnOdontologo(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    // En la url "/odontologo/guardar" hacemos un POST para guardar el Odontologo
    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    // En la url "/odontologo/modificar" actualizamos un odontólogo ya existente
    @PutMapping("/modificar")
    public ResponseEntity<Odontologo> actualizarUnOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.actualizar(odontologo));
    }

    // En la url "/odontologo/eliminar/{id}" utilizamos el metodo DELETE para eliminar un odontólogo segun su ID, si no se encuentra lanzo la exception ResourceNotFoundException
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUnOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Eliminado");
    }

}
