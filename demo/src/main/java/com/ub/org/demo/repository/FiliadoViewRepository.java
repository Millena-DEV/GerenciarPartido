package com.ub.org.demo.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ub.org.demo.view.FiliadoView;

@Repository
public interface FiliadoViewRepository extends JpaRepository<FiliadoView,String>, JpaSpecificationExecutor<FiliadoView> {

    List<FiliadoView> findByNomeContaining(String nome);
    List<FiliadoView> findByCpf(String cpf);
    Optional<FiliadoView> findByTitulo(String titulo);
    List<FiliadoView> findByUf(String uf);


    long countByUf(String uf);
    long countByUfAndMunicipio(String uf, String municipio);


  

    
   

    

   
    
    
    


}
