package com.ub.org.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ub.org.demo.enums.Funcao;
import com.ub.org.demo.view.Permissao;


public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    Optional<Permissao> findFirstByFuncao(Funcao funcao);
    List<Permissao> findByFuncao(Funcao funcao);


}
