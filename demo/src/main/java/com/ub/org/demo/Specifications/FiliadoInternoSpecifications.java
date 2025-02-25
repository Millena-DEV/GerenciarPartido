package com.ub.org.demo.Specifications;
 import com.ub.org.demo.view.FiliacaoInterna;
// Ajuste para o pacote correto
import org.springframework.data.jpa.domain.Specification;
public class FiliadoInternoSpecifications {
    
    public static Specification<FiliacaoInterna> hasNome(String nome) {
        return (root, query, criteriaBuilder) -> {
            if (nome == null || nome.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
        };
    }

    public static Specification<FiliacaoInterna> hasCpf(String cpf) {
        return (root, query, criteriaBuilder) -> {
            if (cpf == null || cpf.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("cpf"), cpf);
        };
    }

    public static Specification<FiliacaoInterna> hasTitulo(String titulo) {
        return (root, query, criteriaBuilder) -> {
            if (titulo == null || titulo.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("titulo"), titulo);
        };
    }

    public static Specification<FiliacaoInterna> hasUf(String uf) {
        return (root, query, criteriaBuilder) -> {
            if (uf == null || uf.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("uf"), uf);
        };
    }

    public static Specification<FiliacaoInterna> hasMunicipio(String municipio) {
        return (root, query, criteriaBuilder) -> {
            if (municipio == null || municipio.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("municipio"), municipio);
        };
    }

    public static Specification<FiliacaoInterna> hasDataFiliacao(String dataFiliacao) {
        return (root, query, criteriaBuilder) -> {
            if (dataFiliacao == null || dataFiliacao.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("dataFiliacao"), dataFiliacao);
        };
    }
}
