package com.company.ClinicaOdontologica.service.impl;

import com.company.ClinicaOdontologica.dto.OdontologoDTO;
import com.company.ClinicaOdontologica.entity.Odontologo;
import com.company.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.company.ClinicaOdontologica.repository.IOdontologoRepository;
import com.company.ClinicaOdontologica.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger logger = Logger.getLogger(OdontologoService.class);

    // Repositorio de Odontólogos utilizado para acceder a la base de datos.
    private final IOdontologoRepository iOdontologoRepository;

    // Para la conversión de objetos:
    private final ObjectMapper objectMapper;

    // Constructor de OdontologoService que permite la inyección de dependencias.
    public OdontologoService(IOdontologoRepository iOdontologoRepository, ObjectMapper objectMapper) {
        this.iOdontologoRepository = iOdontologoRepository;
        this.objectMapper = objectMapper;
    }

    // Actualiza un Odontólogo en la base de datos.
    @Override
    public Odontologo actualizar(Odontologo odontologo) {
         logger.info("Odontologo - actualizar: Se va actualizar el odontologo");
         return guardar(odontologo);   // El método guardar() utiliza .save; este lo que hace es crear si el ID = 0 pero si ID!=0 actualiza los cambios.
    }

    // Busca un Odontólogo por su Id, si lo encuentra retorna el OdontologoDTO, sino muestra la exception.
    @Override
    public OdontologoDTO buscarPorId(Long id) throws Exception {
        Optional<Odontologo> found = iOdontologoRepository.findById(id); // Utilizo el objeto Optional que permite que "found" devuelva nulo u Odontologo
        if(found.isPresent()) {  // Evaluamos si found tiene contenido
            logger.info("Odontologo - buscarPorId: Se encontro el odontologo y se convertira a DTO para ser devuelto");
            return objectMapper.convertValue(found, OdontologoDTO.class); // Convertimos a found que es de tipo Odontologo a OdontologoDTO.
        } else {
            logger.warn("Odontologo - buscarPorId: No se encontro ningun odontologo con ese ID");
            throw new Exception("El odontologo no existe");
        }
    }

    // Busco todos los Odontólogos en la base de datos y retorno una lista de tipo OdontologoDTO con los Odontólogos encontrados.
    @Override
    public List<OdontologoDTO> buscarTodos() {
       List<OdontologoDTO> odontologoDTOS = new ArrayList<>();  // Creamos un ArrayList de tipo OdontologoDTO
       for (Odontologo o : iOdontologoRepository.findAll()){    // Iteramos el array
           logger.info("Odontologo - buscarTodos: Se esta iterando el array de odontologos");
           odontologoDTOS.add(objectMapper.convertValue(o,OdontologoDTO.class));  // En cada iteración convertimos el objeto de tipo Odontologo a OdontologoDTO y lo agregamos al ArrayList
       }
        return odontologoDTOS;
    }

    // Guardo un Odontólogo en la base de datos utilizando el método save de JpaRepository.
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Odontologo - guardar: Se va agregar el odontologo");
        return iOdontologoRepository.save(odontologo);
    }

    // Busco por Id un Odontólogo en la base de datos y lo elimino utilizando el método deleteById de JpaRepository, si da error lanzo una exception.
    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> found = iOdontologoRepository.findById(id);
        if(found.isPresent()) {
            iOdontologoRepository.deleteById(id);
            logger.warn("Odontologo - eliminar: Se ha eliminado el odontologo");
        } else {
            logger.error("No se ha encontrado el odontologo con id " + id);
            throw new ResourceNotFoundException("No se ha encontrado el odontologo");
        }
    }

}
