package com.notaRapida.dtos;

import java.util.List;

public class DashboardDTO {

    private Long totalFaturas;
    private Double valorTotal;
    private Long faturasVencidas;
    private Long proximosVencimentos;
    private List<FaturaResumoDTO> faturasRecentes;

    public DashboardDTO() {
    }

    public DashboardDTO(Long totalFaturas, Double valorTotal, Long faturasVencidas, Long proximosVencimentos, List<FaturaResumoDTO> faturasRecentes) {
        this.totalFaturas = totalFaturas;
        this.valorTotal = valorTotal;
        this.faturasVencidas = faturasVencidas;
        this.proximosVencimentos = proximosVencimentos;
        this.faturasRecentes = faturasRecentes;
    }

    public Long getTotalFaturas() {
        return totalFaturas;
    }

    public void setTotalFaturas(Long totalFaturas) {
        this.totalFaturas = totalFaturas;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getFaturasVencidas() {
        return faturasVencidas;
    }

    public void setFaturasVencidas(Long faturasVencidas) {
        this.faturasVencidas = faturasVencidas;
    }

    public Long getProximosVencimentos() {
        return proximosVencimentos;
    }

    public void setProximosVencimentos(Long proximosVencimentos) {
        this.proximosVencimentos = proximosVencimentos;
    }

    public List<FaturaResumoDTO> getFaturasRecentes() {
        return faturasRecentes;
    }

    public void setFaturasRecentes(List<FaturaResumoDTO> faturasRecentes) {
        this.faturasRecentes = faturasRecentes;
    }

    @Override
    public String toString() {
        return "DashboardDTO{" +
                "totalFaturas=" + totalFaturas +
                ", valorTotal=" + valorTotal +
                ", faturasVencidas=" + faturasVencidas +
                ", proximosVencimentos=" + proximosVencimentos +
                ", faturasRecentes=" + faturasRecentes +
                '}';
    }
}
