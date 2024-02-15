package com.ccsw.tutorial.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorial.cliente.model.Cliente;
import com.ccsw.tutorial.cliente.model.ClienteDto;

@ExtendWith(MockitoExtension.class)
public class ClienteTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    public void findAllShouldReturnAllClientes() {

        List<Cliente> list = new ArrayList<>();
        list.add(mock(Cliente.class));

        when(clienteRepository.findAll()).thenReturn(list);

        List<Cliente> clientes = clienteService.findAll();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
    }

    public static final String CLIENT_NAME = "CLIENT1";

    @Test
    public void saveNotExistsClienteIdShouldInsert() {

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setName(CLIENT_NAME);

        ArgumentCaptor<Cliente> cliente = ArgumentCaptor.forClass(Cliente.class);

        clienteService.save(null, clienteDto);

        verify(clienteRepository).save(cliente.capture());

        assertEquals(CLIENT_NAME, cliente.getValue().getName());
    }

    public static final Long EXISTS_CLIENT_ID = 1L;

    @Test
    public void saveExistsClientIdShouldUpdate() {

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setName(CLIENT_NAME);

        Cliente cliente = mock(Cliente.class);
        when(clienteRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(cliente));

        clienteService.save(EXISTS_CLIENT_ID, clienteDto);

        verify(clienteRepository).save(cliente);
    }

    @Test
    public void deleteExistsClienteIdShouldDelete() throws Exception {

        Cliente cliente = mock(Cliente.class);
        when(clienteRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(cliente));

        clienteService.delete(EXISTS_CLIENT_ID);

        verify(clienteRepository).deleteById(EXISTS_CLIENT_ID);
    }

}
