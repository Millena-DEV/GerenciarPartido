package com.ub.org.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ub.org.demo.view.Documentos;
import com.ub.org.demo.view.FiliacaoInterna;
import com.ub.org.demo.view.Filiados;
@Repository
public interface DocumentoRepository extends JpaRepository<Documentos, Long> {

    List<Documentos> findByFiliado(Filiados filiado);

    Optional<Documentos> findByIdDocumento(Long idDocumento);

    List<Documentos> findByFiliadoInterno(FiliacaoInterna filiado);
   
    

}
