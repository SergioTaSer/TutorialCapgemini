package com.ccsw.tutorial.prestamo.model;

public class FiltersDto {

    @Override
    public String toString() {
        return "FiltersDto [game=" + game + ", cliente=" + cliente + ", fecha=" + fecha + "]";
    }

    private Long game;
    private Long cliente;
    private String fecha;

    public Long getIdGame() {
        return game;
    }

    public void setGameTitle(Long gameTitle) {

        this.game = gameTitle;
    }

    public Long getIdCliente() {
        return cliente;
    }

    public void setIdCliente(Long idCliente) {
        this.cliente = idCliente;
    }

    public String getDate() {
        return fecha;
    }

    public void setDate(String fecha) {
        this.fecha = fecha;
    }

    public FiltersDto(Long game, Long cliente, String fecha) {
        super();
        this.game = game;
        this.cliente = cliente;
        this.fecha = fecha;
    }

    public FiltersDto() {
    }

}
