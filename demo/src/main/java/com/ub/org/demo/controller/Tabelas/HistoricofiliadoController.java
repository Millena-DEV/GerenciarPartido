package com.ub.org.demo.controller.Tabelas;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.ub.org.demo.dto.FiliadoDTO;
import com.ub.org.demo.repository.HistoricoFiliadoViewRepository;
import com.ub.org.demo.repository.UsuarioRepository;
import com.ub.org.demo.service.HistoricoFiliadoService;
import com.ub.org.demo.view.HistoricoFiliados;
import com.ub.org.demo.view.Usuarios;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
@Controller
public class HistoricofiliadoController {

    @Autowired
    HistoricoFiliadoService filiadoService;
    @Autowired 
    HistoricoFiliadoViewRepository filiadoViewRepository;
    @Autowired 
    UsuarioRepository usuarioRepository;


     @Cacheable("cachePagHistoricoFiliados")
     @GetMapping("/historicofiliados") // Mapeia para a URL /filiados
    public String getFiliados(Model model, 
                           @RequestParam(defaultValue = "1") int page, 
                           @RequestParam(defaultValue = "25") int size, 
                           @RequestParam(required = false) String nome,
                           @RequestParam(required = false) String titulo,
                           @RequestParam(required = false) String uf,
                           @RequestParam(required = false) String municipio,
                           @RequestParam(required = false) String dataFiliacao) {

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
        Page<HistoricoFiliados> pagedResult;
       
            // Filtro com base no papel do usuário
            if ("ROLE_ADMINISTRADOR".equals(role) || "ROLE_ADMINISTRADOR_NACIONAL".equals(role) || 
                "ROLE_CONSULTOR_NACIONAL".equals(role) || "ROLE_OPERADOR_NACIONAL".equals(role)) {
                // Usuário Nacional: Pode ver todos os filiados
               // pagedResult = filiadoService.findAll(pageable);
               pagedResult = filiadoService.filtrarFiliados(nome, titulo, uf, municipio, dataFiliacao, pageable);
            } else if ("ROLE_CONSULTOR_ESTADUAL".equals(role) || "ROLE_OPERADOR_ESTADUAL".equals(role) || 
                    "ADM_ESTADUAL".equals(role)) {
                // Usuário Estadual: Filtra pelo estado do usuário
                pagedResult = filiadoService.filtrarFiliados(nome,  titulo, ufUsuario, municipio, dataFiliacao, pageable);
            } else if ("ROLE_CONSULTOR_MUNICIPAL".equals(role) || "ROLE_OPERADOR_MUNICIPAL".equals(role)) {
                // Usuário Municipal: Filtra pelo município do usuário
                pagedResult = filiadoService.filtrarFiliados(nome, titulo, ufUsuario, municipioUsuario, dataFiliacao, pageable);
            } else {
                // Se o papel não for reconhecido, retorna um resultado vazio ou outro comportamento
                pagedResult = Page.empty(pageable);
            }
        

        List<FiliadoDTO> filiados = pagedResult.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        // Adiciona os filtros ao modelo para exibir na interface
        model.addAttribute("nome", nome);
        model.addAttribute("uf", uf);
        model.addAttribute("municipio", municipio);
        model.addAttribute("titulo", titulo);
  
        model.addAttribute("filiados", filiados);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pagedResult.getTotalPages());
        model.addAttribute("pageSize", size);

         long totalFiliados = 0;
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
      NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
      String totalFiliadosFormatted = numberFormat.format(totalFiliados);
     // Adiciona atributos ao modelo
     model.addAttribute("totalFiliados", totalFiliadosFormatted);

        return "tb_historico_filiados"; // Nome do arquivo HTML
    }

    private FiliadoDTO convertToDTO(HistoricoFiliados filiado) {
        FiliadoDTO dto = new FiliadoDTO();
        dto.setNome(filiado.getNome());
        dto.setTitulo(filiado.getTitulo());
        dto.setUf(filiado.getUf());
        dto.setMunicipio(filiado.getMunicipio());
        dto.setDataFiliacao(filiado.getDataFiliacao());
        dto.setGenero(filiado.getGenero());
        dto.setSituacao(filiado.getSituacao());
        dto.setMotivo_desfiliacao(filiado.getMotivo_desfiliacao());
        dto.setData_atualizacao(filiado.getData_insercao());
        return dto;
    }

    /*@GetMapping("/api/filiados")
    @ResponseBody
    public List<HistoricoFiliados> getFiliados() {
        return filiadoViewRepository.findAll();
    }
*/
    // Método para exibir os detalhes de um filiado
    /*@GetMapping("/detalhar/{id}")
    public String detalharFiliado(@PathVariable("id") String id, Model model) {
        HistoricoFiliados filiado = filiadoService.buscarPorId(id)
        .orElseThrow(() -> new IllegalArgumentException("Filiado não encontrado com ID: " + id)); // Suponha que esse método retorne um filiado por ID
        model.addAttribute("filiado", filiado);  // Passa o objeto filiado para a página
        return "detalhar-filiado";  // Nome da view onde você vai exibir os detalhes
    }


     // Método para exibir os detalhes de um filiado
     @GetMapping("/documentos/{id}")
     public String documentoFiliado(@PathVariable("id") String id, Model model) {
        HistoricoFiliados filiado = filiadoService.buscarPorId(id)
        .orElseThrow(() -> new IllegalArgumentException("Filiado não encontrado com ID: " + id));
         model.addAttribute("filiado", filiado);  // Passa o objeto filiado para a página
         return "documentos-filiado";  // Nome da view onde você vai exibir os detalhes
     }*/
    }