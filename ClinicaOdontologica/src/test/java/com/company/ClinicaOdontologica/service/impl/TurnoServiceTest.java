package com.company.ClinicaOdontologica.service.impl;

import com.company.ClinicaOdontologica.dto.TurnoDTO;
import com.company.ClinicaOdontologica.entity.Domicilio;
import com.company.ClinicaOdontologica.entity.Odontologo;
import com.company.ClinicaOdontologica.entity.Paciente;
import com.company.ClinicaOdontologica.entity.Turno;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.company.ClinicaOdontologica.service.IDomicilioService;
import com.company.ClinicaOdontologica.service.IOdontologoService;
import com.company.ClinicaOdontologica.service.IPacienteService;
import com.company.ClinicaOdontologica.service.ITurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServiceTest {

    @Autowired
    ITurnoService iTurnoService;

    @Autowired
    IDomicilioService iDomicilioService;

    @Autowired
    IPacienteService iPacienteService;

    @Autowired
    IOdontologoService iOdontologoService;


    Domicilio domicilio1 = new Domicilio("Av.Italia", "2234", "Montevideo", "Montevideo");
    Paciente paciente1 = new Paciente("Daniel", "Lema","123456","dlema@mail.com", domicilio1, LocalDate.now());
    Odontologo odontologo1 = new Odontologo("Pedro", "Perez", "AFB");
    Turno turno1 = new Turno(paciente1, odontologo1, LocalDateTime.of(2023,06,15,10,40,00));

    @Test
    void testCrearTurno(){

        iDomicilioService.guardar(domicilio1);
        iPacienteService.guardar(paciente1);
        iOdontologoService.guardar(odontologo1);

        Turno turno = new Turno();
        turno.setPaciente(paciente1);
        turno.setOdontologo(odontologo1);
        turno.setFecha(LocalDateTime.of(2023,06,15,10,40,00));

        iTurnoService.guardar(turno);

        assertNotNull(turno);
    }

    @Test
    @Transactional
    void testBuscarTurnoPorId() throws Exception {

        iDomicilioService.guardar(domicilio1);
        iPacienteService.guardar(paciente1);
        iOdontologoService.guardar(odontologo1);
        iTurnoService.guardar(turno1);

        Long id = turno1.getId();

        TurnoDTO result = iTurnoService.buscarPorId(id);

        assertNotNull(result);
    }

    @Test
    @Transactional
    void testBuscarTurnos(){

        iDomicilioService.guardar(domicilio1);
        iPacienteService.guardar(paciente1);
        iOdontologoService.guardar(odontologo1);
        iTurnoService.guardar(turno1);

        assertNotNull(iTurnoService.buscarTodos());
    }

    @Test
    public void testEliminarTurno() throws ResourceNotFoundException {

        iDomicilioService.guardar(domicilio1);
        iPacienteService.guardar(paciente1);
        iOdontologoService.guardar(odontologo1);
        iTurnoService.guardar(turno1);

        iTurnoService.eliminar(turno1.getId());

        turno1 = null;

        assertNull(turno1);
    }

    @Test
    public void testActualizarTurno(){

        iDomicilioService.guardar(domicilio1);
        iPacienteService.guardar(paciente1);
        iOdontologoService.guardar(odontologo1);
        paciente1.setApellido("Bika");
        iPacienteService.actualizar(paciente1);

        assertEquals(paciente1.getApellido(),"Bika");
    }
}