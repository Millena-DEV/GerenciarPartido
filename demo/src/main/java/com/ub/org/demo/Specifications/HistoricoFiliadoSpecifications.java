package com.ub.org.demo.Specifications;
import com.ub.org.demo.view.HistoricoFiliados;

import org.springframework.data.jpa.domain.Specification;
public class HistoricoFiliadoSpecifications {
    
    public static Specification<HistoricoFiliados> hasNome(String nome) {
        return (root, query, criteriaBuilder) -> {
            if (nome == null || nome.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
        };
    }


    public static Specification<HistoricoFiliados> hasTitulo(String titulo) {
        return (root, query, criteriaBuilder) -> {
            if (titulo == null || titulo.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("titulo"), titulo);
        };
    }

    public static Specification<HistoricoFiliados> hasUf(String uf) {
        return (root, query, criteriaBuilder) -> {
            if (uf == null || uf.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("uf"), uf);
        };
    }

    public static Specification<HistoricoFiliados> hasMunicipio(String municipio) {
        return (root, query, criteriaBuilder) -> {
            if (municipio == null || municipio.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("municipio"), municipio);
        };
    }

    public static Specification<HistoricoFiliados> hasDataFiliacao(String dataFiliacao) {
        return (root, query, criteriaBuilder) -> {
            if (dataFiliacao == null || dataFiliacao.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("dataFiliacao"), dataFiliacao);
        };
    }
}
