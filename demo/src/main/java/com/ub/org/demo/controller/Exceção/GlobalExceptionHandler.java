package com.ub.org.demo.controller.Exceção;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manipulador de exceção para "TituloJaCadastradoException"
    @ExceptionHandler(TituloJaCadastradoException.class)
    public String handleTituloJaCadastradoException(TituloJaCadastradoException ex, Model model) {
        // Adiciona a mensagem da exceção ao modelo
        model.addAttribute("erroMensagem", ex.getMessage());
        return "erro"; // Redireciona para a página de erro
    }

    // Manipulador genérico para outras exceções
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("erroMensagem", "'Seu Usuário Não há permissões para cadastrar Filiado.'");
        return "erro"; // Redireciona para a página de erro
    }
}
