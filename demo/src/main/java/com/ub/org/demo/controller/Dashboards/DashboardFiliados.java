package com.ub.org.demo.controller.Dashboards;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ub.org.demo.enums.Funcao;
import com.ub.org.demo.repository.GraficoFiliadoRepository;
import com.ub.org.demo.repository.UsuarioRepository;
import com.ub.org.demo.service.FiliadoService;
import com.ub.org.demo.view.GraficoFiliado;
import com.ub.org.demo.view.Usuarios;
@Controller
public class DashboardFiliados {

    @Autowired
    private FiliadoService filiadoService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GraficoFiliadoRepository filiado;
    
@GetMapping("/dashboardFiliados")
    public String mostrarIndex(Model model,
                                            @RequestParam(required = false) String uf,
                                            @RequestParam(required = false) String genero
                                            ) {
    
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
        Funcao funcaoUser = (usuario != null) ? usuario.getFuncao() : null;
        String userUF=(usuario!=null)? usuario.getUf() : null;

        model.addAttribute("userFuncao", funcaoUser);
        
        model.addAttribute("email", username);
           
        model.addAttribute("userUF", userUF);
    
        long totalFiliados = 0;
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    // Verifica o perfil do usuário para contar os filiados conforme a UF e/ou Município
    if ("ROLE_ADMINISTRADOR".equals(role) || "ROLE_ADMINISTRADOR_NACIONAL".equals(role) ||"ROLE_CONSULTOR_NACIONAL".equals(role) || "ROLE_OPERADOR_NACIONAL".equals(role) ) {
        // Para admin, conta todos os filiados independentemente da UF e Município
        totalFiliados = filiadoService.contarFiliados();
    } else if ("ROLE_CONSULTOR_ESTADUAL".equals(role) || "ROLE_OPERADOR_ESTADUAL".equals(role) || "ADM_ESTADUAL".equals(role)  ) {
        // Para estadual, conta os filiados da UF
        totalFiliados = filiadoService.contarFiliadosPorUf(ufUsuario);

    } else if ("ROLE_CONSULTOR_MUNICIPAL".equals(role) || "ROLE_OPERADOR_MUNICIPAL".equals(role)) {
        // Para municipal, conta os filiados da UF e do Município
        totalFiliados = filiadoService.contarFiliadosPorUfEMunicipio(ufUsuario, municipioUsuario);
    }
            // Adiciona atributos ao modelo
            model.addAttribute("totalFiliados", totalFiliados);
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
        return "DashboardFiliados"; // Nome do arquivo HTML sem extensão
      
    }
/**************************************************************************************************************************************************** */
@GetMapping("/api/filiado")
@ResponseBody
public List<GraficoFiliado> getFiliados(
        @RequestParam(required = false) Integer anoFiliacao,
        @RequestParam(required = false) String uf,
        @RequestParam(required = false) String genero) {

    
    if (anoFiliacao != null && uf != null && genero != null) {
      filiado.findByAnoFiliacaoAndUfAndGenero(anoFiliacao, uf,genero);
    } else {
        filiado.findAll();
    }
  return  filiado.findAll();

}

}








 