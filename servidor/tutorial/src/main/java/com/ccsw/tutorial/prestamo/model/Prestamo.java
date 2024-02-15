package com.ccsw.tutorial.prestamo.model;

import java.util.Date;

import com.ccsw.tutorial.cliente.model.Cliente;
import com.ccsw.tutorial.game.model.Game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @prestamo ccsw
 *
 */
@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_prestamo", nullable = false)
    private Date fechaPrestamo;

    @Column(name = "fecha_devolucion", nullable = false)
    private Date fechaDevolucion;

    /**
     * @return id
     */
    public Long getId() {

        return this.id;
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
    public Game getGame() {

        return this.game;
    }

    /**
     * @param game new value of {@link #getGame}.
     */
    public void setGame(Game game) {

        this.game = game;
    }

    /**
     * @return cliente
     */
    public Cliente getCliente() {

        return this.cliente;
    }

    /**
     * @param cliente new value of {@link #getCliente}.
     */
    public void setCliente(Cliente cliente) {

        this.cliente = cliente;
    }

    /**
     * @return fechaPrestamo
     */
    public Date getFechaPrestamo() {

        return this.fechaPrestamo;
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

        return this.fechaDevolucion;
    }

    /**
     * @param fechaDevolucion new value of {@link #getFechaDevolucion}.
     */
    public void setFechaDevolucion(Date fechaDevolucion) {

        this.fechaDevolucion = fechaDevolucion;
    }

}
