package com.notaRapida.dtos;

import com.notaRapida.models.enums.StatusFatura;

import java.math.BigDecimal;

public class FaturaResumoDTO {
    private String codigo;
    private String cliente;
    private BigDecimal valorTotal;
    private StatusFatura status;

    public FaturaResumoDTO() {
    }

    public FaturaResumoDTO(String codigo, String cliente, BigDecimal valorTotal, StatusFatura status) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusFatura getStatus() {
        return status;
    }

    public void setStatus(StatusFatura status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FaturaResumoDTO{" +
                "codigo='" + codigo + '\'' +
                ", cliente='" + cliente + '\'' +
                ", valorTotal=" + valorTotal +
                ", status='" + status + '\'' +
                '}';
    }
}
