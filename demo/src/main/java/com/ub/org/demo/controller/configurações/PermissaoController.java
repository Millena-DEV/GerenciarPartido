package com.ub.org.demo.controller.configurações;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ub.org.demo.repository.PermissaoRepository;
import com.ub.org.demo.repository.UsuarioRepository;
import com.ub.org.demo.view.Permissao;
import com.ub.org.demo.view.Usuarios;
import org.springframework.ui.Model;

@Controller
public class PermissaoController {


      @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    // Exibir tela de permissões
    @GetMapping("/permissoes")
    public String exibirPermissoes(Model model) {
        List<Usuarios> usuarios = usuarioRepository.findAll(); // Listar todos os usuários
        model.addAttribute("usuarios", usuarios);
        return "permissoes";
    }


    // Carregar permissões de um usuário
    @GetMapping("/permissoes/{usuarioId}")
    public String editarPermissoes(@PathVariable Long usuarioId, Model model) {
        Usuarios usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null) {
            return "redirect:/permissoes";
        }

        List<Permissao> permissoes = permissaoRepository.findByFuncao(usuario.getFuncao());
        
        model.addAttribute("usuario", usuario);
        model.addAttribute("permissoes", permissoes);
        return "editarPermissoes"; // A página de edição de permissões
    }
  

    @PostMapping("/permissoes/alterar-status/{permissaoId}")
    public ResponseEntity<String> alterarStatusPermissao(@PathVariable Long permissaoId, 
                                                         @RequestParam Long usuarioId, 
                                                         @RequestParam String permissao, 
                                                         @RequestParam String status) {
    
        // Verificar se a permissão existe
        Optional<Permissao> permissaoOpt = permissaoRepository.findById(permissaoId);
        if (!permissaoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permissão não encontrada");
        }
    
        Permissao permissaoObj = permissaoOpt.get();
    
        // Verificar se o usuário existe
        Optional<Usuarios> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (!usuarioOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    
        // Atualizar o status da permissão correspondente
        switch (permissao) {
            case "criar":
                permissaoObj.setCriar(status);
                break;
            case "editar":
                permissaoObj.setEditar(status);
                break;
            case "excluir":
                permissaoObj.setExcluir(status);
                break;
            case "vizualizar":
                permissaoObj.setVizualizar(status);
                break;
            case "aprovar":
                permissaoObj.setAprovar(status);
                break;
            case "acessar":
                permissaoObj.setAcessar(status);
                break;
            case "interagir":
                permissaoObj.setInteragir(status);
                break;
            case "bloquear_usuario":
                permissaoObj.setBloqueia_user(status);
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Permissão desconhecida");
        }
    
        // Salvar as alterações no banco
        permissaoRepository.save(permissaoObj);
    
        return ResponseEntity.ok("Permissão alterada com sucesso");
    }
    
    

}


