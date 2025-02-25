package com.ub.org.demo.service;
import com.ub.org.demo.repository.DocumentoRepository;
import com.ub.org.demo.repository.FiliacaoInternaRepository;
import com.ub.org.demo.view.Documentos;
import com.ub.org.demo.view.FiliacaoInterna;
import com.ub.org.demo.view.Filiados;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FiliacaoInternaService {
  
@Autowired
private FiliacaoInternaRepository filiadoRepository;
@Autowired
private DocumentoRepository documentoRepository;

//private static final String DIRETORIO_ARQUIVOS = "C://Users//millena.ti//OneDrive - União Brasil//DocumentoSistema//Documentos-Filiados Internos//";
private static final String DIRETORIO_ARQUIVOS = "C://DocumentoSistema//Documentos-Filiados Internos//";
 

public void salvarFiliadoEArquivos(String titulo, String bairro, String cep, String complemento, String cpf, 
String dataNascimento, String email, String genero, String logradouro, 
String municipio, String nome, String nomeMae, String nomePai, String numero, 
String celular1, String celular2, String secao, 
String tipo_logradouro, String uf, String zona, 
MultipartFile foto, MultipartFile cpfFile, MultipartFile tituloDoc, 
MultipartFile ficha, MultipartFile ficha_deslifiacao) throws IOException {

    // 1. Criar e salvar o filiado no banco de dados
    FiliacaoInterna novoFiliado = new FiliacaoInterna();
    novoFiliado.setTitulo(titulo);
    novoFiliado.setBairro(bairro);
    novoFiliado.setCep(cep);
    novoFiliado.setComplemento(complemento);
    novoFiliado.setCpf(cpf);
    novoFiliado.setDataNascimento(dataNascimento);
    novoFiliado.setEmail(email);
    novoFiliado.setGenero(genero);
    novoFiliado.setLogradouro(logradouro);
    novoFiliado.setMunicipio(municipio);
    novoFiliado.setNome(nome);
    novoFiliado.setNomeMae(nomeMae);
    novoFiliado.setNomePai(nomePai);
    novoFiliado.setNumero(numero);
    novoFiliado.setNumeroTelefone1(celular1);
    novoFiliado.setNumeroTelefone2(celular2);
    novoFiliado.setSecao(secao);
    novoFiliado.setSituacao("Aguardando Validação");
    novoFiliado.setTipoLogradouro(tipo_logradouro);
    novoFiliado.setUf(uf);
    novoFiliado.setZona(zona);
    novoFiliado.setTipo_filiacao("Filiação interna");

    // Salvar o novo filiado no banco de dados
    filiadoRepository.save(novoFiliado);

    // 2. Salvar os documentos do filiado (como foto, CPF, título de eleitor, etc.)
    // Criar um diretório para o filiado
    File pastaFiliado = new File(DIRETORIO_ARQUIVOS + titulo); 
    if (!pastaFiliado.exists()) {
    pastaFiliado.mkdirs();
    }
    // Salvar os documentos
    if (foto != null && !foto.isEmpty()) {
    salvarArquivo(foto, pastaFiliado, "Foto_Documento", titulo);
    }

    if (cpfFile != null && !cpfFile.isEmpty()) {
    salvarArquivo(cpfFile, pastaFiliado, "CPF", titulo);
    }

    if (tituloDoc != null && !tituloDoc.isEmpty()) {
    salvarArquivo(tituloDoc, pastaFiliado, "Titulo_Eleitor", titulo);
    }

    if (ficha != null && !ficha.isEmpty()) {
    salvarArquivo(ficha, pastaFiliado, "Ficha_Filiação", titulo);
    }

    if (ficha_deslifiacao != null && !ficha_deslifiacao.isEmpty()) {
    salvarArquivo(ficha_deslifiacao, pastaFiliado, "Ficha_Deslifiacao", titulo);
      }
}
private void salvarArquivo(MultipartFile arquivo, File diretório, String tipoDocumento, @RequestParam String titulo) throws IOException {
    // Gerar um nome único para o arquivo (incluindo o título do filiado e UUID)
    if (arquivo == null || arquivo.isEmpty()) {
        // Ignorar o arquivo se for null ou vazio, ou lançar uma exceção, se necessário
        return;  // Ou lançar uma exceção personalizada se necessário
    }
      
    String nomeArquivoOriginal = arquivo.getOriginalFilename();
    String extensaoArquivo = nomeArquivoOriginal.substring(nomeArquivoOriginal.lastIndexOf("."));
    
    // Gerar um nome único para o arquivo, incluindo o título e UUID
    String nomeArquivo =  "_" + tipoDocumento + "_" + titulo + extensaoArquivo;

    // Caminho completo onde o arquivo será salvo
    Path caminhoArquivo = Paths.get(diretório.getAbsolutePath() + "/" + nomeArquivo);

    // Copiar o arquivo para o diretório
    try (InputStream inputStream = arquivo.getInputStream()) {
        Files.copy(inputStream, caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);
    }

  
    FiliacaoInterna filiado = filiadoRepository.findByTitulo(titulo)
        .orElseThrow(() -> new IllegalArgumentException("Filiado não encontrado com título: " + titulo));

        
     
    // Criar um novo objeto Documento para persistir no banco de dados
    Documentos documento = new Documentos();
    documento.setFiliadoInterno(filiado); // Associa o filiado ao documento
    documento.setNomeArquivo(nomeArquivo);
    documento.setTipoMime(arquivo.getContentType());
    documento.setCaminhoArquivo(caminhoArquivo.toString());

    // Persistir o documento no banco de dados
    documentoRepository.save(documento);
}
public Optional<FiliacaoInterna> buscarPorId(String titulo) {
    return filiadoRepository.findByTitulo(titulo);
}


  // Método para listar documentos de um filiado
  public List<Documentos> listarDocumentos(Filiados filiado) {
      return documentoRepository.findByFiliado(filiado);
  }

  // Método para buscar um documento por ID
  public Optional<Documentos> buscarDocumentoPorId(Long idDocumento) {
      return documentoRepository.findByIdDocumento(idDocumento);
  }
}



            
      

   
