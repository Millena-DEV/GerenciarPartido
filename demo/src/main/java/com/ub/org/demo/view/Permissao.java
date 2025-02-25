package com.ub.org.demo.view;

import com.ub.org.demo.enums.Funcao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Permissao {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String criar;  // Exemplo: "CRIAR", "EDITAR", "EXCLUIR"
    @Column(nullable = false)
    private String editar; 
    @Column(nullable = false)
    private String excluir; 
    @Column(nullable = false)
    private String vizualizar; 
    @Column(nullable = false)
    private String aprovar; 
    @Column(nullable = false)
    private String acessar; 
    @Column(nullable = false)
    private String interagir; 
    @Column(nullable = false)
    private String bloqueia_user; 
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario; // Associação com o usuário

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Funcao funcao;  // Função do usuário associada à permissão

  

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getBloqueia_user() {
        return bloqueia_user;
    }

    public void setBloqueia_user(String bloqueia_user) {
        this.bloqueia_user = bloqueia_user;
    }

    public String getCriar() {
        return criar;
    }

    public void setCriar(String criar) {
        this.criar = criar;
    }

    public String getEditar() {
        return editar;
    }

    public void setEditar(String editar) {
        this.editar = editar;
    }

    public String getExcluir() {
        return excluir;
    }

    public void setExcluir(String excluir) {
        this.excluir = excluir;
    }

    public String getVizualizar() {
        return vizualizar;
    }

    public void setVizualizar(String vizualizar) {
        this.vizualizar = vizualizar;
    }

    public String getAprovar() {
        return aprovar;
    }

    public void setAprovar(String aprovar) {
        this.aprovar = aprovar;
    }

    public String getAcessar() {
        return acessar;
    }

    public void setAcessar(String acessar) {
        this.acessar = acessar;
    }

    public String getInteragir() {
        return interagir;
    }

    public void setInteragir(String interagir) {
        this.interagir = interagir;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
