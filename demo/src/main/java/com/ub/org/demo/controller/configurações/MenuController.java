package com.ub.org.demo.controller.configurações;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.ub.org.demo.enums.Funcao;
import com.ub.org.demo.repository.CandidatoViewRepository;
import com.ub.org.demo.repository.FiliadoViewRepository;
import com.ub.org.demo.repository.UsuarioRepository;
import com.ub.org.demo.service.AniverssarioService;
import com.ub.org.demo.view.Aniverssario;
import com.ub.org.demo.view.CandidatoView;
import com.ub.org.demo.view.FiliadoView;
import com.ub.org.demo.view.Usuarios;


@Controller // Certifique-se de que o controlador esteja anotado com @Controller
public class MenuController { // Renomeado para começar com letra maiúscula
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AniverssarioService aniverssarioService;
    @Autowired
    FiliadoViewRepository filiadoView;
    @Autowired
     CandidatoViewRepository candidatoView;
        @Cacheable("cachePagInicial")
        @GetMapping("/index")
        public String showIndexForm(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") 
        int size, Model model,@RequestParam(required = false) String uf, @RequestParam(required = false) String municipio, Pageable pageable)  {
             // Recuperar o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String role = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst().orElse(null);
    
        // Obter a UF e o Município do usuário
        Usuarios usuario = usuarioRepository.findByEmail(username);
        String ufUsuario = (usuario != null) ? usuario.getUf() : null;
        String municipioUsuario = (usuario != null) ? usuario.getMunicipio() : null;
        String nomeUsuario = (usuario != null) ? usuario.getNome() : null;
        Funcao funcaoUser = (usuario != null) ? usuario.getFuncao() : null;
        String sobrenome =(usuario != null) ? usuario.getSobrenome() : null;
        String userUF=(usuario!=null)? usuario.getUf() : null;

        model.addAttribute("userLogado", nomeUsuario);
        model.addAttribute("sobrenome", sobrenome);
        
        model.addAttribute("userFuncao", funcaoUser);
        
        model.addAttribute("email", username);
           
        model.addAttribute("userUF", userUF);

         FiliadoView filiado = filiadoView.findAll().stream().findFirst().orElse(null);
         CandidatoView candidato = candidatoView.findAll().stream().findFirst().orElse(null);

          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
         String dataFiliadoFormatted = sdf.format(filiado.getData_insercao());
         String dataCandidatoFormatted = sdf.format(candidato.getData_insercao());
       
            // Adicionando a data de inserção ao modelo
            model.addAttribute("filiado", dataFiliadoFormatted);
            model.addAttribute("candidato", dataCandidatoFormatted);
       


        /*********************************************************************************** */
        long aniverssariantes = 0;
        long  aniverssariantesMes = 0;
          // Verifica o perfil do usuário para contar os filiados conforme a UF e/ou Município
         if ("ROLE_ADMINISTRADOR".equals(role) || "ROLE_ADMINISTRADOR_NACIONAL".equals(role) ||
            "ROLE_CONSULTOR_NACIONAL".equals(role) || "ROLE_OPERADOR_NACIONAL".equals(role)) {
            aniverssariantes = aniverssarioService.countAniversariantesDeHoje();
            aniverssariantesMes = aniverssarioService.countAniversariantesDoMes();
        } else if ("ROLE_CONSULTOR_ESTADUAL".equals(role) || "ROLE_OPERADOR_ESTADUAL".equals(role) || "ADM_ESTADUAL".equals(role)) {
            aniverssariantes = aniverssarioService.countAniversariantesDeHojeUF(ufUsuario);
            aniverssariantesMes = aniverssarioService.countAniversariantesDoMesUF(ufUsuario);
        } else if ("ROLE_CONSULTOR_MUNICIPAL".equals(role) || "ROLE_OPERADOR_MUNICIPAL".equals(role)) {
            aniverssariantes = aniverssarioService.countAniversariantesDeHojeUFMuni(ufUsuario, municipioUsuario);
            aniverssariantesMes = aniverssarioService.countAniversariantesDoMesUFMuni(ufUsuario, municipioUsuario);
        }
        model.addAttribute("aniverssariantes", aniverssariantes);
        model.addAttribute("aniverssariantesMes", aniverssariantesMes);

        /*********************************************************************************** */

        // Variável para armazenar o resultado da busca
        Page<Aniverssario> pagedResult = null;
       // Verifica o perfil do usuário para listar os aniversariantes conforme a UF e/ou Município
        if ("ROLE_ADMINISTRADOR".equals(role) || "ROLE_ADMINISTRADOR_NACIONAL".equals(role) ||
            "ROLE_CONSULTOR_NACIONAL".equals(role) || "ROLE_OPERADOR_NACIONAL".equals(role)) {
            pagedResult = aniverssarioService.findAniverssariosDeHojeComFiltro(pageable);
        } else if ("ROLE_CONSULTOR_ESTADUAL".equals(role) || "ROLE_OPERADOR_ESTADUAL".equals(role) || "ADM_ESTADUAL".equals(role)) {
            pagedResult = aniverssarioService.findAniverssariosDeHojeComFiltro(ufUsuario, pageable);
        } else if ("ROLE_CONSULTOR_MUNICIPAL".equals(role) || "ROLE_OPERADOR_MUNICIPAL".equals(role)) {
            pagedResult = aniverssarioService.findAniverssariosDeHojeComFiltro(ufUsuario, municipioUsuario, pageable);
        } else {
            pagedResult = aniverssarioService.findAniverssariosDeHojeComFiltro(pageable);
        }
        model.addAttribute("aniverssariantes", aniverssariantes);
     
         model.addAttribute("aniverssarios", pagedResult.getContent());
         model.addAttribute("currentPage", page);
         model.addAttribute("totalPages", pagedResult.getTotalPages());
         model.addAttribute("pageSize", size);

         

            return "index"; // Retorna a página de cadastro
        }

       
}

