package com.ub.org.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ub.org.demo.enums.Funcao;
import com.ub.org.demo.service.UsuarioService;

@SpringBootApplication
@EnableCaching // Habilita o cache
@EnableScheduling

public class DemoApplication   implements CommandLineRunner{

	public static void main(String[] args)  {
		SpringApplication.run(DemoApplication.class, args);
	}
 @Autowired
    private UsuarioService usuarioService;
	  @Override

    public void run(String... args) throws Exception {
        // Cria o primeiro administrador com os parâmetros desejados
        usuarioService.criarAdministrador("admin@admin.com", "admin123", "Administrador", "Sistema", "Gestor", 
                                          Funcao.ADMINISTRADOR, "DF", "BRASÍLIA", "12345678901");
    }

	

}
