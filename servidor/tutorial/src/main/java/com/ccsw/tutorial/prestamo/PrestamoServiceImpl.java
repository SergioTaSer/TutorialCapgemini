package com.ccsw.tutorial.prestamo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.cliente.ClienteService;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.prestamo.model.FiltersDto;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

import jakarta.transaction.Transactional;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    PrestamoRepository prestamoRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    GameService gameService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Prestamo> findAll() {
        return (List<Prestamo>) this.prestamoRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Prestamo> findPage(PrestamoSearchDto dto) {

        FiltersDto filtros = dto.getFilterParams();

        if (filtros == null) {
            return this.prestamoRepository.findAll(null, dto.getPageable().getPageable());
        }

        PrestamoSpecification gameSpec = new PrestamoSpecification(
                new SearchCriteria("game.id", ":", filtros.getIdGame()));
        PrestamoSpecification clienteSpec = new PrestamoSpecification(
                new SearchCriteria("cliente.id", ":", filtros.getIdCliente()));
        PrestamoSpecification datePrestamoSpec = new PrestamoSpecification(
                new SearchCriteria("fechaPrestamo", ">=", filtros.getDate()));
        PrestamoSpecification dateDevolucionSpec = new PrestamoSpecification(
                new SearchCriteria("fechaDevolucion", "<=", filtros.getDate()));

        Specification<Prestamo> spec = Specification.where(gameSpec).and(clienteSpec).and(datePrestamoSpec)
                .and(dateDevolucionSpec);

        return this.prestamoRepository.findAll(spec, dto.getPageable().getPageable());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(PrestamoDto data) throws Exception {

        Prestamo prestamo;

        prestamo = new Prestamo();

        BeanUtils.copyProperties(data, prestamo, "id", "game", "cliente");

        prestamo.setCliente(clienteService.get(data.getCliente().getId()));
        prestamo.setGame(gameService.get(data.getGame().getId()));

        // Comprobación: fecha Inicio menor que fecha final
        if (prestamo.getFechaDevolucion().before(prestamo.getFechaPrestamo())) {
            throw new Exception("La fecha de devolucion no puede ser anterior a la fecha de inicio del prestamo");
        }

        // Comprobacion: diferencia 14 dias
        long diferencia = prestamo.getFechaDevolucion().getTime() - prestamo.getFechaPrestamo().getTime();
        long diferenciaDias = TimeUnit.DAYS.convert(diferencia, TimeUnit.MILLISECONDS);

        if (diferenciaDias > 14) {
            throw new Exception("La diferencia entre la fecha de préstamo y devolución no puede ser mayor a 14 días.");
        }

        // Verificación de préstamo duplicado para un juego en la misma fecha
        boolean existePrestamo = prestamoRepository.juegoPrestado(prestamo.getGame(), prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucion());

        if (existePrestamo) {
            throw new Exception("El juego ya está prestado en el rango de fechas seleccionado.");
        }

        // Verificar que un cliente no tiene prestados más de dos juegos un mismo día

        int cantidadPrestamos = prestamoRepository.contarPrestamosPorClienteYFecha(prestamo.getCliente(),
                prestamo.getFechaPrestamo(), prestamo.getFechaDevolucion());
        if (cantidadPrestamos >= 2) {
            throw new Exception("El cliente ya tiene dos juegos prestados para la fecha seleccionada.");
        }

        this.prestamoRepository.save(prestamo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.prestamoRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        this.prestamoRepository.deleteById(id);
    }

}
