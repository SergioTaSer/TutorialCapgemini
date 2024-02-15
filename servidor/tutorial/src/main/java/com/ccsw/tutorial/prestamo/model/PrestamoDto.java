package com.ccsw.tutorial.prestamo.model;

import java.util.Date;

import com.ccsw.tutorial.cliente.model.ClienteDto;
import com.ccsw.tutorial.game.model.GameDto;

/**
 * @prestamo ccsw
 *
 */
public class PrestamoDto {

    private Long id;

    private GameDto game;

    private ClienteDto cliente;

    private Date fechaPrestamo;

    private Date fechaDevolucion;

    /**
     * @return id
     */
    public Long getId() {

        return id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * @return game
     */
    public GameDto getGame() {

        return game;
    }

    /**
     * @param game new value of {@link #getGame}.
     */
    public void setGame(GameDto game) {

        this.game = game;
    }

    /**
     * @return cliente
     */
    public ClienteDto getCliente() {

        return cliente;
    }

    /**
     * @param cliente new value of {@link #getCliente}.
     */
    public void setCliente(ClienteDto cliente) {

        this.cliente = cliente;
    }

    /**
     * @return fechaPrestamo
     */
    public Date getFechaPrestamo() {

        return fechaPrestamo;
    }

    /**
     * @param fechaPrestamo new value of {@link #getFechPrestamo}.
     */
    public void setFechaPrestamo(Date fechaPrestamo) {

        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * @return fechaDevolucion
     */
    public Date getFechaDevolucion() {

        return fechaDevolucion;
    }

    /**
     * @param fechaDevolucion new value of {@link #getFechaDevolucion}.
     */
    public void setFechaDevolucion(Date fechaDevolucion) {

        this.fechaDevolucion = fechaDevolucion;
    }

}
