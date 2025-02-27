package com.ub.org.demo.controller.Tabelas;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.ub.org.demo.dto.FiliadoDTO;
import com.ub.org.demo.repository.FiliadosRepository;
import com.ub.org.demo.repository.UsuarioRepository;
import com.ub.org.demo.service.FiliadoInternoService;
import com.ub.org.demo.view.FiliacaoInterna;
import com.ub.org.demo.view.Filiados;
import com.ub.org.demo.view.Usuarios;

import java.util.List;
import java.util.stream.Collectors;
@Controller
public class FiliadoInternoController {

    @Autowired
    private FiliadoInternoService filiadoService;
    @Autowired 
    FiliadosRepository filiadoViewRepository;
    @Autowired 
    UsuarioRepository usuarioRepository;

      @Cacheable("cachePagFiliado-Interno")
     @GetMapping("/filiado-Interno") // Mapeia para a URL /filiados
    public String getFiliados(Model model, 
                           @RequestParam(defaultValue = "1") int page, 
                           @RequestParam(defaultValue = "25") int size, 
                           @RequestParam(required = false) String nome,
                           @RequestParam(required = false) String cpf,
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
        Page<FiliacaoInterna> pagedResult;
       
            // Filtro com base no papel do usuário
            if ("ROLE_ADMINISTRADOR".equals(role) || "ROLE_ADMINISTRADOR_NACIONAL".equals(role) || 
                "ROLE_CONSULTOR_NACIONAL".equals(role) || "ROLE_OPERADOR_NACIONAL".equals(role)) {
                // Usuário Nacional: Pode ver todos os filiados
               // pagedResult = filiadoService.findAll(pageable);
               pagedResult = filiadoService.filtrarFiliados(nome, cpf, titulo, uf, municipio, dataFiliacao, pageable);
            } else if ("ROLE_CONSULTOR_ESTADUAL".equals(role) || "ROLE_OPERADOR_ESTADUAL".equals(role) || 
                    "ADM_ESTADUAL".equals(role)) {
                // Usuário Estadual: Filtra pelo estado do usuário
                pagedResult = filiadoService.filtrarFiliados(nome, cpf, titulo, ufUsuario, municipio, dataFiliacao, pageable);
            } else if ("ROLE_CONSULTOR_MUNICIPAL".equals(role) || "ROLE_OPERADOR_MUNICIPAL".equals(role)) {
                // Usuário Municipal: Filtra pelo município do usuário
                pagedResult = filiadoService.filtrarFiliados(nome, cpf, titulo, ufUsuario, municipioUsuario, dataFiliacao, pageable);
            } else {
                // Se o papel não for reconhecido, retorna um resultado vazio ou outro comportamento
                pagedResult = Page.empty(pageable);
            }

           

        

        List<FiliadoDTO> filiados = pagedResult.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
                System.out.println("Número de filiados encontrados: " + filiados.size());
        // Adiciona os filtros ao modelo para exibir na interface
        model.addAttribute("nome", nome);
        model.addAttribute("uf", uf);
        model.addAttribute("municipio", municipio);
        model.addAttribute("titulo", titulo);
        model.addAttribute("cpf", cpf);

  
        model.addAttribute("filiados", filiados);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pagedResult.getTotalPages());
        model.addAttribute("pageSize", size);

        return "FiliacaoInterna"; // Nome do arquivo HTML
    }

    private FiliadoDTO convertToDTO(FiliacaoInterna filiado) {
        FiliadoDTO dto = new FiliadoDTO();
        dto.setNome(filiado.getNome());
        dto.setCpf(filiado.getCpf());
        dto.setTitulo(filiado.getTitulo());
        dto.setDataNascimento(filiado.getDataNascimento());
        dto.setUf(filiado.getUf());
        dto.setMunicipio(filiado.getMunicipio());
        dto.setDataFiliacao(filiado.getDataFiliacao());
        dto.setNomePai(filiado.getNomePai());
        dto.setNomeMae(filiado.getNomeMae());
        dto.setLogradouro(filiado.getLogradouro());
        dto.setComplemento(filiado.getComplemento());
        dto.setNumero(filiado.getNumero());
        dto.setBairro(filiado.getBairro());
        dto.setCep(filiado.getCep());
        dto.setNumeroTelefone1(filiado.getNumeroTelefone1());
        dto.setNumeroTelefone2(filiado.getNumeroTelefone2());
        dto.setEmail(filiado.getEmail());
        dto.setGenero(filiado.getGenero());
        dto.setSituacao(filiado.getSituacao());
        return dto;
    }
    @GetMapping("/api/filiado-Interno")
    @ResponseBody
    public List<Filiados> getFiliados() {
        return filiadoViewRepository.findAll();
    }

    // Método para exibir os detalhes de um filiado
    @GetMapping("/detalhar-Interno/{id}")
    public String detalharFiliado(@PathVariable("id") String id, Model model) {
        FiliacaoInterna filiado = filiadoService.buscarPorId(id)
        .orElseThrow(() -> new IllegalArgumentException("Filiado não encontrado com ID: " + id)); // Suponha que esse método retorne um filiado por ID
        model.addAttribute("FiliacaoInterna", filiado); // Passa o objeto filiado para a página
        return "detalhar-filiadoInterno";  // Nome da view onde você vai exibir os detalhes
    }


     // Método para exibir os detalhes de um filiado
     @GetMapping("/documentos-Interno/{id}")
     public String documentoFiliado(@PathVariable("id") String id, Model model) {
        FiliacaoInterna filiado = filiadoService.buscarPorId(id)
        .orElseThrow(() -> new IllegalArgumentException("Filiado não encontrado com ID: " + id));
         model.addAttribute("filiado", filiado);  // Passa o objeto filiado para a página
         return "documentos-filiadoInterno";  // Nome da view onde você vai exibir os detalhes
     }
    }