package com.ub.org.demo.controller.Cadastros;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ub.org.demo.service.FiliacaoInternaService;
import com.ub.org.demo.view.FiliacaoInterna;

@Controller
public class NovoFiliadoController {
    @Autowired
    private FiliacaoInternaService filiadoService;
  @Cacheable("cachePagSalvarFiliadoNovo")
    @PostMapping("/salvarFiliadoComDocumentos")
    public ModelAndView salvarFiliadoComDocumentos(
        @ModelAttribute FiliacaoInterna filiado, 
        @RequestParam String titulo,
        @RequestParam String bairro,
        @RequestParam String cep,
        @RequestParam String complemento,
        @RequestParam String cpf,
        @RequestParam String dataNascimento,
        @RequestParam String email,
        @RequestParam String genero,
        @RequestParam String logradouro,
        @RequestParam String municipio,
        @RequestParam String nome,
        @RequestParam String nomeMae,
        @RequestParam String nomePai,
        @RequestParam String numero,
        @RequestParam String celular1,
        @RequestParam String celular2,
        @RequestParam String secao,
        @RequestParam String tipo_logradouro,
        @RequestParam String uf,
        @RequestParam String zona,
        @RequestParam(required = false) MultipartFile foto,
        @RequestParam (required = false)  MultipartFile cpfFile,
        @RequestParam (required = false) MultipartFile tituloDoc,
        @RequestParam (required = false) MultipartFile ficha,
        @RequestParam (required = false) MultipartFile ficha_deslifiacao) {
        try {
            filiadoService.salvarFiliadoEArquivos(titulo, bairro, cep, complemento, cpf, dataNascimento, email, genero, 
                                                  logradouro, municipio, nome, nomeMae, nomePai, numero, celular1, celular2, 
                                                  secao, tipo_logradouro, uf, zona, foto, cpfFile, tituloDoc, ficha, ficha_deslifiacao);
           // Redirecionar para a página "index.html"
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("FiliacaoInterna"); // Nome do arquivo HTML (não precisa da extensão .html)
            return modelAndView;
        } catch (IOException e) {
            // Em caso de erro, redirecionar para uma página de erro
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("erro");  // Nome do arquivo HTML de erro (erro.html)
            return modelAndView;  // Retorna o ModelAndView com a página de erro
        }
    }
    

    @GetMapping("/novo-filiado")
    public String showRegistrationForm(Model model) {
        model.addAttribute("filiado", new FiliacaoInterna());  // Adicionando um novo objeto FiliacaoInterna ao modelo
        return "filiacao"; // Retorna a página de cadastro
    }
    


}
