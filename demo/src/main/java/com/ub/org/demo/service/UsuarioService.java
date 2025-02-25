package com.ub.org.demo.service;

import com.ub.org.demo.enums.Funcao;
import com.ub.org.demo.repository.PermissaoRepository;
import com.ub.org.demo.repository.UsuarioRepository;
import com.ub.org.demo.view.Permissao;
import com.ub.org.demo.view.Usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuariosRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

     @Autowired
    private PermissaoRepository permissaoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuarios usuario = usuariosRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + email);
        }

        // Supondo que usuario.getFuncao() retorna uma String ou Enum que pode ser convertido
          
        String role = usuario.getFuncao().name(); // Ex: "ADMIN" ou "USER"

        return org.springframework.security.core.userdetails.User.withUsername(usuario.getEmail())
            .password(usuario.getSenha())
            .roles(role) // Assegure-se de que não tem o prefixo "ROLE_"
            .build();
    }
            public boolean emailExiste(String email) {
                return usuariosRepository.existsByEmail(email);
            }


    public void salvarUsuario(String email, String senha, String nome, String sobrenome, String cargo,Funcao funcao,String uf,String municipio, String cpf) {
        
        String senhaCriptografada = passwordEncoder.encode(senha);
        Usuarios usuario = new Usuarios();
        usuario.setEmail(email);
        usuario.setSenha(senhaCriptografada);
        usuario.setNome(nome);
        usuario.setSobrenome(sobrenome);
        usuario.setCargo(cargo);
        usuario.setFuncao(funcao);   // Aqui, funcao seria uma String que corresponde a um valor do Enum
        usuario.setUf(uf);
        usuario.setMunicipio(municipio);
        usuario.setCpf(cpf);
        // Preencha outros campos conforme necessário
        
    
        usuariosRepository.save(usuario);
          // Criar permissões automaticamente conforme a função do usuário
        Permissao permissao = new Permissao();
        permissao.setFuncao(funcao);
        permissao.setUsuario(usuario); 

        // Definir permissões com base na função do usuário
        switch (funcao) {
            case ADMINISTRADOR:
                permissao.setCriar("Ativado");
                permissao.setEditar("Ativado");
                permissao.setExcluir("Ativado");
                permissao.setVizualizar("Ativado");
                permissao.setAprovar("Ativado");
                permissao.setAcessar("Ativado");
                permissao.setInteragir("Ativado");
                permissao.setBloqueia_user("Ativado");
                break;
            case ADMINISTRADOR_NACIONAL:
                permissao.setCriar("Desativado");
                permissao.setEditar("Ativado");
                permissao.setExcluir("Ativado");
                permissao.setVizualizar("Ativado");
                permissao.setAprovar("Ativado");
                permissao.setAcessar("Ativado");
                permissao.setInteragir("Ativado");
                permissao.setBloqueia_user("Ativado");
                break;
                case ADM_ESTADUAL:
                permissao.setCriar("Desativado");
                permissao.setEditar("Ativado");
                permissao.setExcluir("Ativado");
                permissao.setVizualizar("Ativado");
                permissao.setAprovar("Ativado");
                permissao.setAcessar("Ativado");
                permissao.setInteragir("Ativado");
                permissao.setBloqueia_user("Ativado");
                break;
                case CONSULTOR_ESTADUAL:
                permissao.setCriar("Desativado");
                permissao.setEditar("Desativado");
                permissao.setExcluir("Desativado");
                permissao.setVizualizar("Ativado");
                permissao.setAprovar("Desativado");
                permissao.setAcessar("Ativado");
                permissao.setInteragir("Desativado");
                permissao.setBloqueia_user("Desativado");
                break;
                case CONSULTOR_MUNICIPAL:
                permissao.setCriar("Desativado");
                permissao.setEditar("Desativado");
                permissao.setExcluir("Desativado");
                permissao.setVizualizar("Ativado");
                permissao.setAprovar("Desativado");
                permissao.setAcessar("Ativado");
                permissao.setInteragir("Desativado");
                permissao.setBloqueia_user("Desativado");
                break;
                case CONSULTOR_NACIONAL:
                permissao.setCriar("Desativado");
                permissao.setEditar("Desativado");
                permissao.setExcluir("Desativado");
                permissao.setVizualizar("Ativado");
                permissao.setAprovar("Desativado");
                permissao.setAcessar("Ativado");
                permissao.setInteragir("Desativado");
                permissao.setBloqueia_user("Desativado");
                break;
                case OPERADOR_ESTADUAL:
                permissao.setCriar("Desativado");
                permissao.setEditar("Desativado");
                permissao.setExcluir("Desativado");
                permissao.setVizualizar("Ativado");
                permissao.setAprovar("Desativado");
                permissao.setAcessar("Ativado");
                permissao.setInteragir("Ativado");
                permissao.setBloqueia_user("Desativado");
                break;
                case OPERADOR_MUNICIPAL:
                permissao.setCriar("Desativado");
                permissao.setEditar("Desativado");
                permissao.setExcluir("Desativado");
                permissao.setVizualizar("Ativado");
                permissao.setAprovar("Desativado");
                permissao.setAcessar("Ativado");
                permissao.setInteragir("Ativado");
                permissao.setBloqueia_user("Desativado");
                break;
                case OPERADOR_NACIONAL:
                permissao.setCriar("Desativado");
                permissao.setEditar("Desativado");
                permissao.setExcluir("Desativado");
                permissao.setVizualizar("Ativado");
                permissao.setAprovar("Desativado");
                permissao.setAcessar("Ativado");
                permissao.setInteragir("Ativado");
                permissao.setBloqueia_user("Desativado");
                break;
            default:
                permissao.setCriar("Desativado");
                permissao.setEditar("Desativado");
                permissao.setExcluir("Desativado");
                permissao.setVizualizar("Desativado");
                permissao.setAprovar("Desativado");
                permissao.setAcessar("Desativado");
                permissao.setInteragir("Desativado");
                permissao.setBloqueia_user("Desativado");
                break;
        }

        // Salvar a permissão no banco de dados
        permissaoRepository.save(permissao);

    }
    // Método para criar o primeiro administrador
    public void criarAdministrador(String email, String senha, String nome, String sobrenome, 
                                    String cargo, Funcao funcao, String uf, String municipio, String cpf) {

        // Verifica se já existe um administrador
        if (usuariosRepository.count() == 0) {
            Usuarios usuario = new Usuarios();

            // Criptografa a senha usando BCrypt
            String senhaCriptografada = new BCryptPasswordEncoder().encode(senha);

            // Configura os campos do usuário
            usuario.setEmail(email);
            usuario.setSenha(senhaCriptografada);
            usuario.setNome(nome);
            usuario.setSobrenome(sobrenome);
            usuario.setCargo(cargo);
            usuario.setFuncao(funcao);  // Aqui, você define a função usando o Enum
            usuario.setUf(uf);
            usuario.setMunicipio(municipio);
            usuario.setCpf(cpf);
            // Salva o usuário no banco de dados
            usuariosRepository.save(usuario);

           
        }
    }
    


    public boolean authenticate(String email, String rawPassword) {
        Usuarios usuario = usuariosRepository.findByEmail(email);
        if (usuario != null) {
            return passwordEncoder.matches(rawPassword, usuario.getSenha());
        }
        return false;
    }

    public void userLogado(Model model, Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName(); // Usando email como nome de usuário
            Usuarios usuario = usuariosRepository.findByEmail(email); // Busca o usuário pelo email
            
    
            if (usuario != null) {
                // Adiciona todas as informações do usuário ao modelo
                model.addAttribute("nome", usuario.getNome()); // Adiciona o objeto de usuário ao modelo
                model.addAttribute("sobrenome", usuario.getSobrenome());
                model.addAttribute("cargo", usuario.getCargo());
                model.addAttribute("funcao", usuario.getFuncao());
                model.addAttribute("uf", usuario.getUf());
                model.addAttribute("municipio", usuario.getMunicipio());
                model.addAttribute("email", usuario.getEmail());
                model.addAttribute("senha", usuario.getSenha());
                model.addAttribute("userLogado",usuario.getCpf());
                System.out.println("CPF do usuário logado: " + usuario.getCpf()); 
          
                // Adicione outras propriedades conforme necessário
                // Exemplo:
                // model.addAttribute("email", user.getEmail());
                // model.addAttribute("telefone", user.getTelefone());
            }
        }
    }

    

   
}
