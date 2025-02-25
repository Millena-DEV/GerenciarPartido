package com.ub.org.demo.view;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FiliacaoInterna", schema = "uniao_brasil")
public class FiliacaoInterna {
    @Id
    @GeneratedValue 
    private long id;
    @Column(name = "titulo", unique = true)
    private String titulo;
    private String cpf;
    private String nome;
    private String dataNascimento;
    private String genero;

    private String nomePai;

    private String nomeMae;

  @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime data_cadastro;

   private String dataFiliacao;

   private String tipo_filiacao;
    private String uf;

    private String municipio;


    private String zona;

    private String secao;
   
    private String motivoDesfiliacao;

    
    private String tipoLogradouro;

   
    private String logradouro;

  
    private String complemento;

    
    private String numero;

    private String bairro;

 
    private String cep;

    private String numeroTelefone1;

   
    private String numeroTelefone2;

    private String email;


    private String situacao;


    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    // Ajustando para remover a parte dos nanosegundos
    public void setDataCadastro(LocalDateTime data_cadastro) {
        this.data_cadastro = data_cadastro.truncatedTo(ChronoUnit.MILLIS);
    }


    public LocalDateTime getData_cadastro() {
        return data_cadastro;
    }



    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
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


    public void setData_cadastro(LocalDateTime data_cadastro) {
        this.data_cadastro = data_cadastro;
    }


    public String getTipo_filiacao() {
        return tipo_filiacao;
    }


    public void setTipo_filiacao(String tipo_filiacao) {
        this.tipo_filiacao = tipo_filiacao;
    }


    public String getDataNascimento() {
        return dataNascimento;
    }


    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


   


    
    


	

   
}
  