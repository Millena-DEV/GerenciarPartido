package com.ub.org.demo.enums;

public enum Funcao  {
    ADMINISTRADOR ("Administrador"), //  Usuário com permissões totais sobre o sistema, capaz de liberar ou bloquear acesso a funções e telas. criar, editar, excluir, visualizar
    ADMINISTRADOR_NACIONAL("Administrador Nacional"), //Usuário com permissões mais restritas, capaz de acessar e realizar algumas ações específicas, mas sem controle total. criar, editar, excluir, visualizar
    CONSULTOR_NACIONAL("Consultor Nacional"),//Usuário comum, com permissões muito limitadas, geralmente apenas para visualizar dados ou realizar ações específicas. VIZUALIZAR TUDO
    OPERADOR_NACIONAL("Operador Nacional"),//Usuário comum, com permissões muito limitadas, geralmente apenas para visualizar dados ou realizar ações específicas.criar, editar, excluir, visualizar TUDO
    CONSULTOR_ESTADUAL("Consultor Estadual"),//Usuário comum, com permissões muito limitadas, geralmente apenas para visualizar dados ou realizar ações específicas.VIZUALIZAR(UF)
    CONSULTOR_MUNICIPAL("Consultor Municipal"),//Usuário comum, com permissões muito limitadas, geralmente apenas para visualizar dados ou realizar ações específicas.VIZUALIZAR(MUNICIPIO)
    OPERADOR_ESTADUAL("Operador Estadual"),//Usuário comum, com permissões muito limitadas, geralmente apenas para visualizar dados ou realizar ações específicas.criar, editar, excluir, visualizar (UF)
    OPERADOR_MUNICIPAL("Operador Municipal"),//Usuário comum, com permissões muito limitadas, geralmente apenas para visualizar dados ou realizar ações específicas.criar, editar, excluir, visualizar (MUNICIPIO)
    ADM_ESTADUAL("Administrador_estadual"),
    VISITANTE("Visitante");

    private String descricao;

    Funcao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}