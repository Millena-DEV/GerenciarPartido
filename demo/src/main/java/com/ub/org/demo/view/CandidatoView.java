package com.ub.org.demo.view;
import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vw_candidatos", schema = "uniao_brasil")
public class CandidatoView {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "cd_cand")
    private String cd_cand; 
    @Column(name = "ano")
    private Integer ano;
    @Column(insertable=false, updatable=false,name = "ano")
    private String ano_csv;
	@Column(name = "turno")
    private String turno;
	@Column(name = "uf")
    private String uf;
	@Column(name = "cd_municipio")
    private String cod_muni;
	@Column(name = "municipio")
    private String municipio;
	@Column(name = "cargo")
    private String cargo;
	@Column(name = "nome")
    private String nome;
	@Column(name = "dt_nasc")
    private String nascimento;
	@Column(name = "titulo")
    private String titulo;
	@Column(name = "cpf")
    private String cpf;
	@Column(name = "genero")
    private String genero;
	@Column(name = "etnia")
    private String etinia;
	@Column(name = "despesa")
    private String despesa;
	@Column(name = "situ_cand")
    private String situ_cand;
	@Column(name = "reeleito")
    private String reeleito;
	@Column(name = "partido")
    private String partido;
    @Column(name="ano_validade_mandato")
    private Integer ano_validade_mandato;
    @Column(name="situ_filiacao")
    private String situ_filiacao;
    @Column(name="ano_filtro")
    private Integer ano_filtro;
    @Column(name="qtd_votos")
    private Integer qtd_votos;
    @Column(name="data_insercao")
    private Date data_insercao;



    public String getReeleito() {
        return reeleito;
    }
    public void setReeleito(String reeleito) {
        this.reeleito = reeleito;
    }
    public String getSitu_filiacao() {
        return situ_filiacao;
    }
    public String getAno_csv() {
        return ano_csv;
    }
    public void setAno_csv(String ano_csv) {
        this.ano_csv = ano_csv;
    }
    public void setSitu_filiacao(String situ_filiacao) {
        this.situ_filiacao = situ_filiacao;
    }
    public Integer getAno() {
        return ano;
    }
    public void setAno(Integer ano) {
        this.ano = ano;
    }
    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
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
    
    public String getPartido() {
        return partido;
    }
    public void setPartido(String partido) {
        this.partido = partido;
    }

    public void setAno_validade_mandato(Integer ano_validade_mandato) {
        this.ano_validade_mandato = ano_validade_mandato;
    }
    public Integer getAno_validade_mandato() {
        return ano_validade_mandato;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
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
