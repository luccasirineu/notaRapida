package com.notaRapida.models;

import jakarta.persistence.Entity;

@Entity
public class Cliente {

    private String nome;
    private String email;
    private String endereco;
    private String cidade;
    private String uf;
    private String cep;
    private String documento; // opcional

    public Cliente() {
    }

    public Cliente(String nome, String email, String endereco, String cidade, String uf, String cep, String documento) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.documento = documento;
    }

    public Cliente(String nome, String email, String endereco, String cidade, String uf, String cep) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
