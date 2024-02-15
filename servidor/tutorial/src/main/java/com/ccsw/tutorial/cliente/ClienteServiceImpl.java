package com.ccsw.tutorial.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.cliente.model.Cliente;
import com.ccsw.tutorial.cliente.model.ClienteDto;

/**
 * @author ccsw
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Cliente get(Long id) {

        return this.clienteRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Cliente> findAll() {

        return (List<Cliente>) this.clienteRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, ClienteDto dto) {

        Cliente cliente;

        if (id == null) {
            cliente = new Cliente();
        } else {
            cliente = this.clienteRepository.findById(id).orElse(null);
        }

        cliente.setName(dto.getName());

        this.clienteRepository.save(cliente);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.clienteRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        this.clienteRepository.deleteById(id);
    }

}
