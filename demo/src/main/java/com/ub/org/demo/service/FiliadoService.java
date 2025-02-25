package com.ub.org.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ub.org.demo.Specifications.FiliadoSpecifications;
import com.ub.org.demo.repository.FiliadoViewRepository;
import com.ub.org.demo.repository.FiliadosRepository;
import com.ub.org.demo.view.Filiados;



@Service
public class FiliadoService {

    @Autowired
    private FiliadosRepository filiadoRepository;

    @Autowired
    private FiliadoViewRepository filiadocount;


    public long contarFiliados() {
        return filiadocount.count(); // Retorna o número total de Filiados
    }
     

    public long contarFiliadosPorUf(String uf) {
        // Filtra e conta os filiados pela UF
        return filiadocount.countByUf(uf);
    }
    
    public long contarFiliadosPorUfEMunicipio(String uf, String municipio) {
        // Filtra e conta os filiados pela UF e Município
        return filiadocount.countByUfAndMunicipio(uf, municipio);
    }

    // Método para filtrar filiados com base nas especificações
    public Page<Filiados> filtrarFiliados(String nome, String cpf, String titulo, String uf, String municipio, String dataFiliacao, Pageable pageable) {
        Specification<Filiados> spec = Specification.where(FiliadoSpecifications.hasNome(nome))
                                                       .and(FiliadoSpecifications.hasCpf(cpf))
                                                       .and(FiliadoSpecifications.hasTitulo(titulo))
                                                       .and(FiliadoSpecifications.hasUf(uf))
                                                       .and(FiliadoSpecifications.hasMunicipio(municipio))
                                                       .and(FiliadoSpecifications.hasDataFiliacao(dataFiliacao));

        return filiadoRepository.findAll(spec,pageable);
      
    }
    // Método para buscar todos os filiados com paginação
    public Page<Filiados> findAll(Pageable pageable) {
        return filiadoRepository.findAll(pageable);
    }
    public Optional<Filiados> buscarPorId(String id) {
        return filiadoRepository.findByTitulo(id);
    }




    
   

   
   


    
    
}
