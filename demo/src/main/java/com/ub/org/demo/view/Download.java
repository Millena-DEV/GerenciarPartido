package com.ub.org.demo.view;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Download {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomeArquivo;
    private String usuario;
    private LocalDateTime dataDownload;

    public Download(String nomeArquivo, String usuario, LocalDateTime dataDownload) {
        this.nomeArquivo = nomeArquivo;
        this.usuario = usuario;
        this.dataDownload = dataDownload;
        
    }
    

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataDownload() {
        return dataDownload;
    }

    public void setDataDownload(LocalDateTime dataDownload) {
        this.dataDownload = dataDownload;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

}
