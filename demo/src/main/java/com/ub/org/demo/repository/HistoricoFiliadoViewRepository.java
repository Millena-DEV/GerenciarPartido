package com.ub.org.demo.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ub.org.demo.view.HistoricoFiliados;


@Repository
public interface HistoricoFiliadoViewRepository extends JpaRepository<HistoricoFiliados,String>, JpaSpecificationExecutor<HistoricoFiliados> {

    List<HistoricoFiliados> findByNomeContaining(String nome);
    Optional<HistoricoFiliados> findByTitulo(String titulo);
    List<HistoricoFiliados> findByUf(String uf);
    List<HistoricoFiliados> findByMunicipio(String municipio);
    List<HistoricoFiliados> findByDataFiliacao(String dataFiliacao);


    long countByUf(String uf);
    long countByUfAndMunicipio(String uf, String municipio);


  

    
   

    

   
    
    
    


}
