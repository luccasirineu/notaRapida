package com.notaRapida.dtos;

import com.notaRapida.models.Cliente;
import com.notaRapida.models.ItemFatura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FaturaRequestDTO {
    private String nomeFatura;
    private LocalDate vencimento;
    private String observacoes;
    private BigDecimal valorTotal;

    private ClienteDTO clienteDTO;

    private List<ItemFaturaRequestDTO> itens = new ArrayList<>();

    public FaturaRequestDTO() {
    }

    public FaturaRequestDTO(String nomeFatura, LocalDate vencimento, String observacoes, BigDecimal valorTotal, ClienteDTO clienteDTO, List<ItemFaturaRequestDTO> itens) {
        this.nomeFatura = nomeFatura;
        this.vencimento = vencimento;
        this.observacoes = observacoes;
        this.valorTotal = valorTotal;
        this.clienteDTO = clienteDTO;
        this.itens = itens;
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

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }

    public List<ItemFaturaRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemFaturaRequestDTO> itens) {
        this.itens = itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
