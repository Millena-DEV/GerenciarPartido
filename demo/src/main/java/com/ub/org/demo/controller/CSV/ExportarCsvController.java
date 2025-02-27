package com.ub.org.demo.controller.CSV;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/export")
public class ExportarCsvController {
    @Autowired
    private ExportarCsvService ExportarCsvService;
    @Autowired
    private DownloadService downloadService;

    @GetMapping("/filiados")
    public void exportFiliados(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String nomeArquivo = "filiados.csv";
        // Registra o download no banco de dados
        downloadService.registrarDownload(nomeArquivo);
        // Configurações para exportar o CSV
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=" + nomeArquivo);
        ExportarCsvService.writeFiliadosToCsv(response.getWriter());
    }

    @GetMapping("/candidatos")
    public void exportCandidatos(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String nomeArquivo = "candidatos.csv";
        // Registra o download no banco de dados
        downloadService.registrarDownload(nomeArquivo);
        // Configurações para exportar o CSV
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=" + nomeArquivo);
        ExportarCsvService.writeCandidatosToCsv(response.getWriter());
    }


}
