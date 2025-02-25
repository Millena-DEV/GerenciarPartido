package com.ub.org.demo.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "documentos", schema = "uniao_brasil")
public class Documentos {
@Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "id_documento")
 private Long idDocumento;

 @ManyToOne
 @JoinColumn(name = "filiado_titulo", referencedColumnName = "titulo", nullable = true)
 private Filiados filiado;

 @ManyToOne
 @JoinColumn(name = "filiacao_titulo", referencedColumnName = "titulo", nullable = true)
 private FiliacaoInterna filiadoInterno;

 @Column(name = "nome_arquivo")
 private String nomeArquivo;

 @Column(name = "tipo_mime")
 private String tipoMime;

 @Column(name = "caminho_arquivo")
 private String caminhoArquivo;  // Armazenar o caminho do arquivo no sistema

public Filiados getFiliado() {
    return filiado;
}

public void setFiliado(Filiados filiado) {
    this.filiado = filiado;
}

public String getNomeArquivo() {
    return nomeArquivo;
}

public void setNomeArquivo(String nomeArquivo) {
    this.nomeArquivo = nomeArquivo;
}

public String getTipoMime() {
    return tipoMime;
}

public void setTipoMime(String tipoMime) {
    this.tipoMime = tipoMime;
}

public String getCaminhoArquivo() {
    return caminhoArquivo;
}

public void setCaminhoArquivo(String caminhoArquivo) {
    this.caminhoArquivo = caminhoArquivo;
}

public FiliacaoInterna getFiliadoInterno() {
    return filiadoInterno;
}

public void setFiliadoInterno(FiliacaoInterna filiadoInterno) {
    this.filiadoInterno = filiadoInterno;
}


}
