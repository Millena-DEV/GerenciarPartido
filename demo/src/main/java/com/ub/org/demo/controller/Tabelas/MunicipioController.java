package com.ub.org.demo.controller.Tabelas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ub.org.demo.repository.MunicipioRepository;
import com.ub.org.demo.repository.UsuarioRepository;
import com.ub.org.demo.view.Usuarios;
import com.ub.org.demo.view.municipio;
import org.springframework.security.core.Authentication;

@Controller
public class MunicipioController {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    
    // @Cacheable("cachePagMunicipio")
    @GetMapping("/api/municipios/{uf}")
    public ResponseEntity<List<municipio>> getMunicipiosByUf(@PathVariable String uf) {
        // Verifica se o usuário está autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication != null ? authentication.getName() : null;
        
        // Se não estiver autenticado (username é null ou vazio), retorna todos os municípios da UF
        if (username == null || username.isEmpty()) {
            List<municipio> municipios = municipioRepository.findByUf(uf);
            return ResponseEntity.ok(municipios);
        }
    
        // Se estiver autenticado, verifica o papel (role) do usuário
        String role = authentication.getAuthorities().stream()
                                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                                    .findFirst().orElse(null);
    
        // Se o usuário for Consultor Municipal ou Operador Municipal
        if ("ROLE_CONSULTOR_MUNICIPAL".equals(role) || "ROLE_OPERADOR_MUNICIPAL".equals(role)) {
            // Busca o usuário no banco de dados para verificar a UF e município
            Usuarios usuario = usuarioRepository.findByEmail(username);
            if (usuario != null && usuario.getUf().equals(uf)) {
                List<municipio> municipios = municipioRepository.findByUfAndMunicipio(uf, usuario.getMunicipio());
                return ResponseEntity.ok(municipios);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acesso negado
            }
        }
    
        // Se o usuário for Consultor Estadual ou Operador Estadual
        if ("ROLE_CONSULTOR_ESTADUAL".equals(role) || "ROLE_OPERADOR_ESTATUAL".equals(role)) {
            // Verifica a UF do usuário
            Usuarios usuario = usuarioRepository.findByEmail(username);
            if (usuario != null && usuario.getUf().equals(uf)) {
                List<municipio> municipios = municipioRepository.findByUf(uf); // Retorna os municípios da UF
                return ResponseEntity.ok(municipios);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acesso negado
            }
        }
    
        // Para outros usuários autenticados (ou se a role não corresponder), retorna todos os municípios da UF
        List<municipio> municipios = municipioRepository.findByUf(uf);
        return ResponseEntity.ok(municipios);
    }
    


}
