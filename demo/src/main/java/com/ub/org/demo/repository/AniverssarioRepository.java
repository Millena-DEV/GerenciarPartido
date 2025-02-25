package com.ub.org.demo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ub.org.demo.view.Aniverssario;


@Repository
public interface AniverssarioRepository extends JpaRepository<Aniverssario,Long>,JpaSpecificationExecutor<Aniverssario> {

       @Query("SELECT COUNT(a) FROM Aniverssario a WHERE EXTRACT(MONTH FROM a.dtNasc) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(DAY FROM a.dtNasc) = EXTRACT(DAY FROM CURRENT_DATE)")
       long countAniversariantesDeHoje();
       @Query("SELECT COUNT(a) FROM Aniverssario a WHERE EXTRACT(MONTH FROM a.dtNasc) = EXTRACT(MONTH FROM CURRENT_DATE)")
       long countAniversariantesDoMes();
       
       @Query("SELECT COUNT(a) FROM Aniverssario a WHERE EXTRACT(MONTH FROM a.dtNasc) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(DAY FROM a.dtNasc) = EXTRACT(DAY FROM CURRENT_DATE) AND a.uf = :uf")
       long countAniversariantesDeHojeUF(String uf);

       @Query("SELECT COUNT(a) FROM Aniverssario a WHERE EXTRACT(MONTH FROM a.dtNasc) = EXTRACT(MONTH FROM CURRENT_DATE)  AND a.uf = :uf")
       long countAniversariantesDoMesUF(String uf);
       
       @Query("SELECT COUNT(a) FROM Aniverssario a WHERE EXTRACT(MONTH FROM a.dtNasc) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(DAY FROM a.dtNasc) = EXTRACT(DAY FROM CURRENT_DATE) AND a.uf = :uf AND a.municipio = :municipio")
       long countAniversariantesDeHojeUFMuni(String uf, String municipio);

       @Query("SELECT COUNT(a) FROM Aniverssario a WHERE EXTRACT(MONTH FROM a.dtNasc) = EXTRACT(MONTH FROM CURRENT_DATE)  AND a.uf = :uf AND a.municipio = :municipio")
       long countAniversariantesDoMesUFMuni(String uf, String municipio);
       
       // Consulta para aniversariantes de hoje sem filtros
    @Query("SELECT a FROM Aniverssario a WHERE EXTRACT(MONTH FROM a.dtNasc) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(DAY FROM a.dtNasc) = EXTRACT(DAY FROM CURRENT_DATE)")
    Page<Aniverssario> findAniverssariosDeHoje(Pageable pageable);

    // Consulta para aniversariantes de hoje filtrados por UF
    @Query("SELECT a FROM Aniverssario a WHERE EXTRACT(MONTH FROM a.dtNasc) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(DAY FROM a.dtNasc) = EXTRACT(DAY FROM CURRENT_DATE) AND a.uf = :uf")
    Page<Aniverssario> findAniverssariosPorUf(String uf, Pageable pageable);

    // Consulta para aniversariantes de hoje filtrados por UF e Município
    @Query("SELECT a FROM Aniverssario a WHERE EXTRACT(MONTH FROM a.dtNasc) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(DAY FROM a.dtNasc) = EXTRACT(DAY FROM CURRENT_DATE) AND a.uf = :uf AND a.municipio = :municipio")
    Page<Aniverssario> findAniverssariosPorUfEMunicipio(String uf, String municipio, Pageable pageable);


}