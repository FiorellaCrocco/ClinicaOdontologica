package com.company.ClinicaOdontologica.service.impl;

import com.company.ClinicaOdontologica.dto.OdontologoDTO;
import com.company.ClinicaOdontologica.entity.Odontologo;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.company.ClinicaOdontologica.repository.IOdontologoRepository;
import com.company.ClinicaOdontologica.service.IOdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    IOdontologoService iOdontologoService;

    Odontologo odontologo1 = new Odontologo("Pedro", "Perez", "AFA");
    Odontologo odontologo2 = new Odontologo("Carmen", "Zain", "ABF");

    @Test
    public void testCrearOdontologo(){
        Odontologo odontologoFernando = new Odontologo();
        odontologoFernando.setNombre("Fernando");
        odontologoFernando.setApellido("Fernandez");
        odontologoFernando.setMatricula("AFC");

        iOdontologoService.guardar(odontologoFernando);

        assertNotNull(odontologoFernando);
    }

    @Test
    public void testBuscarOdontologoPorId() throws Exception {

        iOdontologoService.guardar(odontologo1);

        Long id = odontologo1.getId();

       assertNotNull(iOdontologoService.buscarPorId(id));
    }

    @Test
    public void testBuscarTodosOdontologos(){
        iOdontologoService.guardar(odontologo1);
        iOdontologoService.guardar(odontologo2);

        assertNotNull(iOdontologoService.buscarTodos());
    }

    @Test
    public void testEliminarOdontologo() throws ResourceNotFoundException {
        iOdontologoService.guardar(odontologo2);
        iOdontologoService.eliminar(odontologo2.getId());

        odontologo2 = null;

        assertNull(odontologo2);
    }

    @Test
    public void testActualizarOdontologo(){
        iOdontologoService.guardar(odontologo2);
        odontologo2.setApellido("Lorenzo");
        iOdontologoService.actualizar(odontologo2);

        assertEquals(odontologo2.getApellido(),"Lorenzo");
    }
}