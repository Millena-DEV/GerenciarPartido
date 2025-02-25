package com.ub.org.demo.view;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "vw_filiados", schema = "uniao_brasil")
public class FiliadoView {
    @Id
     @Column(name = "titulo")   
     private String titulo;
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_nascimento") // Atualizando para minúsculas e sem espaços
    private String dataNascimento;

    @Column(name = "genero")
    private String genero;

    @Column(name = "nome_pai")
    private String nomePai;

    @Column(name = "nome_mae")
    private String nomeMae;

    @Column(name = "data_filiacao")
    private String dataFiliacao;

    @Column(name = "ano_filiacao")
    private Integer anoFiliacao;

    @Column(name = "uf")
    private String uf;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "zona")
    private String zona;

    @Column(name = "secao")
    private String secao;
    @Column(name = "motivo_desfiliacao")
    private String motivoDesfiliacao;

    @Column(name = "tipo_logradouro")
    private String tipoLogradouro;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "numero")
    private String numero;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "numero_telefone1")
    private String numeroTelefone1;

    @Column(name = "numero_telefone2")
    private String numeroTelefone2;

    @Column(name = "email")
    private String email;

    @Column(name = "situacao")
    private String situacao;
     @Column(name="data_insercao")
    private Date data_insercao;




    public Date getData_insercao() {
        return data_insercao;
    }



    public void setData_insercao(Date data_insercao) {
        this.data_insercao = data_insercao;
    }



    public String getCpf() {
        return cpf;
    }

    

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getDataFiliacao() {
        return dataFiliacao;
    }

    public void setDataFiliacao(String dataFiliacao) {
        this.dataFiliacao = dataFiliacao;
    }
    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }






    public String getMotivoDesfiliacao() {
        return motivoDesfiliacao;
    }

    public void setMotivoDesfiliacao(String motivoDesfiliacao) {
        this.motivoDesfiliacao = motivoDesfiliacao;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumeroTelefone1() {
        return numeroTelefone1;
    }

    public void setNumeroTelefone1(String numeroTelefone1) {
        this.numeroTelefone1 = numeroTelefone1;
    }

    public String getNumeroTelefone2() {
        return numeroTelefone2;
    }

    public void setNumeroTelefone2(String numeroTelefone2) {
        this.numeroTelefone2 = numeroTelefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getSituacao() {
        return situacao;
    }



    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }



    public Integer getAnoFiliacao() {
        return anoFiliacao;
    }



    public void setAnoFiliacao(Integer anoFiliacao) {
        this.anoFiliacao = anoFiliacao;
    }

}
