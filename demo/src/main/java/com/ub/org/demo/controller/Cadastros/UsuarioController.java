package com.ub.org.demo.controller.Cadastros;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ub.org.demo.service.ServicoRecuperacaoSenha;
@RestController
@RequestMapping("/api")
public class UsuarioController {


    private final ServicoRecuperacaoSenha servicoRecuperacaoSenha;

    @Autowired
    public UsuarioController(ServicoRecuperacaoSenha servicoRecuperacaoSenha) {
        this.servicoRecuperacaoSenha = servicoRecuperacaoSenha;
    }
  

     @PostMapping("/envia-email")
    public ResponseEntity<String> enviaEmail(@RequestBody Map<String, String> request) {
    try {
        // Recupera o e-mail do corpo da requisição
        String email = request.get("email");

        // Verifica se o e-mail é válido
        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O e-mail é obrigatório.");
        }

        // Recupera o usuário pelo e-mail
        servicoRecuperacaoSenha.recuperarUsuarioPorEmail(email);

        // Gera o token de recuperação e envia o e-mail
        String token = servicoRecuperacaoSenha.gerarTokenRecuperacao(email);
        servicoRecuperacaoSenha.enviarEmailRecuperacao(email, token);

        return ResponseEntity.ok("E-mail de recuperação enviado com sucesso.");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
    }
}


    @PostMapping("/salvarSenha")
    public ResponseEntity<String> salvarNovaSenha(@RequestParam String token, @RequestParam String novaSenha) {
        try {
            // Verifica o token
            if (token == null || token.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido ou ausente.");
            }
    
            // Valida o token e realiza a recuperação de senha
            boolean sucesso = servicoRecuperacaoSenha.validarTokenERedefinirSenha(token, novaSenha);
    
            if (sucesso) {
                return ResponseEntity.ok("Senha redefinida com sucesso.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido ou expirado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao redefinir senha: " + e.getMessage());
        }


 }
 
 
}