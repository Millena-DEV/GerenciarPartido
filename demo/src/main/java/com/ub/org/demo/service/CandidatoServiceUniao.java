package com.ub.org.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ub.org.demo.repository.CandidatoUniaoRepository;
import com.ub.org.demo.view.CandidatoUniao;
import jakarta.persistence.EntityNotFoundException;



@Service
public class CandidatoServiceUniao {

    @Autowired
    private CandidatoUniaoRepository candidatoViewRepository;

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


     // Método para buscar todos os Candidatos com paginação
    public Page<CandidatoUniao> findAll(Pageable pageable) {
        return candidatoViewRepository.findAll(pageable);
    }

public CandidatoUniao buscarPorId(Long id) {
    return candidatoViewRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Candidato não encontrado com o ID: " + id));
}





}
