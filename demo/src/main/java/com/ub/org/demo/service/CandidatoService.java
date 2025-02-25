package com.ub.org.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.ub.org.demo.Specifications.CandidatoSpecifications;
import com.ub.org.demo.repository.CandidatoViewRepository;
import com.ub.org.demo.view.CandidatoView;
import jakarta.persistence.EntityNotFoundException;



@Service
public class CandidatoService {

    @Autowired
    private CandidatoViewRepository candidatoViewRepository;

    public long contarCandidatosPorPartidoECargo(String partido, String cargo, Integer ano) {
        System.out.println("Partido: " + partido + ", Cargo: " + cargo + ", Ano: " + ano);
        return candidatoViewRepository.countByPartidoAndCargoAndAno(partido, cargo, ano);
    }

    public long contarCandidatos() {
        return candidatoViewRepository.count(); // Retorna o número total de Candidatos
    }

    public long contarCandidatosPorUf(String uf,String cargo, Integer ano, String partido) {
        // Filtra e conta os filiados pela UF
        return candidatoViewRepository.countByUf(uf,cargo,ano,partido );
    }
    
    public long contarCandidatosPorUfEMunicipio(String uf, String municipio,String cargo, Integer ano, String partido) {
        // Filtra e conta os filiados pela UF e Município
        return candidatoViewRepository.countByUfAndMunicipio(uf, municipio,cargo,ano,partido);
    }

    // Método para filtrar candidatos com base nas especificações
    public Page<CandidatoView> filtrarCandidatos(Integer ano , String partido, String nome, String cpf, String titulo, String uf, String municipio, String cargo, String situ_filiacao, Pageable pageable) {
        Specification<CandidatoView> spec = Specification.where(CandidatoSpecifications.hasNome(nome))
                                                       .and(CandidatoSpecifications.hasTitulo(titulo))
                                                       .and(CandidatoSpecifications.hasUf(uf))
                                                       .and(CandidatoSpecifications.hasMunicipio(municipio))
                                                       .and(CandidatoSpecifications.hasAno(ano))
                                                       .and(CandidatoSpecifications.hasPartido(partido))
                                                       .and(CandidatoSpecifications.hasCargo(cargo))
                                                       .and(CandidatoSpecifications.hasSituacao(situ_filiacao));
                                                       
                                                     

        return candidatoViewRepository.findAll(spec,pageable);
    }
     // Método para buscar todos os Candidatos com paginação
    public Page<CandidatoView> findAll(Pageable pageable) {
        return candidatoViewRepository.findAll(pageable);
    }

public CandidatoView buscarPorId(Long id) {
    return candidatoViewRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Candidato não encontrado com o ID: " + id));
}

  // Método para filtrar candidatos com base nas especificações
  public List<CandidatoView> filtrarCandidato(Integer ano , String partido, String nome, String cpf, String titulo, String uf, String municipio, String cargo, String situ_filiacao) {
    Specification<CandidatoView> spec = Specification.where(CandidatoSpecifications.hasNome(nome))
                                                   .and(CandidatoSpecifications.hasTitulo(titulo))
                                                   .and(CandidatoSpecifications.hasUf(uf))
                                                   .and(CandidatoSpecifications.hasMunicipio(municipio))
                                                   .and(CandidatoSpecifications.hasAno(ano))
                                                   .and(CandidatoSpecifications.hasPartido(partido))
                                                   .and(CandidatoSpecifications.hasCargo(cargo))
                                                   .and(CandidatoSpecifications.hasSituacao(situ_filiacao));
                                                   
                                                 

    return candidatoViewRepository.findAll(spec);
}




}
