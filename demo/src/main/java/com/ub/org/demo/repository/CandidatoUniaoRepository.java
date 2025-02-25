package com.ub.org.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ub.org.demo.view.CandidatoUniao;


@Repository
public interface CandidatoUniaoRepository extends JpaRepository<CandidatoUniao,Long>,JpaSpecificationExecutor<CandidatoUniao> {
    
        @Query("SELECT COUNT(c) FROM CandidatoUniao c WHERE c.partido = ?1 AND c.cargo = ?2 "
        + "AND ("
        + "   (CAST(c.ano AS integer) + CASE WHEN UPPER(TRIM(c.cargo)) = 'SENADOR' THEN 8 ELSE 4 END) > ?3 "
        + "   OR "
        + "   (CAST(c.ano AS integer) = ?3 AND UPPER(TRIM(c.cargo)) IS NOT NULL) "
        + ")")

        long countByPartidoAndCargoAndAno(String partido, String cargo, Integer ano);

      
        @Query("SELECT COUNT(c) FROM CandidatoUniao c WHERE c.uf = ?1 AND c.cargo = ?2 AND c.partido = ?4 "
        + " AND ("
        + "   (CAST(c.ano AS integer) + CASE WHEN UPPER(TRIM(c.cargo)) = 'SENADOR' THEN 8 ELSE 4 END) > ?3 "
        + "   OR "
        + "   (CAST(c.ano AS integer) = ?3 AND UPPER(TRIM(c.cargo)) IS NOT NULL) "
        + ")")
        long countByUf(String uf, String cargo, Integer ano, String partido);



        @Query("SELECT COUNT(c) FROM CandidatoUniao c WHERE c.uf = ?1 AND c.municipio = ?2 AND c.cargo = ?3 AND c.partido = ?5  "
        + "AND ("
        + "   (CAST(c.ano AS integer) + CASE WHEN UPPER(TRIM(c.cargo)) = 'SENADOR' THEN 8 ELSE 4 END) > ?4 "
        + "   OR "
        + "   (CAST(c.ano AS integer) = ?4 AND UPPER(TRIM(c.cargo)) IS NOT NULL) "
        + ")")
        long countByUfAndMunicipio(String uf, String municipio, String cargo, Integer ano, String partido);


        @Query("SELECT COUNT(c) FROM CandidatoUniao c WHERE c.partido = ?2 and c.cargo = ?3"
        + "AND ("
        + "   (CAST(c.ano AS integer) + CASE WHEN UPPER(TRIM(c.cargo)) = 'SENADOR' THEN 8 ELSE 4 END) > ?1 "
        + "   OR "
        + "   (CAST(c.ano AS integer) = ?1 AND UPPER(TRIM(c.cargo)) IS NOT NULL) "
        + ")")
        List<CandidatoUniao> findByAnoAndPartido(Integer ano,String partido,String cargo);


        @Query("SELECT c FROM CandidatoUniao c WHERE c.partido = ?2 and c.cargo = ?3 and c.uf= ?4 and c.municipio=?5 and c.genero =?6 and c.etnia =?7 "
        + "AND ("
        + "   (CAST(c.ano AS integer) + CASE WHEN UPPER(TRIM(c.cargo)) = 'SENADOR' THEN 8 ELSE 4 END) > ?1 "
        + "   OR "
        + "   (CAST(c.ano AS integer) = ?1 AND UPPER(TRIM(c.cargo)) IS NOT NULL) "
        + ")")
        List<CandidatoUniao> findByAnoAndPartidoAndCargoAndUfAndmunicipioAndgeneroAndetnia(Integer ano, String partido, String cargo,String uf,String municipio,String genero,String etnia);


        @Query("SELECT COUNT(c) FROM CandidatoUniao c WHERE c.partido = ?2"
        + "AND ("
        + "   (CAST(c.ano AS integer) + CASE WHEN UPPER(TRIM(c.cargo)) = 'SENADOR' THEN 8 ELSE 4 END) > ?1 "
        + "   OR "
        + "   (CAST(c.ano AS integer) = ?1 AND UPPER(TRIM(c.cargo)) IS NOT NULL) "
        + ")")
        List<CandidatoUniao> findByAnoAndPartido(Integer ano, String partido);

          @Query("SELECT COUNT(c) FROM CandidatoView c WHERE c.partido = ?2 and c.cargo = ?3 and c.uf=?4"
        + "AND ("
        + "   (CAST(c.ano AS integer) + CASE WHEN UPPER(TRIM(c.cargo)) = 'SENADOR' THEN 8 ELSE 4 END) > ?1 "
        + "   OR "
        + "   (CAST(c.ano AS integer) = ?1 AND UPPER(TRIM(c.cargo)) IS NOT NULL) "
        + ")")
        List<CandidatoUniao> findUf(Integer ano, String partido, String cargo,String uf);

        @Query("SELECT COUNT(c) FROM CandidatoUniao c WHERE c.partido = ?2 and c.cargo = ?3 and c.uf=?4 and c.municipio =?5"
        + "AND ("
        + "   (CAST(c.ano AS integer) + CASE WHEN UPPER(TRIM(c.cargo)) = 'SENADOR' THEN 8 ELSE 4 END) > ?1 "
        + "   OR "
        + "   (CAST(c.ano AS integer) = ?1 AND UPPER(TRIM(c.cargo)) IS NOT NULL) "
        + ")")
        List<CandidatoUniao> findUFMuni(Integer ano, String partido, String cargo,String uf,String municipio);

            List<CandidatoUniao> findByNomeContaining(String nome);
            List<CandidatoUniao> findByCpf(String cpf);
            List<CandidatoUniao> findByTitulo(String titulo);
            //List<CandidatoUniao> findByUf(String uf); 
            List<CandidatoUniao> findByMunicipio(String municipio);
            List<CandidatoUniao> findByPartido(String partido);
            List<CandidatoUniao> findByCargo(String cargo);
            List<CandidatoUniao> findByAno(Integer ano_filtro);


          

            
    }