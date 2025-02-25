package com.ub.org.demo.controller.Cadastros;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

import com.ub.org.demo.Specifications.ResponseMessage;
import com.ub.org.demo.service.DocumentoService;

@RestController
public class DocumentoController {

      @Autowired
    private DocumentoService documentoService;
    
    //private static final String DIRETORIO_ARQUIVOS = "C://Users//millena.ti//OneDrive - União Brasil//DocumentoSistema//Documentos - Filiados//"; 
    //private static final String DIRETORIO_ARQUIVOS_INTERNOS = "C://Users//millena.ti//OneDrive - União Brasil//DocumentoSistema//Documentos-Filiados Internos//"; 
    private static final String DIRETORIO_ARQUIVOS = "C://DocumentoSistema//Documentos - Filiados//"; 
    private static final String DIRETORIO_ARQUIVOS_INTERNOS = "C://DocumentoSistema//Documentos-Filiados Internos//";

     @GetMapping("/documentos")
        public String showDocumentoForm(Model model) {
            return "documentos"; // Retorna a página de cadastro
        }

     // Endpoint para upload de um novo documento
     @PostMapping("/upload/{titulo}")
        public ResponseEntity<ResponseMessage> uploadDocumento(
            @PathVariable String titulo, // Captura o ID do filiado
            @RequestParam(required = false) MultipartFile foto,
            @RequestParam(required = false) MultipartFile cpf,
            @RequestParam(required = false) MultipartFile tituloDoc,
            @RequestParam(required = false) MultipartFile ficha,
            @RequestParam(required = false) MultipartFile ficha_deslifiacao
            ) {

                try {
                    // Envia os arquivos para o serviço que cuidará do armazenamento
                    documentoService.salvarDocumento(titulo, foto, cpf, tituloDoc, ficha, ficha_deslifiacao);
        
                    // Retorna a resposta em JSON com status e mensagem
                    ResponseMessage response = new ResponseMessage("success", "Documentos enviados com sucesso!");
                    return ResponseEntity.ok(response);
                } catch (IOException e) {
                    // Retorna a resposta de erro em JSON com status e mensagem
                    ResponseMessage response = new ResponseMessage("error", "Erro ao enviar documentos: " + e.getMessage());
                    return ResponseEntity.status(500).body(response);
                }
    }
 

      // Endpoint para listar todos os arquivos de um diretório específico
      @GetMapping("/documentos/{titulo}/arquivos")
      public ResponseEntity<List<String>> listarArquivos(@PathVariable String titulo) {
        File pastaFiliado = new File(DIRETORIO_ARQUIVOS + titulo); // Diretório com os arquivos
        List<String> arquivos = new ArrayList<>();
        
        // Verifica se o diretório existe
        if (pastaFiliado.exists() && pastaFiliado.isDirectory()) {
            for (File arquivo : pastaFiliado.listFiles()) {
                if (arquivo.isFile()) {
                    arquivos.add(arquivo.getName()); // Adiciona o nome do arquivo à lista
                }
            }
        }
    
        // Retorna a lista de arquivos para o cliente
        return ResponseEntity.ok(arquivos);
    }

    // Endpoint para servir os arquivos de imagem dinamicamente
    @GetMapping("/documentos/{titulo}/imagem/{fileName}") 
    public ResponseEntity<Resource> serveImage(@PathVariable String titulo, @PathVariable String fileName) {
        File file = new File(DIRETORIO_ARQUIVOS + titulo + "//" + fileName);
        
        if (file.exists()) {
            // Detectando o tipo MIME do arquivo
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        
        // Se o tipo MIME não puder ser detectado, verificamos a extensão do arquivo
        if (mimeType == null) {
            if (fileName.endsWith(".pdf")) {
                mimeType = "application/pdf"; // Definindo tipo MIME para PDFs
            } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                mimeType = "image/jpeg"; // Definindo tipo MIME para imagens JPEG97
            } else if (fileName.endsWith(".png")) {
                mimeType = "image/png"; // Definindo tipo MIME para imagens PNG
            } else if (fileName.endsWith(".gif")) {
                mimeType = "image/gif"; // Definindo tipo MIME para imagens GIF
            }else if(fileName.endsWith(".jfif")){
                mimeType = "image/jfif"; // Definindo tipo MIME para imagens GIF
            } else {
                mimeType = "application/octet-stream"; // Tipo genérico para outros arquivos
            }
        }

            // Usando o tipo MIME detectado
            MediaType mediaType = MediaType.parseMediaType(mimeType);

            // Criando o recurso a partir do arquivo
            Resource resource = new FileSystemResource(file);

            // Retorna a imagem com o tipo MIME correto para exibição
            return ResponseEntity.ok()
                    .contentType(mediaType)  // Define o tipo de mídia corretamente
                    .body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 se o arquivo não for encontrado
        }
    }
/****************************************************************************************************************************************************************************************************************** */

    // Endpoint para listar todos os arquivos de um diretório específico
    @GetMapping("/documentos-interno/{titulo}/arquivos")
    public ResponseEntity<List<String>> listarArquivo(@PathVariable String titulo) {
      File pastaFiliado = new File(DIRETORIO_ARQUIVOS_INTERNOS + titulo); // Diretório com os arquivos
      List<String> arquivos = new ArrayList<>();
      
      // Verifica se o diretório existe
      if (pastaFiliado.exists() && pastaFiliado.isDirectory()) {
          for (File arquivo : pastaFiliado.listFiles()) {
              if (arquivo.isFile()) {
                  arquivos.add(arquivo.getName()); // Adiciona o nome do arquivo à lista
              }
          }
      }
  
      // Retorna a lista de arquivos para o cliente
      return ResponseEntity.ok(arquivos);
  }

  // Endpoint para servir os arquivos de imagem dinamicamente
  @GetMapping("/documentos-interno/{titulo}/imagem/{fileName}") 
  public ResponseEntity<Resource> serveImages(@PathVariable String titulo, @PathVariable String fileName) {
      File file = new File(DIRETORIO_ARQUIVOS_INTERNOS + titulo + "//" + fileName);
      
      if (file.exists()) {
          // Detectando o tipo MIME do arquivo
      String mimeType = URLConnection.guessContentTypeFromName(file.getName());
      
      // Se o tipo MIME não puder ser detectado, verificamos a extensão do arquivo
      if (mimeType == null) {
          if (fileName.endsWith(".pdf")) {
              mimeType = "application/pdf"; // Definindo tipo MIME para PDFs
          } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
              mimeType = "image/jpeg"; // Definindo tipo MIME para imagens JPEG
          } else if (fileName.endsWith(".png")) {
              mimeType = "image/png"; // Definindo tipo MIME para imagens PNG
          } else if (fileName.endsWith(".gif")) {
              mimeType = "image/gif"; // Definindo tipo MIME para imagens GIF
          }else if(fileName.endsWith(".jfif")){
              mimeType = "image/jfif"; // Definindo tipo MIME para imagens GIF
          } else {
              mimeType = "application/octet-stream"; // Tipo genérico para outros arquivos
          }
      }

          // Usando o tipo MIME detectado
          MediaType mediaType = MediaType.parseMediaType(mimeType);

          // Criando o recurso a partir do arquivo
          Resource resource = new FileSystemResource(file);

          // Retorna a imagem com o tipo MIME correto para exibição
          return ResponseEntity.ok()
                  .contentType(mediaType)  // Define o tipo de mídia corretamente
                  .body(resource);
      } else {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 se o arquivo não for encontrado
      }
  }


}
