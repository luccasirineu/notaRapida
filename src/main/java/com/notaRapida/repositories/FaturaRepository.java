package com.notaRapida.repositories;

import com.notaRapida.models.Fatura;
import com.notaRapida.models.enums.StatusFatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {

    @Query("SELECT SUM(f.valorTotal) FROM Fatura f")
    Double sumAllValores();

    Long countByStatus(StatusFatura status);

    Long countByVencimentoBetween(LocalDate inicio, LocalDate fim);

    List<Fatura> findTop3ByOrderByIdDesc();
}
