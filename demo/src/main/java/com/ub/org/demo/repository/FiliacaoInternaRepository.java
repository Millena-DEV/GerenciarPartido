package com.ub.org.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.ub.org.demo.view.FiliacaoInterna;



@Repository
public interface FiliacaoInternaRepository extends JpaRepository<FiliacaoInterna,Long>, JpaSpecificationExecutor<FiliacaoInterna> {

 
    Optional<FiliacaoInterna> findByTitulo(String titulo);
    long countByUf(String uf);
    long countByUfAndMunicipio(String uf, String municipio);
    List<FiliacaoInterna> findByNomeContaining(String nome);
    List<FiliacaoInterna> findByCpf(String cpf);
    List<FiliacaoInterna> findByUf(String uf);
    List<FiliacaoInterna> findByMunicipio(String municipio);
    List<FiliacaoInterna> findByDataFiliacao(String dataFiliacao);



  
   
    
    
    

    

}
