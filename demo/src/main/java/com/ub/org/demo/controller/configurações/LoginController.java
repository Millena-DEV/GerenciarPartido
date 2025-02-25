package com.ub.org.demo.controller.configurações;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ub.org.demo.service.UsuarioService;
import com.ub.org.demo.view.Usuarios;
import jakarta.validation.Valid;

@Controller
public class LoginController {
    
    @Autowired
    private UsuarioService  userService;

     @GetMapping("/cadastrar")
    public String showRegistrationForm(Model model, Authentication authentication) {
        userService.userLogado(model, authentication);
        model.addAttribute("usuario", new Usuarios());

        return "cadastrar"; // Retorna a página de cadastro
    }

    @PostMapping("/cadastrar")
     public String registerUser(@Valid @ModelAttribute("usuario") Usuarios user, BindingResult result) {
    if (userService.emailExiste(user.getEmail())) {
        result.rejectValue("email", "email.duplicate", "Este e-mail já está em uso.");
    }
    if (result.hasErrors()) {
        return "cadastrar"; // Retorna ao formulário se houver erros
    }
   
    // Passa os parâmetros individuais para o serviço
    userService.salvarUsuario(
        user.getEmail(),
        user.getSenha(),
        user.getNome(),
        user.getSobrenome(),
        user.getCargo(),
        user.getFuncao(),
        user.getUf(),
        user.getMunicipio(),
        user.getCpf()
    );
    
    // Redireciona para a página de login após o cadastro bem-sucedido
    return "redirect:/login";
}

    @PostMapping("/login")
        public String login(@RequestParam String username, @RequestParam String password, Model model) {
            if (userService.authenticate(username, password)) {
                return "redirect:/index"; // Redirecionar para a página inicial ou painel
            }
            model.addAttribute("error", true);
            return "login"; // Retorna à página de login em caso de falha
}

  
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login"; // Retorna a página de cadastro
    }

    @GetMapping("/controle_usuarios")
    public String showcontroleUsuarioForm(Model model) {
        return "controle_usuarios"; // Retorna a página de cadastro
    }
    
    @GetMapping("/redefinir-senha")
    public String showRedefinirSenha(@RequestParam String token, Model model) {
        // Adicionando o token ao modelo
        model.addAttribute("token", token);
        return "redefinir-senha";  // Nome da view (por exemplo, redefinir-senha.html)
    }

}
