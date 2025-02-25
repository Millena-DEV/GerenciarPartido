package com.ub.org.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ub.org.demo.Specifications.HistoricoFiliadoSpecifications;
import com.ub.org.demo.repository.HistoricoFiliadoViewRepository;
import com.ub.org.demo.view.HistoricoFiliados;

@Service
public class HistoricoFiliadoService {


    @Autowired
    private HistoricoFiliadoViewRepository filiadocount;


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
    public Page<HistoricoFiliados> filtrarFiliados(String nome, String titulo, String uf, String municipio, String dataFiliacao, Pageable pageable) {
        Specification<HistoricoFiliados> spec = Specification.where(HistoricoFiliadoSpecifications.hasNome(nome))
                                                       .and(HistoricoFiliadoSpecifications.hasTitulo(titulo))
                                                       .and(HistoricoFiliadoSpecifications.hasUf(uf))
                                                       .and(HistoricoFiliadoSpecifications.hasMunicipio(municipio))
                                                       .and(HistoricoFiliadoSpecifications.hasDataFiliacao(dataFiliacao));

        return filiadocount.findAll(spec,pageable);
      
    }
    // Método para buscar todos os filiados com paginação
    public Page<HistoricoFiliados> findAll(Pageable pageable) {
        return filiadocount.findAll(pageable);
    }
    public Optional<HistoricoFiliados> buscarPorId(String id) {
        return filiadocount.findByTitulo(id);
    }




    
   

   
   


    
    
}
