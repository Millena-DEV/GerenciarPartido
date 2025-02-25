/*package com.ub.org.demo.segurança;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfignovo {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    // Injeta o handler personalizado
    public SecurityConfignovo(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                .disable()  // Desabilita CSRF completamente
            )
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll() // Permitir recursos estáticos
                .requestMatchers("/cadastrar").permitAll() // Permitir acesso a /cadastrar
                .requestMatchers("/login").permitAll() // Permitir acesso a /login
                .requestMatchers("/redefinir-senha").permitAll() // Permitir acesso a /resetar-senha
                .requestMatchers("/api/envia-email").permitAll() // Permitir acesso a /api/envia-email sem autenticação
                .requestMatchers("/filiados").permitAll()
                .requestMatchers("/api/filiados").permitAll()
                .requestMatchers("/candidatos").permitAll()
                .requestMatchers("/api/candidatos").permitAll()
                
              

                .requestMatchers("/admin/**").hasRole("ADMINISTRADOR_NACIONAL")
                .requestMatchers("/detalhar/candidato/").hasAnyRole("ADMINISTRADOR_NACIONAL","OPERADOR_NACIONAL","OPERADOR_ESTADUAL") 
                .requestMatchers("/detalhar/").hasAnyRole("ADMINISTRADOR_NACIONAL","OPERADOR_NACIONAL","OPERADOR_ESTADUAL") 
                .requestMatchers("/documentos/**").hasAnyRole("ADMINISTRADOR_NACIONAL","OPERADOR_NACIONAL","OPERADOR_ESTADUAL") 
                .requestMatchers("/api/municipios/**").hasAnyRole("CONSULTOR_MUNICIPAL", "OPERADOR_MUNICIPAL", "OPERADOR_NACIONAL","OPERADOR_ESTADUAL","ADMINISTRADOR_NACIONAL") // Apenas usuários do município podem acessar
                .requestMatchers("/upload-documents/**").hasAnyRole("ADMINISTRADOR_NACIONAL","OPERADOR_NACIONAL","OPERADOR_ESTADUAL")
                .requestMatchers("/error").permitAll()
                .anyRequest().authenticated() // Requer autenticação para outras páginas
            )
            .formLogin(form -> form
                .loginPage("/login") // Página de login personalizada
                .successHandler(customAuthenticationSuccessHandler) // Redireciona para /index após login bem-sucedido
                .usernameParameter("username") // Parâmetro para o nome de usuário
                .passwordParameter("password") // Parâmetro para a senha
                .failureUrl("/login?error=true") // Página de erro no login
                .permitAll() // Permite acesso público à página de login
            )
            .logout(logout -> logout.permitAll()); // Permite logout sem autenticação

        return http.build(); // Retorna a configuração do filtro de segurança
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Cria e retorna um PasswordEncoder
    }


    
}
*/