package com.ub.org.demo.controller.configurações;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class ErrorController {
    @GetMapping("/acesso-negado")
    public String acessoNegado(Model model) {
        return "acesso-negado";  // A página que contém o modal
    }

}
