package com.ccsw.tutorial.cliente;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorial.cliente.model.Cliente;

/**
 * @author ccsw
 *
 */
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
