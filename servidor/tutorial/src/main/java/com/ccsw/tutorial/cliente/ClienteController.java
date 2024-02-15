package com.ccsw.tutorial.cliente;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.cliente.model.Cliente;
import com.ccsw.tutorial.cliente.model.ClienteDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author ccsw
 * 
 */
@Tag(name = "Cliente", description = "API of Cliente")
@RequestMapping(value = "/cliente")
@RestController
@CrossOrigin(origins = "*")

public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar todos los clientes
     *
     * @return {@link List} de {@link ClienteDto}
     */
    @Operation(summary = "Find", description = "Method that return a list of Clientes")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ClienteDto> findAll() {

        List<Cliente> clientes = this.clienteService.findAll();

        return clientes.stream().map(e -> mapper.map(e, ClienteDto.class)).collect(Collectors.toList());
    }

    /**
     * Método para crear o actualizar un cliente
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Cliente")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody ClienteDto dto) {

        this.clienteService.save(id, dto);
    }

    /**
     * Método para borrar un cliente
     *
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a Cliente")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.clienteService.delete(id);
    }
}
