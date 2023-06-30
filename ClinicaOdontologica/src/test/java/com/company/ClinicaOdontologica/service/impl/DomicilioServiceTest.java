package com.company.ClinicaOdontologica.service.impl;

import com.company.ClinicaOdontologica.entity.Domicilio;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.company.ClinicaOdontologica.service.IDomicilioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DomicilioServiceTest {

    @Autowired
    IDomicilioService iDomicilioService;

    Domicilio domicilio = new Domicilio("Av.Italia", "2234", "Montevideo", "Montevideo");
    Domicilio domicilio2 = new Domicilio("Bv.Artigas", "1182", "Montevideo", "Montevideo");

    @Test
    void testCrearDomicilio() {
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Bv.Artigas");
        domicilio.setNumero("1232");
        domicilio.setLocalidad("Montevideo");
        domicilio.setProvincia("Montevideo");
        iDomicilioService.guardar(domicilio);

        assertNotNull(domicilio);
    }

    @Test
    public void testBuscarDomicilioPorId()  {

        iDomicilioService.guardar(domicilio);

        Long id = domicilio.getId();

        assertNotNull(iDomicilioService.buscarPorId(id));
    }

    @Test
    public void testBuscarTodosDomicilios(){
        iDomicilioService.guardar(domicilio);
        iDomicilioService.guardar(domicilio2);

        assertNotNull(iDomicilioService.buscarTodos());
    }

    @Test
    public void testEliminarDomicilio() throws ResourceNotFoundException {
        iDomicilioService.guardar(domicilio2);
        iDomicilioService.eliminar(domicilio2.getId());

        domicilio2 = null;

        assertNull(domicilio2);
    }

    @Test
    public void testActualizarDomicilio(){
        iDomicilioService.guardar(domicilio);
        domicilio.setCalle("1221");
        iDomicilioService.actualizar(domicilio);

        assertEquals(domicilio.getCalle(),"1221");
    }


}