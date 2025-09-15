package com.notaRapida.services;

import com.notaRapida.dtos.DashboardDTO;
import com.notaRapida.dtos.FaturaResumoDTO;
import com.notaRapida.models.Fatura;
import com.notaRapida.models.enums.StatusFatura;
import com.notaRapida.repositories.FaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private FaturaRepository faturaRepository;

    public DashboardDTO getDashboardData() {
        long totalFaturas = faturaRepository.count();
        double valorTotal = faturaRepository.sumAllValores();
        long faturasVencidas = faturaRepository.countByStatus(StatusFatura.VENCIDA);
        long proximosVencimentos = faturaRepository.countByVencimentoBetween(
                LocalDate.now(), LocalDate.now().plusDays(7));

        List<Fatura> ultimasFaturas = faturaRepository.findTop3ByOrderByIdDesc();

        List<FaturaResumoDTO> recentes = new ArrayList<>();

        for (Fatura f : ultimasFaturas) {
            FaturaResumoDTO dto = new FaturaResumoDTO(
                    "FAT-" + f.getId(),
                    f.getCliente().getNome(),
                    f.getValorTotal(),
                    f.getStatus()
            );
            recentes.add(dto);
        }

        return new DashboardDTO(totalFaturas, valorTotal, faturasVencidas, proximosVencimentos, recentes);
    }

}
