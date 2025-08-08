package com.notaRapida.dtos;

import com.notaRapida.models.ItemFatura;

import java.time.LocalDate;
import java.util.List;

public class FaturaResponseDTO {

    private Long id; // ID da fatura gerada
    private String nomeFatura;
    private LocalDate vencimento;
    private String observacoes;
    private Double valorTotal;

    // Dados do Cliente
    private ClienteDTO cliente;

    // Itens da Fatura
    private List<ItemFatura> itens;

    public FaturaResponseDTO() {
    }

    public FaturaResponseDTO(Long id, String nomeFatura, LocalDate vencimento, String observacoes, Double valorTotal,
                             ClienteDTO cliente, List<ItemFatura> itens) {
        this.id = id;
        this.nomeFatura = nomeFatura;
        this.vencimento = vencimento;
        this.observacoes = observacoes;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFatura() {
        return nomeFatura;
    }

    public void setNomeFatura(String nomeFatura) {
        this.nomeFatura = nomeFatura;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public List<ItemFatura> getItens() {
        return itens;
    }

    public void setItens(List<ItemFatura> itens) {
        this.itens = itens;
    }
}