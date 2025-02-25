package com.ub.org.demo.view;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "vw_filiados_grafico", schema = "uniao_brasil")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GraficoFiliado {
    @Id
     @Column(name = "titulo")   
     private String titulo;
     
     @Column(name = "genero")
     private String genero;


    @Column(name = "ano_filiacao")
    private Integer anoFiliacao;

    @Column(name = "uf")
    private String uf;

    @Column(name = "municipio")
    private String municipio;

     @Column(name="data_insercao")
    private Date data_insercao;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnoFiliacao() {
        return anoFiliacao;
    }

    public void setAnoFiliacao(Integer anoFiliacao) {
        this.anoFiliacao = anoFiliacao;
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

    public Date getData_insercao() {
        return data_insercao;
    }

    public void setData_insercao(Date data_insercao) {
        this.data_insercao = data_insercao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }



}
