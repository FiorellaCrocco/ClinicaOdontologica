package com.company.ClinicaOdontologica.service.impl;

import com.company.ClinicaOdontologica.dto.PacienteDTO;
import com.company.ClinicaOdontologica.entity.Domicilio;
import com.company.ClinicaOdontologica.entity.Paciente;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.company.ClinicaOdontologica.service.IDomicilioService;
import com.company.ClinicaOdontologica.service.IPacienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceTest {

    @Autowired
    IPacienteService iPacienteService;

    @Autowired
    IDomicilioService iDomicilioService;

    Domicilio domicilio1 = new Domicilio("Av.Italia", "2234", "Montevideo", "Montevideo");

    Paciente paciente = new Paciente("Katherine", "Soto", "584265", "ksotomail.com", domicilio1, LocalDate.now());
    Paciente paciente1 = new Paciente("Kate", "Soto", "584265", "ksootomail.com", domicilio1, LocalDate.now());

    @Test
    @Transactional
    public void testCrearPaciente() throws Exception {

        iDomicilioService.guardar(domicilio1);

        Paciente pacienteFiorella = new Paciente();
        pacienteFiorella.setNombre("Fiorella");
        pacienteFiorella.setApellido("Crocco");
        pacienteFiorella.setDNI("123456");
        pacienteFiorella.setEmail("fc@mail.com");
        pacienteFiorella.setFechaAlta(LocalDate.now());
        iDomicilioService.guardar(domicilio1);
        pacienteFiorella.setDomicilio(domicilio1);
        iPacienteService.guardar(pacienteFiorella);
        assertNotNull(pacienteFiorella);
    }

    @Test
    @Transactional
    public void testBuscarPacientePorId() throws Exception {

        iDomicilioService.guardar(domicilio1);

        iPacienteService.guardar(paciente);

        Long id = paciente.getId();

        PacienteDTO result = iPacienteService.buscarPorId(id);

        assertNotNull(result);
        assertNotNull(result.getDomicilio());
    }

    @Test
    @Transactional
    public void testBuscarTodosPacientes(){
        iDomicilioService.guardar(domicilio1);
        iPacienteService.guardar(paciente);
        iPacienteService.guardar(paciente1);

        assertNotNull(iPacienteService.buscarTodos());
    }

    @Test
    public void testEliminarPaciente() throws ResourceNotFoundException {
        iDomicilioService.guardar(domicilio1);
        iPacienteService.guardar(paciente1);
        iPacienteService.eliminar(paciente1.getId());

        paciente1 = null;

        assertNull(paciente1);
    }

    @Test
    public void testActualizarPaciente(){
        iDomicilioService.guardar(domicilio1);
        iPacienteService.guardar(paciente1);

        paciente1.setApellido("Bicca");
        iPacienteService.actualizar(paciente1);

        assertEquals(paciente1.getApellido(),"Bicca");
    }

}