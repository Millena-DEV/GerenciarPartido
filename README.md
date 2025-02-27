# Sistema de Gerenciamento de Partido Político

Este sistema tem como objetivo gerenciar informações relacionadas a partidos políticos, incluindo filiados, municípios, e candidatos. Ele fornece uma API que permite buscar dados sobre os filiados, municípios e candidatos, e também apresenta gráficos interativos com o **ApexCharts** para facilitar a visualização das informações.

## 🚀 Funcionalidades

- **API RESTful** para:
  - Buscar **filiados** registrados em partidos políticos.
  - Buscar **candidatos** e suas informações.
  - Buscar **municípios** e seus dados associados.
  
- **Gráficos interativos** com **ApexCharts**:
  - Visualização de dados de filiados, candidatos e municípios.
  - Gráficos dinâmicos para facilitar a análise das informações.

## 💻 Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java baseadas em microservices.
- **Spring Security**: Implementação de autenticação e autorização para a aplicação.
- **CSV**: Formato utilizado para exportar e importar dados de filiados, candidatos e municípios.
- **PostgreSQL**: Banco de dados relacional para armazenar informações.
- **Bootstrap 5**: Framework CSS para estilizar a interface de usuário.

## 📊 Tecnologias para Gráficos

- **ApexCharts**: Biblioteca JavaScript para criar gráficos interativos e dinâmicos.

## 🔧 Como Executar o Projeto

### Pré-requisitos

- **Java 17** ou superior.
- **Maven** ou **Gradle** para gerenciamento de dependências.
- **PostgreSQL** instalado e configurado.

### Passos para Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/Millena-DEV/Sistema-Politico.git
   cd Sistema-Politico
   
###Configure seu Banco de dados 
- **Jspring.datasource.url=jdbc:postgresql://localhost:5432/sistema_politico7**
- **Jspring.datasource.username=seu_usuario7**
- **Jspring.datasource.password=sua_senha7**

