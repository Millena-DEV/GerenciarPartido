package com.ub.org.demo.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ub.org.demo.Specifications.FiliadoInternoSpecifications;
import com.ub.org.demo.repository.FiliacaoInternaRepository;
import com.ub.org.demo.view.FiliacaoInterna;



@Service
public class FiliadoInternoService {

    @Autowired
    private FiliacaoInternaRepository filiadoRepository;


    public long contarFiliados() {
        return filiadoRepository.count(); // Retorna o número total de Filiados
    }
     

    public long contarFiliadosPorUf(String uf) {
        // Filtra e conta os filiados pela UF
        return filiadoRepository.countByUf(uf);
    }
    
    public long contarFiliadosPorUfEMunicipio(String uf, String municipio) {
        // Filtra e conta os filiados pela UF e Município
        return filiadoRepository.countByUfAndMunicipio(uf, municipio);
    }



    
    // Método para filtrar filiados com base nas especificações
    public Page<FiliacaoInterna> filtrarFiliados(String nome, String cpf, String titulo, String uf, String municipio, String dataFiliacao, Pageable pageable) {
        Specification<FiliacaoInterna> spec = Specification.where(FiliadoInternoSpecifications.hasNome(nome))
                                                       .and(FiliadoInternoSpecifications.hasCpf(cpf))
                                                       .and(FiliadoInternoSpecifications.hasTitulo(titulo))
                                                       .and(FiliadoInternoSpecifications.hasUf(uf))
                                                       .and(FiliadoInternoSpecifications.hasMunicipio(municipio))
                                                       .and(FiliadoInternoSpecifications.hasDataFiliacao(dataFiliacao));

        return filiadoRepository.findAll(spec,pageable);
      
    }
    // Método para buscar todos os filiados com paginação
    public Page<FiliacaoInterna> findAll(Pageable pageable) {
        return filiadoRepository.findAll(pageable);
    }
    public Optional<FiliacaoInterna> buscarPorId(String id) {
        return filiadoRepository.findByTitulo(id);
    }




    
   

   
   


    
    
}
