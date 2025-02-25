package com.ub.org.demo.controller.Dashboards;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ub.org.demo.enums.Funcao;
import com.ub.org.demo.repository.CandidatoUniaoRepository;
import com.ub.org.demo.repository.UsuarioRepository;
import com.ub.org.demo.service.CandidatoServiceUniao;
import com.ub.org.demo.service.FiliadoService;
import com.ub.org.demo.view.CandidatoUniao;
import com.ub.org.demo.view.Usuarios;
@Controller
public class DashboardCandidatos {

    @Autowired
    private FiliadoService filiadoService;
    @Autowired
    private CandidatoServiceUniao candidatoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CandidatoUniaoRepository candidatoUniao;
     
 
@Cacheable(value = "candidatosDashboard")
@GetMapping("/dashboardCandidatos")
    public String mostrarIndex(Model model, @RequestParam(required = false) String partido,
            @RequestParam(required = false) Integer ano) {
    
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
    
        long totalFiliados = 0;
    
        // Definindo valores padrão se não forem fornecidos
        if (partido == null) {
            partido = "UNIÃO"; // Valor padrão
        }
        if (ano == null) {
            ano = 2024; // Valor padrão
        }
        // Definir valores padrão
        String[] partidos = {
                "UNIÃO", "AGIR", "AVANTE", "CIDADANIA", "DC", "DEM", "MDB", "MOBILIZA", "NOVO", "PATRIOTA",
                "PC do B", "PCB", "PDT", "PHS", "PL", "PMB", "PMDB", "PMN", "PODE", "PP", "PPL", "PPS", "PR",
                "PRB", "PRD", "PROS", "PRP", "PRTB", "PSB", "PSC", "PSD", "PSDB", "PSDC", "PSL", "PSOL", "PT",
                "PT do B", "PTB", "PTC", "PTN", "PV", "REDE", "REPUBLICANOS", "SD", "SOLIDARIEDADE"
        };
    
        String[] anos = {
                "2024", "2022", "2020", "2018", "2016"
        };
    
        String[] cargos = {
                "DEPUTADO DISTRITAL", "DEPUTADO ESTADUAL", "DEPUTADO FEDERAL", "GOVERNADOR", "SENADOR",
                "VICE-GOVERNADOR", "PREFEITO", "VEREADOR", "VICE-PREFEITO", "PRESIDENTE", "VICE-PRESIDENTE"
        };
    
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
        // Lista para armazenar contagens de candidatos
        List<Long> contagemCandidatos = new java.util.ArrayList<>();
        Map<String, Long> contagemPorCargo = new HashMap<>();
        // Admin: Conta todos os filiados
        if ("ROLE_ADMINISTRADOR".equals(role) || "ROLE_ADMINISTRADOR_NACIONAL".equals(role) || "ROLE_CONSULTOR_NACIONAL".equals(role) || "ROLE_OPERADOR_NACIONAL".equals(role)) {
            for (String cargo : cargos) {
                long contagem = candidatoService.contarCandidatosPorPartidoECargo(partido, cargo, ano);
                contagemCandidatos.add(contagem);
                contagemPorCargo.put(cargo, contagem);

                System.out.println("vereador: " + cargo +contagem);
            }
        } 
        // Consultor ou Operador Estadual: Conta filiados por UF
        else if ("ROLE_CONSULTOR_ESTADUAL".equals(role) || "ROLE_OPERADOR_ESTADUAL".equals(role) || "ADM_ESTADUAL".equals(role)) {
            System.out.println("UF: " + ufUsuario);
            System.out.println("Ano: " + ano);
            System.out.println("Partido: " + partido);
    
            
            for (String cargo : cargos) {
                long contagem = candidatoService.contarCandidatosPorUf(ufUsuario, cargo, ano,partido); // Adicionei o filtro de UF aqui
                contagemCandidatos.add(contagem);
                contagemPorCargo.put(cargo, contagem);
            }
        } 
        // Consultor ou Operador Municipal: Conta filiados por UF e Município
        else if ("ROLE_CONSULTOR_MUNICIPAL".equals(role) || "ROLE_OPERADOR_MUNICIPAL".equals(role)) {
            for (String cargo : cargos) {
                long contagem = candidatoService.contarCandidatosPorUfEMunicipio(ufUsuario, municipioUsuario, cargo, ano,partido); // Adicionei o filtro de UF e Município aqui
                contagemCandidatos.add(contagem);
                contagemPorCargo.put(cargo, contagem);
            }
        }
    
        // Adiciona os dados ao modelo para a visualização
        model.addAttribute("totalDeputadoDistrital", contagemPorCargo.get("DEPUTADO DISTRITAL"));
        model.addAttribute("totalDeputadoEstadual", contagemPorCargo.get("DEPUTADO ESTADUAL"));
        model.addAttribute("totalDeputadoFederal", contagemPorCargo.get("DEPUTADO FEDERAL"));
        model.addAttribute("totalGovernador", contagemPorCargo.get("GOVERNADOR"));
        model.addAttribute("totalSenador", contagemPorCargo.get("SENADOR"));
        model.addAttribute("totalViceGovernador", contagemPorCargo.get("VICE-GOVERNADOR"));
        model.addAttribute("totalPrefeitos", contagemPorCargo.get("PREFEITO"));
        model.addAttribute("totalVereador", contagemPorCargo.get("VEREADOR"));
        model.addAttribute("totalVicePrefeitos", contagemPorCargo.get("VICE-PREFEITO"));
        model.addAttribute("totalPresidentes", contagemPorCargo.get("PRESIDENTE"));
        model.addAttribute("totalVicePresidentes", contagemPorCargo.get("VICE-PRESIDENTE"));

    
        model.addAttribute("ano", ano);
        model.addAttribute("partido", partido);
        model.addAttribute("contagemPorCargo", contagemPorCargo);
        model.addAttribute("cargos", cargos);
        model.addAttribute("contagemCandidatos", contagemCandidatos);
        return "DashboardCandidatos"; // Nome do arquivo HTML sem extensão
      
    }
/**************************************************************************************************************************************************** */
  @GetMapping("/api/usuario/logado")
@ResponseBody
public Map<String, Object> getUsuarioLogado() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    String role = authentication.getAuthorities().stream()
            .map(grantedAuthority -> grantedAuthority.getAuthority())
            .findFirst().orElse(null);

