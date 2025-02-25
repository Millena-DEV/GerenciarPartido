package com.ub.org.demo.view;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "vw_historico_filiados", schema = "uniao_brasil")
public class HistoricoFiliados {
    @Id
    @Column(name = "titulo")   
    private String titulo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "genero")
    private String genero;

    @Column(name = "data_filiacao")
    private String dataFiliacao;

    @Column(name = "uf")
    private String uf;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "zona")
    private String zona;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "motivo_desfiliacao")
    private String motivo_desfiliacao;

    @Column(name = "data_atualizacao")
    private String data_insercao;

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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getMotivo_desfiliacao() {
        return motivo_desfiliacao;
    }

    public void setMotivo_desfiliacao(String motivo_desfiliacao) {
        this.motivo_desfiliacao = motivo_desfiliacao;
    }

    public String getData_insercao() {
        return data_insercao;
    }

    public void setData_insercao(String data_insercao) {
        this.data_insercao = data_insercao;
    }

   


    

}

  