package com.notaRapida.dtos;

import com.notaRapida.models.Cliente;
import com.notaRapida.models.ItemFatura;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FaturaRequestDTO {
    private String nomeFatura;
    private LocalDate vencimento;
    private String observacoes;

    private String nomeCliente;
    private String emailCliente;
    private String enderecoCliente;
    private String cidadeCliente;
    private String ufCliente; 
    private String cepCliente;

    private List<ItemFatura> itens = new ArrayList<>();

    public FaturaRequestDTO() {
    }

    public FaturaRequestDTO(String nomeFatura, LocalDate vencimento, String observacoes, String nomeCliente, String emailCliente, String enderecoCliente, String cidadeCliente, String ufCliente, String cepCliente, List<ItemFatura> itens) {
        this.nomeFatura = nomeFatura;
        this.vencimento = vencimento;
        this.observacoes = observacoes;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.enderecoCliente = enderecoCliente;
        this.cidadeCliente = cidadeCliente;
        this.ufCliente = ufCliente;
        this.cepCliente = cepCliente;
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

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getCidadeCliente() {
        return cidadeCliente;
    }

    public void setCidadeCliente(String cidadeCliente) {
        this.cidadeCliente = cidadeCliente;
    }

    public String getUfCliente() {
        return ufCliente;
    }

    public void setUfCliente(String ufCliente) {
        this.ufCliente = ufCliente;
    }

    public String getCepCliente() {
        return cepCliente;
    }

    public void setCepCliente(String cepCliente) {
        this.cepCliente = cepCliente;
    }

    public List<ItemFatura> getItens() {
        return itens;
    }

    public void setItens(List<ItemFatura> itens) {
        this.itens = itens;
    }
}
