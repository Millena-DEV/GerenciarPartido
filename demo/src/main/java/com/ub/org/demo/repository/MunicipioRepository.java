package com.ub.org.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.ub.org.demo.view.municipio;
@Repository
public interface MunicipioRepository extends JpaRepository<municipio, Long> {
List<municipio> findByUf(String uf);

List<municipio> findByUfAndMunicipio(String uf, String municipio);


}
