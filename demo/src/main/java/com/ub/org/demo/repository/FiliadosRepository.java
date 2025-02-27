package com.ub.org.demo.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ub.org.demo.view.Filiados;

@Repository
public interface FiliadosRepository extends JpaRepository<Filiados,String>, JpaSpecificationExecutor<Filiados> {

    List<Filiados> findByNomeContaining(String nome);
    List<Filiados> findByCpf(String cpf);
    Optional<Filiados> findByTitulo(String titulo);
    List<Filiados> findByUf(String uf);
    List<Filiados> findByMunicipio(String municipio);
    List<Filiados> findByDataFiliacao(String dataFiliacao);
    boolean existsByTitulo(String titulo);
    
   
    
    
    


}
