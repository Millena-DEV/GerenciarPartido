package com.ub.org.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ub.org.demo.view.Usuarios;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
   
    Usuarios findByEmail(String email);

    Usuarios findByTokenRecuperacao(String token);

    boolean existsByEmail(String email);
    

}
