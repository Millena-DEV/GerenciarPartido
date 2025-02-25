package com.ub.org.demo.Specifications;

import org.springframework.data.jpa.domain.Specification;
import com.ub.org.demo.view.CandidatoView;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;



public class CandidatoSpecifications {

      @PersistenceContext
    private EntityManager entityManager;

     // Especificação para filtrar pelo campo 'ano'
     public static Specification<CandidatoView> hasAno(Integer ano_filtro) {
        return (root, query, criteriaBuilder) -> {
            if (ano_filtro == null) {
                return criteriaBuilder.conjunction(); // Retorna uma condição verdadeira se o ano for nulo
            }
            return criteriaBuilder.equal(root.get("ano_filtro"), ano_filtro); // Retorna uma condição de comparação para o ano
        };
    }
    public static Specification<CandidatoView> hasPartido(String partido) {
        return (root, query, criteriaBuilder) -> {
            if (partido == null || partido.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("partido"), "%" + partido + "%");
        };
    }
    public static Specification<CandidatoView> hasCargo(String cargo) {
        return (root, query, criteriaBuilder) -> {
            if (cargo == null || cargo.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("cargo"), "%" + cargo + "%");
        };
    }
public static Specification<CandidatoView> hasNome(String nome) {
        return (root, query, criteriaBuilder) -> {
            if (nome == null || nome.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
        };
    }

    public static Specification<CandidatoView> hasTitulo(String titulo) {
        return (root, query, criteriaBuilder) -> {
            if (titulo == null || titulo.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("titulo"), titulo);
        };
    }

    public static Specification<CandidatoView> hasUf(String uf) {
        return (root, query, criteriaBuilder) -> {
            if (uf == null || uf.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("uf"), uf);
        };
    }

    public static Specification<CandidatoView> hasSituacao(String situ_filiacao) {
        return (root, query, criteriaBuilder) -> {
            if (situ_filiacao == null || situ_filiacao.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("situ_filiacao"), situ_filiacao);
        };
    }

    public static Specification<CandidatoView> hasMunicipio(String municipio) {
        return (root, query, criteriaBuilder) -> {
            if (municipio == null || municipio.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("municipio"), municipio);
        };
    }

}
