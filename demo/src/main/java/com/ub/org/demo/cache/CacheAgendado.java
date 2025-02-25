package com.ub.org.demo.cache;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
@Component
public class CacheAgendado {
    private static final Logger log = LoggerFactory.getLogger(CacheAgendado.class);
    @Scheduled(fixedDelay = 30,timeUnit = TimeUnit.MINUTES)
    @CacheEvict(value = {"cachePagInicial", "cachePagFiliados","candidatosCache","cachePagHistoricoFiliados","cachePagFiliado-Interno"}, allEntries = true)

    public void limparcache(){
        log.info("Cache limpo em: " + LocalDateTime.now());

    }

}
