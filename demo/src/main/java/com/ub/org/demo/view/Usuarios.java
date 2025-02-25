package com.ub.org.demo.view;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.ub.org.demo.enums.Funcao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Entity
@Table(name = "usuarios" , schema = "uniao_brasil")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Nome é obrigatório")
    private String nome; // Nome de usuário
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Sobrenome é obrigatório")
    private String sobrenome; // Nome de usuário
    @Column(nullable = false)
    @NotBlank(message = "Senha é obrigatória")
    private String senha; // Senha
    @Column(nullable = false)
    private String cargo; // Campo para cargo
    @Column(nullable = false)
    @NotBlank(message = "UF é obrigatório")
    private String uf; // Campo para cargo
    @Column(nullable = false)
    @NotBlank(message = "Municipio é obrigatório")
    private String municipio; // Campo para cargo

    @Column(unique = true, nullable = false)
    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    private String email; // Campo para email

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
     private LocalDateTime data_cadastro;

     @Column(name = "token_recuperacao")
     private String tokenRecuperacao;
 
     @Column(name = "data_expiracao_token")
     private LocalDateTime dataExpiracaoToken;

     @Enumerated(EnumType.STRING)
     private Funcao funcao; // Papéis do usuário

      @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
        private List<Permissao> permissoes; // As permissões associadas ao usuário
        
        @Column(unique = true, nullable = false)
        private String cpf;
        private String userLogado; 
        
        


     public String getTokenRecuperacao() {
        return tokenRecuperacao;
    }


    public void setTokenRecuperacao(String tokenRecuperacao) {
        this.tokenRecuperacao = tokenRecuperacao;
    }


    public LocalDateTime getDataExpiracaoToken() {
        return dataExpiracaoToken;
    }


    public void setDataExpiracaoToken(LocalDateTime dataExpiracaoToken) {
        this.dataExpiracaoToken = dataExpiracaoToken;
    }


    public Usuarios() {
        // Define a data de cadastro no momento da criação
        this.data_cadastro = LocalDateTime.now();
    }


    public LocalDateTime getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(LocalDateTime data_cadastro) {
        this.data_cadastro = data_cadastro;
    }


   
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome= nome;
    }
    

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    public Funcao getFuncao() {
        return funcao;
    }


    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }


    public List<Permissao> getPermissoes() {
        return permissoes;
    }


    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public String getUserLogado() {
        return userLogado;
    }


    public void setUserLogado(String userLogado) {
        this.userLogado = userLogado;
    }




   
   
    

}
