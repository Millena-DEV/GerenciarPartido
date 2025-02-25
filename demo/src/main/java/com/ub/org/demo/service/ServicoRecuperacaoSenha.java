package com.ub.org.demo.service;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ub.org.demo.repository.UsuarioRepository;
import com.ub.org.demo.view.Usuarios;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class ServicoRecuperacaoSenha {

    private final UsuarioRepository usuarioRepository;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ServicoRecuperacaoSenha(UsuarioRepository usuarioRepository, JavaMailSender mailSender, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

    // Recupera o usuário pelo e-mail
    public Usuarios recuperarUsuarioPorEmail(String email) throws Exception {
        Usuarios usuario = usuarioRepository.findByEmail(email);  // Supondo que não retorne Optional
        if (usuario == null) {
            throw new Exception("Usuário não encontrado com esse e-mail");
        }
        return usuario;
    }

    // Gera o token de recuperação de senha
    public String gerarTokenRecuperacao(String email) throws Exception {
        Usuarios usuario = recuperarUsuarioPorEmail(email);  // Recupera o usuário pelo e-mail

        // Gera um token único de recuperação
        String token = UUID.randomUUID().toString();

        // Define o tempo de expiração do token (1 hora neste exemplo)
        LocalDateTime dataExpiracao = LocalDateTime.now().plusHours(1);

        // Associa o token e a data de expiração ao usuário
        usuario.setTokenRecuperacao(token);
        usuario.setDataExpiracaoToken(dataExpiracao);
        usuarioRepository.save(usuario);

        // Retorna o token gerado
        return token;
    }

    // Envia o token por e-mail para o usuário
    public void enviarEmailRecuperacao(String email, String token) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("noreply@uniaobrasil.org.br");
        helper.setTo(email);
        helper.setSubject("Recuperação de Senha");
        helper.setText("Para recuperar sua senha, clique no link abaixo:\n\n" +
        "http://localhost:8081/redefinir-senha?token=" + token);


        mailSender.send(message);
    }

    // Método para validar o token e recuperar o usuário
    public Usuarios validarTokenRecuperacao(String token) throws Exception {
        // Busca o usuário pelo token
        Usuarios usuario = usuarioRepository.findByTokenRecuperacao(token);

        // Se o token não existir, lança exceção
        if (usuario == null) {
            throw new Exception("Token inválido ou inexistente");
        }

        // Verifica se o token expirou
        if (usuario.getDataExpiracaoToken().isBefore(LocalDateTime.now())) {
            throw new Exception("Token de recuperação expirado");
        }

        // Retorna o usuário associado ao token
        return usuario;
    }

    // Método para salvar a nova senha
    public boolean validarTokenERedefinirSenha(String token, String novaSenha) throws Exception {
        // Valida o token e recupera o usuário
        Usuarios usuario = validarTokenRecuperacao(token);

        if (usuario == null) {
            // Opcional: tratar o caso onde o usuário não é encontrado ou o token é inválido
            throw new Exception("Usuário não encontrado ou token inválido.");
        }
        // Codifica a nova senha antes de salvar
        String senhaCodificada = passwordEncoder.encode(novaSenha);

        // Atualiza a senha do usuário
        usuario.setSenha(senhaCodificada);  // Supondo que a entidade Usuários tenha o campo "senha"
        usuario.setTokenRecuperacao(null);  // Limpa o token após a troca de senha
        usuario.setDataExpiracaoToken(null); // Limpa a data de expiração do token
        usuarioRepository.save(usuario);  // Salva a nova senha no banco de dados
        return true;
    }
}
