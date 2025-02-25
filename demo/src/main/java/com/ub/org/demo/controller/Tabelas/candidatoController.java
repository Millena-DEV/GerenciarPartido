package com.ub.org.demo.controller.Tabelas;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ub.org.demo.dto.CandidatoDTO;
import com.ub.org.demo.repository.CandidatoUniaoRepository;
import com.ub.org.demo.repository.UsuarioRepository;
import com.ub.org.demo.service.CandidatoService;
import com.ub.org.demo.view.CandidatoUniao;
import com.ub.org.demo.view.CandidatoView;
import com.ub.org.demo.view.Usuarios;


@Controller
public class candidatoController {
    @Autowired
    CandidatoUniaoRepository candidatoViewRepository;
    @Autowired
    private CandidatoService  candidatoService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Cacheable(value = "candidatosCache")

    @GetMapping("/candidatos") // Mapeia para a URL /candidatos
    public String getcandidatos(Model model, 
                           @RequestParam(defaultValue = "1") int page, 
                           @RequestParam(defaultValue = "10") int size, 
                           @RequestParam(required = false) String nome,
                           @RequestParam(required = false) String cpf,
                           @RequestParam(required = false) String titulo,
                           @RequestParam(required = false) String uf,
                           @RequestParam(required = false) String municipio,
                           @RequestParam(required = false) Integer ano_filtro,
                           @RequestParam(required = false) String partido,
                           @RequestParam(required = false) String cargo,
                           @RequestParam(required = false)String  situ_filiacao) {

        // Cria um objeto Pageable para a paginação
        Pageable pageable = PageRequest.of(page - 1, size);

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

        // Verifica se há filtros aplicados
        Page<CandidatoView> pagedResult;
      
            // Filtro com base no papel do usuário
            if ("ROLE_ADMINISTRADOR".equals(role) || "ROLE_ADMINISTRADOR_NACIONAL".equals(role) || 
                "ROLE_CONSULTOR_NACIONAL".equals(role) || "ROLE_OPERADOR_NACIONAL".equals(role)) {
                //pagedResult = candidatoService.findAll(pageable);
                pagedResult = candidatoService.filtrarCandidatos(ano_filtro, partido, nome, cpf, titulo, uf, municipio, cargo,situ_filiacao, pageable);
                model.addAttribute("ano_filtro", ano_filtro);
                model.addAttribute("partido", partido);
                model.addAttribute("cargo", cargo);
                model.addAttribute("nome", nome);
                model.addAttribute("titulo", titulo);
                model.addAttribute("cpf", cpf);
                model.addAttribute("uf", uf);
                model.addAttribute("situ_filiacao", situ_filiacao);
            } else if ("ROLE_CONSULTOR_ESTADUAL".equals(role) || "ROLE_OPERADOR_ESTADUAL".equals(role) || 
                    "ADM_ESTADUAL".equals(role)) {
                        pagedResult = candidatoService.filtrarCandidatos(ano_filtro, partido, nome, cpf, titulo, ufUsuario, municipio, cargo,situ_filiacao, pageable);
                        model.addAttribute("ano_filtro", ano_filtro);
                        model.addAttribute("partido", partido);
                        model.addAttribute("cargo", cargo);
                        model.addAttribute("nome", nome);
                        model.addAttribute("titulo", titulo);
                        model.addAttribute("cpf", cpf);
                        model.addAttribute("uf", uf);
                        model.addAttribute("situ_filiacao", situ_filiacao);
            } else if ("ROLE_CONSULTOR_MUNICIPAL".equals(role) || "ROLE_OPERADOR_MUNICIPAL".equals(role)) {
                // Usuário Municipal: Filtra pelo município do usuário
                        pagedResult = candidatoService.filtrarCandidatos(ano_filtro, partido, nome, cpf, titulo, ufUsuario, municipioUsuario, cargo,situ_filiacao, pageable);
                        model.addAttribute("ano_filtro", ano_filtro);
                        model.addAttribute("partido", partido);
                        model.addAttribute("cargo", cargo);
                        model.addAttribute("nome", nome);
                        model.addAttribute("titulo", titulo);
                        model.addAttribute("cpf", cpf);
                        model.addAttribute("uf", uf);
                        model.addAttribute("situ_filiacao", situ_filiacao);
            } else {
                // Se o papel não for reconhecido, retorna um resultado vazio ou outro comportamento
                pagedResult = Page.empty(pageable);
            }
    
                        
        List<CandidatoDTO> candidatos = pagedResult.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        model.addAttribute("candidatos", candidatos);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pagedResult.getTotalPages());
        model.addAttribute("pageSize", size);
        model.addAttribute("ano_filtro", ano_filtro);
        model.addAttribute("partido", partido);
        model.addAttribute("cargo", cargo);
        model.addAttribute("nome", nome);
        model.addAttribute("titulo", titulo);
        model.addAttribute("cpf", cpf);
        model.addAttribute("uf", uf);
        model.addAttribute("situ_filiacao", situ_filiacao);
       
