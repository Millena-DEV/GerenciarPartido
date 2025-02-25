package com.ub.org.demo.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ub.org.demo.view.GraficoFiliado;

@Repository
public interface GraficoFiliadoRepository extends JpaRepository<GraficoFiliado,String>, JpaSpecificationExecutor<GraficoFiliado> {

   List<GraficoFiliado> findByAnoFiliacaoAndUfAndGenero(Integer anoFiliacao, String uf, String genero);


}
