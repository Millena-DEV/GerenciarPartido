package com.ub.org.demo.view;

import jakarta.persistence.*;

@Entity
@Table(name = "municipio", schema = "uniao_brasil")
public class municipio {

    @Id // Define a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se a chave for gerada automaticamente
    private Long id; // Tipo da chave primária

    @Column(name = "municipio") // Nome da coluna no banco
    private String municipio;

    @Column(name = "uf") // Nome da coluna no banco
    private String uf;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
