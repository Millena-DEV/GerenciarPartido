/*package com.ub.org.demo.segurança;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // Verificar se o usuário tem o papel de 'ADMINISTRADOR'
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMINISTRADOR_NACIONAL"));

        // Redirecionar para /adm se for administrador, caso contrário /index
        if (isAdmin) {
            response.sendRedirect("/adm");
        } else {
            response.sendRedirect("/index");
        }
    }
}*/