    // Obter a UF e a Função do usuário
    Usuarios usuario = usuarioRepository.findByEmail(username);
    String ufUsuario = (usuario != null) ? usuario.getUf() : null;
    Funcao funcao = (usuario != null) ? usuario.getFuncao() : null;

    // Caso o usuário não tenha esses roles, pode retornar informações gerais
    Map<String, Object> usuarioInfo = new HashMap<>();
    usuarioInfo.put("uf", ufUsuario);
    usuarioInfo.put("funcao", funcao);
    return usuarioInfo;
}
/*************************************************************************************************************** */

  @GetMapping("/api/candidatos")
    @ResponseBody
    public List<CandidatoUniao> getCandidato (@RequestParam(required = false,defaultValue = "2024") Integer ano,
                                                @RequestParam(required = false) String partido,
                                                @RequestParam(required = false) String cargo,
                                                @RequestParam(required = false) String uf,
                                                @RequestParam(required = false) String municipio,
                                                @RequestParam(required = false) String genero,
                                                 @RequestParam(required = false) String etnia
                                                ) {
      
       // Filtrar com base nos parâmetros
    if (ano != null && partido != null && cargo != null && uf != null && municipio != null && genero != null && etnia != null) {
      return  candidatoUniao.findByAnoAndPartidoAndCargoAndUfAndmunicipioAndgeneroAndetnia(ano, partido, cargo, uf,municipio,genero,etnia);
    }

    // Retorna todos os candidatos se não houver filtros
    return  candidatoUniao.findAll();
    
}





}



 