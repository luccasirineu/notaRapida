package com.notaRapida.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomeFatura;

    private LocalDate vencimento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)  //orphanRemoval serve para remover todos os registros que sao orfaos dele
    @JoinColumn(name = "fatura_id")
    private List<ItemFatura> itens = new ArrayList<>();

    private String observacoes;

    private BigDecimal valorTotal;

    @Lob
    @Column( columnDefinition = "LONGBLOB")
    private byte[] arquivoPdf;


    public Fatura() {
    }

    public Fatura(Long id, String nomeFatura, LocalDate vencimento, Cliente cliente, List<ItemFatura> itens, String observacoes, BigDecimal valorTotal, byte[] arquivoPdf) {
        this.id = id;
        this.nomeFatura = nomeFatura;
        this.vencimento = vencimento;
        this.cliente = cliente;
        this.itens = itens;
        this.observacoes = observacoes;
        this.valorTotal = valorTotal;
        this.arquivoPdf = arquivoPdf;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemFatura> getItens() {
        return itens;
    }

    public void setItens(List<ItemFatura> itens) {
        this.itens = itens;
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

    public byte[] getArquivoPdf() {
        return arquivoPdf;
    }

    public void setArquivoPdf(byte[] arquivoPdf) {
        this.arquivoPdf = arquivoPdf;
    }
}
