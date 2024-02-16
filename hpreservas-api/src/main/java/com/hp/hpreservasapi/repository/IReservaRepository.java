package com.hp.hpreservasapi.repository;

import com.hp.hpreservasapi.model.Quarto;
import com.hp.hpreservasapi.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("Select qtr from Quarto qtr " +
            "left join Reserva rvr " +
            "where rvr.inicio>=?1 " +
            "and rvr.fim<=?1 and qtr.status=?2")
    public abstract Optional<?> isAvaible(Date data, Quarto.QuartoStatus status);
}
