package com.company.ClinicaOdontologica.service.impl;


import com.company.ClinicaOdontologica.dto.TurnoDTO;
import com.company.ClinicaOdontologica.entity.Turno;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.company.ClinicaOdontologica.repository.ITurnoRepository;
import com.company.ClinicaOdontologica.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private static final Logger logger = Logger.getLogger(TurnoService.class);

    // Repositorio de Turnos utilizado para acceder a la base de datos.
    private ITurnoRepository iTurnoRepository;

    // Para la conversión de objetos.
    private ObjectMapper objectMapper;

    // Constructor de TurnoService que permite la inyección de dependencias.
    public TurnoService(ITurnoRepository iTurnoRepository, ObjectMapper objectMapper) {
        this.iTurnoRepository = iTurnoRepository;
        this.objectMapper = objectMapper;
    }

    // Actualizo un Turno en la base de datos.
    @Override
    public Turno actualizar(Turno turno) {
        logger.info("Turno - actualizar: Se va actualizar el turno");
        return guardar(turno); // El método guardar() utiliza .save; este lo que hace es crear si el ID = 0 pero si ID!=0 actualiza los cambios.
    }

    // Busco un Turno por su Id, si lo encuentro retorno el TurnoDTO, sino, muestro la exception.
    @Override
    public TurnoDTO buscarPorId(Long id) throws Exception {
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS); // Se utiliza para solucionar el error "SerializationFeature.FAIL_ON_EMPTY_BEANS)"
        Optional<Turno> found = iTurnoRepository.findById(id);  // Utilizo el objeto Optional que permite que "found" devuelva nulo o Turno
        if(found.isPresent()) {  // Evaluamos si found tiene contenido
            logger.info("Turno - buscarPorId: Se encontro el turno y se convertira a DTO para ser devuelto");
            return objectMapper.convertValue(found, TurnoDTO.class);  // Convertimos a found que es de tipo Turno a TurnoDTO.
        } else {
            logger.warn("Turno - buscarPorId: No se encontro ningun turno con ese ID");
            throw new Exception("El turno no existe");
        }
    }

    // Busco todos los Turnos en la base de datos y retorno una lista de tipo TurnoDTO con los Turnos encontrados.
    @Override
    public List<TurnoDTO> buscarTodos() {
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS); // Se utiliza para solucionar el error "SerializationFeature.FAIL_ON_EMPTY_BEANS)"
        List<TurnoDTO> turnoDTOS = new ArrayList<>();  // Creamos un ArrayList de tipo TurnoDTO
        for (Turno t : iTurnoRepository.findAll()){    // Iteramos el array
            logger.info("Turno - buscarTodos: Se esta iterando el array de turnos");
            turnoDTOS.add(objectMapper.convertValue(t,TurnoDTO.class));  // En cada iteración convertimos el objeto de tipo Turno a TurnoDTO y lo agregamos al ArrayList
        }
        return turnoDTOS;
    }

    // Guardo un Turno en la base de datos utilizando el método save de JpaRepository.
    public Turno guardar(Turno turno){
        logger.info("Turno - guardar: Se va agregar el turno");
        return iTurnoRepository.save(turno);
    }

    // Busco por Id un Turno en la base de datos y lo elimino utilizando el método deleteById de JpaRepository, si da error lanzo una exception.
    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Turno> optionalTurno = iTurnoRepository.findById(id);
        if(optionalTurno.isPresent()){
            iTurnoRepository.deleteById(id);
            logger.warn("Turno - eliminar: Se ha eliminado el turno");
        } else {
            logger.error("No se ha encontrado el turno con id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el turno");
        }
    }

}
