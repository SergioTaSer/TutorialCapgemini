package com.ccsw.tutorial.cliente;

import java.util.List;

import com.ccsw.tutorial.cliente.model.Cliente;
import com.ccsw.tutorial.cliente.model.ClienteDto;

/**
 * @author ccsw
 * 
 */
public interface ClienteService {

    /**
     * Recupera un {@link Cliente} a través de su ID
     *
     * @param id PK de la entidad
     * @return {@link Cliente}
     */
    Cliente get(Long id);

    /**
     * Método para recuperar todos los clientes
     *
     * @return {@link List} de {@link Cliente}
     */
    List<Cliente> findAll();

    /**
     * Método para crear o actualizar una cliente
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, ClienteDto dto);

    /**
     * Método para borrar un cliente
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

}
