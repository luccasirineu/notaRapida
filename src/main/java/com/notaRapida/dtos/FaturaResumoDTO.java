package com.notaRapida.dtos;

import java.math.BigDecimal;

public class FaturaResumoDTO {
    private String codigo;
    private String cliente;
    private BigDecimal valorTotal;
    private String status;

    public FaturaResumoDTO() {
    }

    public FaturaResumoDTO(String codigo, String cliente, BigDecimal valorTotal, String status) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.valor = valorTotal;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
