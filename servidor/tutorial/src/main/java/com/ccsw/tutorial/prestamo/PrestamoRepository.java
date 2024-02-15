package com.ccsw.tutorial.prestamo;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.tutorial.cliente.model.Cliente;
import com.ccsw.tutorial.game.model.Game;
import com.ccsw.tutorial.prestamo.model.Prestamo;

/**
 * @author ccsw
 *
 */
public interface PrestamoRepository extends CrudRepository<Prestamo, Long>, JpaSpecificationExecutor<Prestamo> {

    /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param pageable pageable
     * @param spec     Specification
     * @return {@link Page} de {@link Prestamo}
     */

    @EntityGraph(attributePaths = { "game", "cliente" })
    Page<Prestamo> findAll(Specification<Prestamo> spec, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Prestamo p WHERE p.game = :game AND NOT (p.fechaDevolucion < :fechaPrestamo OR p.fechaPrestamo > :fechaDevolucion)")
    boolean juegoPrestado(@Param("game") Game game, @Param("fechaPrestamo") Date fechaPrestamo,
            @Param("fechaDevolucion") Date fechaDevolucion);

    @Query("SELECT COUNT(p) FROM Prestamo p WHERE p.cliente = :cliente AND NOT (p.fechaPrestamo > :fechaDevolucion OR p.fechaDevolucion < :fechaPrestamo)")
    int contarPrestamosPorClienteYFecha(@Param("cliente") Cliente cliente, @Param("fechaPrestamo") Date fechaPrestamo,
            @Param("fechaDevolucion") Date fechaDevolucion);

}
