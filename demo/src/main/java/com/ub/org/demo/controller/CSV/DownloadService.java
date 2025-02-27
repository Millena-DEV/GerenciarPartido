package com.ub.org.demo.controller.CSV;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ub.org.demo.repository.DownloadRepository;
import com.ub.org.demo.repository.UsuarioRepository;
import com.ub.org.demo.service.UsuarioService;
import com.ub.org.demo.view.Download;
import com.ub.org.demo.view.Usuarios;

@Service
public class DownloadService {

    @Autowired
    private DownloadRepository downloadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; 
    
    public void registrarDownload(String nomeArquivo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuarios usuario = usuarioRepository.findByEmail(username);
        String usuarioLogado =(usuario != null) ? usuario.getEmail() : null;
        Download download = new Download(nomeArquivo, usuarioLogado, LocalDateTime.now());
        downloadRepository.save(download);
    }
}