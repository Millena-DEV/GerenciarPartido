package com.ub.org.demo.segurança;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                .disable()  // Desabilita CSRF completamente
            )
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll() // Permitir recursos estáticos
                .requestMatchers("/filiados").permitAll() // Permitir acesso a /cadastrar
                .requestMatchers("/candidatos").permitAll() // Permitir acesso a /cadastrar
                .requestMatchers("/dashboardCandidatos").permitAll() // Permitir acesso a /cadastrar

                .requestMatchers("/login").permitAll() // Permitir acesso a /login
                .requestMatchers("/api/**").permitAll() // Permite todas as rotas /api/
                .requestMatchers("/api/salvarSenha").permitAll() // Permitir acesso a /api/salvarSenha sem autenticação
                .requestMatchers("/api/municipios/**").permitAll() // Permitir acesso à rota /cad/municipios
                .requestMatchers("/api/export/filiados**").permitAll() 
                .requestMatchers("/error").permitAll()
                .requestMatchers("/permissoes/**").hasAnyRole("ADMINISTRADOR_NACIONAL","ADMINISTRADOR", "ADM_ESTADUAL")
                .requestMatchers("/cadastrar/**").hasAnyRole("ADMINISTRADOR_NACIONAL","ADMINISTRADOR", "ADM_ESTADUAL")

                .requestMatchers("/upload-documents/**").hasAnyRole("ADMINISTRADOR_NACIONAL","ADMINISTRADOR", "ADM_ESTADUAL"," OPERADOR_NACIONAL", "OPERADOR_ESTADUAL","OPERADOR_MUNICIPAL")
                .requestMatchers("/documentos/**").hasAnyRole("ADMINISTRADOR_NACIONAL","ADMINISTRADOR", "ADM_ESTADUAL"," OPERADOR_NACIONAL",  "OPERADOR_ESTADUAL","OPERADOR_MUNICIPAL")

                .requestMatchers("/detalhar/**").hasAnyRole("ADMINISTRADOR_NACIONAL","ADMINISTRADOR", "ADM_ESTADUAL"," OPERADOR_NACIONAL",  "OPERADOR_ESTADUAL","OPERADOR_MUNICIPAL")
                .requestMatchers("/redefinir-senha").hasAnyRole("ADMINISTRADOR_NACIONAL","ADMINISTRADOR") // Permitir acesso a /resetar-senha
                .requestMatchers("/api/envia-email").hasAnyRole("ADMINISTRADOR_NACIONAL","ADMINISTRADOR")// Permitir acesso a /api/envia-email sem autenticação
                .anyRequest().authenticated() // Requer autenticação para outras páginas
            )
            
            .formLogin(form -> form
                .loginPage("/login") // Página de login personalizada
                .defaultSuccessUrl("/index", true) // Redireciona para /index após login bem-sucedido
                .usernameParameter("username") // Parâmetro para o nome de usuário
                .passwordParameter("password") // Parâmetro para a senha
                .failureUrl("/login?error=true") // Página de erro no login
                .permitAll() // Permite acesso público à página de login
            )
           /*  .logout(logout -> logout.permitAll()) // Permite logout sem autenticação
            .exceptionHandling(exception -> exception
            .accessDeniedPage("/acesso-negado") // Redireciona para página de acesso negado*/
          
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/acesso-negado") // Redireciona para página de acesso negado
            )
                .logout(logout -> logout
                .logoutUrl("/logout") // URL para processar o logout
                .logoutSuccessUrl("/login?logout") // Redireciona para a página de login após logout
                .invalidateHttpSession(true) // Invalida a sessão
                .deleteCookies("JSESSIONID") // Remove os cookies de autenticação
                .permitAll()
                
    );
    

    

        return http.build(); // Retorna a configuração do filtro de segurança
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Cria e retorna um PasswordEncoder
    }


    
}
