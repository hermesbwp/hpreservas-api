package com.hp.hpreservasapi.repository;

import com.hp.hpreservasapi.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuartoRepository extends JpaRepository<Quarto, Long> {
}
