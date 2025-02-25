package com.ub.org.demo.dto;

import java.util.Date;

public class CandidatoDTO {
    private Long id;
    private Integer ano;
    private String turno;
    private String uf;
    private String cod_muni;
    private String municipio;
    private String cargo;
    private String cd_cand;
    private String nome;
    private String nascimento;
    private String titulo;
    private String cpf;
    private String genero;
    private String etinia;
    private String despesa;
    private String situ_cand;
    private String reeleicao;
    private String partido;
    private Long contagem; // Contagem de candidatos
    private String situ_filiacao;
    private Integer ano_filtro;
    private Integer qtd_votos;
    private Date data_insercao;
    
   


    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uF) {
        uf = uF;
    }
    public String getCod_muni() {
        return cod_muni;
    }
    public void setCod_muni(String cod_muni) {
        this.cod_muni = cod_muni;
    }
    public String getMunicipio() {
        return municipio;
    }
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getCd_cand() {
        return cd_cand;
    }
    public void setCd_cand(String cd_cand) {
        this.cd_cand = cd_cand;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
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
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getEtinia() {
        return etinia;
    }
    public void setEtinia(String etinia) {
        this.etinia = etinia;
    }
    public String getDespesa() {
        return despesa;
    }
    public void setDespesa(String despesa) {
        this.despesa = despesa;
    }
    public String getSitu_cand() {
        return situ_cand;
    }
    public void setSitu_cand(String situ_cand) {
        this.situ_cand = situ_cand;
    }
    public String getSitu_fili() {
        return situ_filiacao;
    }
    public void setSitu_fili(String situ_filiacao) {
        this.situ_filiacao = situ_filiacao;
    }
    public String getReeleicao() {
        return reeleicao;
    }
    public void setReeleicao(String reeleicao) {
        this.reeleicao = reeleicao;
    }
    public String getPartido() {
        return partido;
    }
    public void setPartido(String partido) {
        this.partido = partido;
    }
   
    public Long getContagem() {
        return contagem;
    }
    public void setContagem(Long contagem) {
        this.contagem = contagem;
    }
    public Integer getAno() {
        return ano;
    }
    public void setAno(Integer ano) {
        this.ano = ano;
    }
    public String getSitu_filiacao() {
        return situ_filiacao;
    }
    public void setSitu_filiacao(String situ_filiacao) {
        this.situ_filiacao = situ_filiacao;
    }
    public Integer getAno_filtro() {
        return ano_filtro;
    }
    public void setAno_filtro(Integer ano_filtro) {
        this.ano_filtro = ano_filtro;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNascimento() {
        return nascimento;
    }
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
    public Integer getQtd_votos() {
        return qtd_votos;
    }
    public void setQtd_votos(Integer qtd_votos) {
        this.qtd_votos = qtd_votos;
    }
    public Date getData_insercao() {
        return data_insercao;
    }
    public void setData_insercao(Date data_insercao) {
        this.data_insercao = data_insercao;
    }

    
}
