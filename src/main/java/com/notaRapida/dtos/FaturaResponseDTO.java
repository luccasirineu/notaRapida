package com.notaRapida.dtos;

import com.notaRapida.dtos.ItemFaturaRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FaturaResponseDTO {

    private Long id;
    private String nomeFatura;
    private LocalDate vencimento;
    private String observacoes;
    private BigDecimal valorTotal;

    private ClienteDTO clienteDTO;

    private List<ItemFaturaRequestDTO> itens;

    public FaturaResponseDTO() {
    }

    public FaturaResponseDTO(Long id, String nomeFatura, LocalDate vencimento, String observacoes, BigDecimal valorTotal,
                             ClienteDTO cliente, List<ItemFaturaRequestDTO> itens) {
        this.id = id;
        this.nomeFatura = nomeFatura;
        this.vencimento = vencimento;
        this.observacoes = observacoes;
        this.valorTotal = valorTotal;
        this.clienteDTO = cliente;
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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setCliente(ClienteDTO cliente) {
        this.clienteDTO = cliente;
    }

    public List<ItemFaturaRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemFaturaRequestDTO> itens) {
        this.itens = itens;
    }
}