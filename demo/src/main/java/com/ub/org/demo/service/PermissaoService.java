package com.ub.org.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ub.org.demo.enums.Funcao;
import com.ub.org.demo.repository.PermissaoRepository;
import com.ub.org.demo.view.Permissao;

@Service
public class PermissaoService {


      @Autowired
    private PermissaoRepository permissaoRepository;

    public List<Permissao> buscarPermissaoPorFuncao(Funcao funcao) {
        return permissaoRepository.findByFuncao(funcao);
    }

    public void salvarPermissao(Permissao permissao) {
        permissaoRepository.save(permissao);
    }
}
