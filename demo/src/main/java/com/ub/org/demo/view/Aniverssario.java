package com.ub.org.demo.view;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vw_candidatos_aniverssarios", schema = "uniao_brasil")
public class Aniverssario {
    @Id
    @Column(name = "id")
    private Long id;
	@Column(name = "uf")
    private String uf;
	@Column(name = "municipio")
    private String municipio;
	@Column(name = "cargo")
    private String cargo;
	@Column(name = "nome")
    private String nome;
	@Column(name = "dt_nasc")
    private LocalDate dtNasc;
	@Column(name = "titulo")
    private String titulo;
    @Column(name="ano_validade_mandato")
    private Integer ano_validade_mandato;
    @Column(name = "completando")
    private String completando;
    @Column(name = "dia_niver")
    private int dia_niver;
    @Column(name = "mes_niver")
    private int mes_niver;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
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
    public Integer getAno_validade_mandato() {
        return ano_validade_mandato;
    }
    public void setAno_validade_mandato(Integer ano_validade_mandato) {
        this.ano_validade_mandato = ano_validade_mandato;
    }
    public LocalDate getDtNasc() {
        return dtNasc;
    }
    public void setDtNasc(LocalDate dtNasc) {
        this.dtNasc = dtNasc;
    }
    public String getCompletando() {
        return completando;
    }
    public void setCompletando(String completando) {
        this.completando = completando;
    }
    public int getDia_niver() {
        return dia_niver;
    }
    public void setDia_niver(int dia_niver) {
        this.dia_niver = dia_niver;
    }
    public int getMes_niver() {
        return mes_niver;
    }
    public void setMes_niver(int mes_niver) {
        this.mes_niver = mes_niver;
    }




    

    
}
