package com.ub.org.demo.controller.CSV;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.ub.org.demo.repository.CandidatoViewRepository;
import com.ub.org.demo.repository.FiliadoViewRepository;
import com.ub.org.demo.repository.UsuarioRepository;
import com.ub.org.demo.view.CandidatoView;
import com.ub.org.demo.view.FiliadoView;
import com.ub.org.demo.view.Usuarios;

@Service
public class ExportarCsvService {
    @Autowired
    private FiliadoViewRepository filiadoViewRepository;
    @Autowired
    private CandidatoViewRepository candidatoViewRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

     public void writeFiliadosToCsv(PrintWriter writer) throws IOException {
            // Recuperar o usuário autenticado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String role = authentication.getAuthorities().stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .findFirst().orElse(null);

            // Obter a UF e o Município do usuário
            Usuarios usuario = usuarioRepository.findByEmail(username);
            String ufUsuario = (usuario != null) ? usuario.getUf() : null;
            String municipioUsuario = (usuario != null) ? usuario.getMunicipio() : null;
            List<FiliadoView>  filiados;
         // Filtro com base no papel do usuário
            if ("ROLE_ADMINISTRADOR".equals(role) || "ROLE_ADMINISTRADOR_NACIONAL".equals(role) || 
                "ROLE_CONSULTOR_NACIONAL".equals(role) || "ROLE_OPERADOR_NACIONAL".equals(role)) {
              
               filiados = filiadoViewRepository.findAll(); // Busca todos os filiados
            } else if ("ROLE_CONSULTOR_ESTADUAL".equals(role) || "ROLE_OPERADOR_ESTADUAL".equals(role) || 
                    "ADM_ESTADUAL".equals(role)) {
                // Usuário Estadual: Filtra pelo estado do usuário
               filiados = filiadoViewRepository.findByUf(ufUsuario);
            } else if ("ROLE_CONSULTOR_MUNICIPAL".equals(role) || "ROLE_OPERADOR_MUNICIPAL".equals(role)) {
                // Usuário Municipal: Filtra pelo município do usuário
                 filiados = filiadoViewRepository.findByUfAndMunicipio(ufUsuario, municipioUsuario);
            } else {
                // Se o papel não for reconhecido, retorna um resultado vazio ou outro comportamento
                filiados = filiadoViewRepository.findAll(); // Busca todos os filiados
            }
     
        CSVWriter csvWriter = new CSVWriter(writer);
        
        // Definir cabeçalho
        String[] header = { "Nome", "CPF", "Titulo Eleitor", "UF","MUNICIPIO","DATA FILIAÇÃO" };
        csvWriter.writeNext(header);

        // Escrever dados dos filiados
        for (FiliadoView filiado : filiados) {
            String[] data = {  
                filiado.getNome(), 
                filiado.getCpf(),
                filiado.getTitulo(),
                filiado.getUf(),
                filiado.getMunicipio(),
                filiado.getDataFiliacao()
               

               
            };
            csvWriter.writeNext(data);
        }
        System.out.println("Total de filiados encontrados: " + filiados.size());
        csvWriter.close();
    }

    public void writeCandidatosToCsv(PrintWriter writer) throws IOException {
        // Recuperar o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String role = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst().orElse(null);

        // Obter a UF e o Município do usuário
        Usuarios usuario = usuarioRepository.findByEmail(username);
        String ufUsuario = (usuario != null) ? usuario.getUf() : null;
        String municipioUsuario = (usuario != null) ? usuario.getMunicipio() : null;
        List<CandidatoView>  candidatos;
     // Filtro com base no papel do usuário
        if ("ROLE_ADMINISTRADOR".equals(role) || "ROLE_ADMINISTRADOR_NACIONAL".equals(role) || 
            "ROLE_CONSULTOR_NACIONAL".equals(role) || "ROLE_OPERADOR_NACIONAL".equals(role)) {
        candidatos = candidatoViewRepository.findAll(); // Busca todos os filiados
        } else if ("ROLE_CONSULTOR_ESTADUAL".equals(role) || "ROLE_OPERADOR_ESTADUAL".equals(role) || 
                "ADM_ESTADUAL".equals(role)) {
            candidatos = candidatoViewRepository.findByUf(ufUsuario);
        } else if ("ROLE_CONSULTOR_MUNICIPAL".equals(role) || "ROLE_OPERADOR_MUNICIPAL".equals(role)) {
            // Usuário Municipal: Filtra pelo município do usuário
            candidatos = candidatoViewRepository.findByUfAndMunicipio(ufUsuario, municipioUsuario);
        } else {
            // Se o papel não for reconhecido, retorna um resultado vazio ou outro comportamento
            candidatos = candidatoViewRepository.findAll(); // Busca todos os filiados
        }
 
         CSVWriter csvWriter = new CSVWriter(writer);
    
        // Definir cabeçalho
        String[] header = { "Ano", "Nome", "Cargo", "Titulo","CPF","Situação","UF","Municipio","Partido","cargo","Genero","Etnia" };
        csvWriter.writeNext(header);

        // Escrever dados dos candidatos
        for (CandidatoView candidato : candidatos) {
            String[] data = {  
            candidato.getAno_csv(),
            candidato.getNome(),
            candidato.getCargo(),
            candidato.getTitulo(),
            candidato.getCpf(),
            candidato.getSitu_filiacao(),
            candidato.getUf(),
            candidato.getMunicipio(),
            candidato.getPartido(),
            candidato.getCargo(),
            candidato.getGenero(),
            candidato.getEtinia()
    

           

           
        };
        csvWriter.writeNext(data);
    }
    System.out.println("Total de filiados encontrados: " + candidatos.size());
    csvWriter.close();
}
}


