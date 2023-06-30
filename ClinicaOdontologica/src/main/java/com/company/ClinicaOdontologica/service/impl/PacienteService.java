package com.company.ClinicaOdontologica.service.impl;


import com.company.ClinicaOdontologica.dto.PacienteDTO;
import com.company.ClinicaOdontologica.entity.Paciente;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.company.ClinicaOdontologica.repository.IPacienteRepository;
import com.company.ClinicaOdontologica.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private static final Logger logger = Logger.getLogger(PacienteService.class);

    // Repositorio de Pacientes utilizado para acceder a la base de datos.
    private IPacienteRepository iPacienteRepository;

    // Para la conversión de objetos.
    private ObjectMapper objectMapper;

    // Constructor de PacienteService que permite la inyección de dependencias.
    public PacienteService(IPacienteRepository iPacienteRepository, ObjectMapper objectMapper) {
        this.iPacienteRepository = iPacienteRepository;
        this.objectMapper = objectMapper;
    }

    // Actualizo un Paciente en la base de datos.
    @Override
    public void actualizar(Paciente paciente) {
        logger.info("Paciente - actualizar: Se va actualizar el paciente");
        guardar(paciente); // El método guardar() utiliza .save; este lo que hace es crear si el ID = 0 pero si ID!=0 actualiza los cambios.
    }

    // Busco un Paciente por su Id, si lo encuentro retorno el PacienteDTO, sino, muestro la exception.
    @Override
    public PacienteDTO buscarPorId(Long id) throws Exception {
        objectMapper.registerModule(new JavaTimeModule()); // Se utiliza para solucionar el error "not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310""
        Optional<Paciente> found = iPacienteRepository.findById(id);  // Utilizo el objeto Optional que permite que "found" devuelva nulo o Paciente
        if(found.isPresent()) {  // Evaluamos si found tiene contenido
            logger.info("Paciente - buscarPorId: Se encontro el paciente y se convertira a DTO para ser devuelto");
            return objectMapper.convertValue(found, PacienteDTO.class);  // Convertimos a found que es de tipo Paciente a PacienteDTO.
        } else {
            logger.warn("Paciente - buscarPorId: No se encontro ningun paciente con ese ID");
            throw new Exception("El paciente no existe");
        }
    }

    // Busco todos los Pacientes en la base de datos y retorno una lista de tipo PacienteDTO con los Pacientes encontrados.
    @Override
    public List<PacienteDTO> buscarTodos() {
        objectMapper.registerModule(new JavaTimeModule()); // Se utiliza para solucionar el error "not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" "
        List<PacienteDTO> pacienteDTOS = new ArrayList<>();  // Creamos un ArrayList de tipo PacienteDTO
        for (Paciente p : iPacienteRepository.findAll()){    // Iteramos el array
            logger.info("Paciente - buscarTodos: Se esta iterando el array de pacientes");
            pacienteDTOS.add(objectMapper.convertValue(p,PacienteDTO.class));  // En cada iteración convertimos el objeto de tipo Paciente a PacienteDTO y lo agregamos al ArrayList
        }
        return pacienteDTOS;
    }

    // Guardo un Paciente en la base de datos utilizando el método save de JpaRepository.
    public void guardar(Paciente paciente){
        logger.info("Paciente - guardar: Se va guardar el paciente");
        paciente.setFechaAlta(LocalDate.now());
        iPacienteRepository.save(paciente);
    }

    // Busco por Id un Paciente en la base de datos y lo elimino utilizando el método deleteById de JpaRepository, si da error lanzo una exception.
    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Paciente> found = iPacienteRepository.findById(id);
        if(found.isPresent()) {
            iPacienteRepository.deleteById(id);
            logger.warn("Paciente - eliminar: Se ha eliminado el paciente");
        } else {
            logger.error("No se ha encontrado el paciente con id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el paciente");
        }
    }

}
