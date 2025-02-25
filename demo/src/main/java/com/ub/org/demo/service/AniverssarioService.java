package com.ub.org.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ub.org.demo.repository.AniverssarioRepository;
import com.ub.org.demo.repository.UsuarioRepository;
import com.ub.org.demo.view.Aniverssario;
import com.ub.org.demo.view.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Service
public class AniverssarioService {
@Autowired
    private AniverssarioRepository aniverssarioRepository;
@Autowired
  private  UsuarioRepository usuarioRepository;

  public String getUfDoUsuarioLogado() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    // Obter os dados do usuário logado
    Usuarios usuario = usuarioRepository.findByEmail(username);
    return usuario != null ? usuario.getUf() : null;
}

public String getMunicipioDoUsuarioLogado() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    // Obter os dados do usuário logado
    Usuarios usuario = usuarioRepository.findByEmail(username);
    return usuario != null ? usuario.getMunicipio() : null;
}

public Page<Aniverssario> findAniverssariosDeHojeComFiltro(Pageable pageable) {
  // Chamando o repositório sem filtros adicionais
  return aniverssarioRepository.findAniverssariosDeHoje(pageable);
}

public Page<Aniverssario> findAniverssariosDeHojeComFiltro(String uf, Pageable pageable) {
  // Chamando o repositório com filtro por UF
  return aniverssarioRepository.findAniverssariosPorUf(uf, pageable);
}

public Page<Aniverssario> findAniverssariosDeHojeComFiltro(String uf, String municipio, Pageable pageable) {
  // Chamando o repositório com filtro por UF e Município
  return aniverssarioRepository.findAniverssariosPorUfEMunicipio(uf, municipio, pageable);
}



    // Filtra os aniversariantes de hoje (mês e dia)
    /*List<Aniverssario> aniversariantesHoje = aniversariantesPage.stream()
    .filter(a -> {
        if (a.getDtNasc() != null) {
            int mes = a.getMes_niver();  // Mes (1)
            int dia = a.getDia_niver();  // Dia (21)
            LocalDate today = LocalDate.now();
            
            return mes == today.getMonthValue() && dia == today.getDayOfMonth();
        }
        return false;
    })
    .collect(Collectors.toList());


  
    return aniversariantesHoje;
}*/

// Contagem de aniversariantes de hoje sem filtro
public long countAniversariantesDeHoje() {
    return aniverssarioRepository.countAniversariantesDeHoje();
}

public long  countAniversariantesDoMes(){
  return aniverssarioRepository.countAniversariantesDoMes();
}
/*////////////////////////////////////////////////////////////////////////////////////////////// */
// Contagem de aniversariantes de hoje filtrando por UF
public long countAniversariantesDeHojeUF(String uf) {
    String ufUsuario = getUfDoUsuarioLogado();
    return aniverssarioRepository.countAniversariantesDeHojeUF(ufUsuario);
}

public long countAniversariantesDoMesUF (String uf){
  String ufUsuario = getUfDoUsuarioLogado();
    return aniverssarioRepository.countAniversariantesDoMesUF(ufUsuario);
} /*////////////////////////////////// */

// Contagem de aniversariantes de hoje filtrando por UF e Município
public long countAniversariantesDeHojeUFMuni(String uf, String municipio ) {
    String ufUsuario = getUfDoUsuarioLogado();
    String municipioUsuario = getMunicipioDoUsuarioLogado();
    return aniverssarioRepository.countAniversariantesDeHojeUFMuni(ufUsuario, municipioUsuario);
}

public long countAniversariantesDoMesUFMuni(String uf, String municipio ) {
  String ufUsuario = getUfDoUsuarioLogado();
  String municipioUsuario = getMunicipioDoUsuarioLogado();
  return aniverssarioRepository.countAniversariantesDoMesUFMuni(ufUsuario, municipioUsuario);
}
}
  
  