        return "tb_candidatos"; // Nome do arquivo HTML
    }

    private CandidatoDTO convertToDTO(CandidatoView candidato) {
        CandidatoDTO dto = new CandidatoDTO();
        dto.setId(candidato.getId());
        dto.setCpf(candidato.getCpf());
        dto.setNome(candidato.getNome());
        dto.setUf(candidato.getUf());
        dto.setMunicipio(candidato.getMunicipio());
        dto.setTitulo(candidato.getTitulo());
        dto.setCargo(candidato.getCargo());
        dto.setAno_filtro(candidato.getAno_filtro());
        dto.setPartido(candidato.getPartido());
        dto.setSitu_filiacao(candidato.getSitu_filiacao());
        dto.setEtinia(candidato.getEtinia());
        dto.setGenero(candidato.getGenero());
        dto.setDespesa(candidato.getDespesa());
        dto.setSitu_cand(candidato.getSitu_cand());
        dto.setSitu_filiacao(candidato.getSitu_filiacao());
        dto.setNascimento(candidato.getNascimento());
        dto.setQtd_votos(candidato.getQtd_votos());
        return dto;
    }


    
    @GetMapping("/api/candidato")
    @ResponseBody
    public Map<String, Map<String, Long>> getCandidatos(
                                                    @RequestParam( required = false, defaultValue = "2024") Integer ano,
                                                    @RequestParam( required = false, defaultValue = "UNIÃO") String partido,
                                                    @RequestParam( required = false) String cargo,
                                                    @RequestParam( required = false) String UF) {

       String[] cargos = {
            "DEPUTADO DISTRITAL", "DEPUTADO ESTADUAL", "DEPUTADO FEDERAL", "GOVERNADOR", "SENADOR",
            "VICE-GOVERNADOR", "PREFEITO", "VEREADOR", "VICE-PREFEITO", "PRESIDENTE", "VICE-PRESIDENTE"
        };

        // Map para armazenar a contagem de candidatos por UF e cargo
        Map<String, Map<String, Long>> contagemPorUfECargo = new HashMap<>();
      // Contabilizar candidatos por UF e cargo
            for (String cargoAtual : cargos) {
                List<CandidatoUniao> candidatos;
                
                // Se a UF for fornecida, filtre por ela
                if (UF != null && !UF.isEmpty()) {
                    candidatos = candidatoViewRepository.findUf(ano, partido, cargoAtual, UF);
                } else {
                    candidatos = candidatoViewRepository.findByAnoAndPartido(ano, partido, cargoAtual);
                }

                // Para cada candidato, atualizar a contagem por UF e cargo
                for (CandidatoUniao candidato : candidatos) {
                    String uf = candidato.getUf();
                    contagemPorUfECargo.putIfAbsent(uf, new HashMap<>());
                    Map<String, Long> contagemPorCargo = contagemPorUfECargo.get(uf);
                    contagemPorCargo.put(cargoAtual, contagemPorCargo.getOrDefault(cargoAtual, 0L) + 1);
                }
            }
    
        return contagemPorUfECargo;
    }
    
    

     // Método para exibir os detalhes de um candidato
    @GetMapping("/detalhar/candidato/{id}")
    public String documentoCandidato(@PathVariable("id") long id, Model model, @RequestHeader(value = HttpHeaders.REFERER, required = false) String referer) {
        CandidatoView candidato = candidatoService.buscarPorId(id);  
        model.addAttribute("candidato", candidato);  
        // Passa a URL da página anterior para o botão "Voltar"
        model.addAttribute("referer", referer);
        return "detalhar-candidato"; 
    }

}