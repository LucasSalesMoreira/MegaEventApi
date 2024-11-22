# MegaEventApi
Projeto da cadeira de sistemas distribuidos.

Este é um projeto de software para gerenciamento de eventos, escrito em Kotlin usando Spring Boot e MySQL como banco de dados. A API é conteinerizada para facilitar o desenvolvimento e a implantação.

## Funcionalidades Principais

- **CRUD para Eventos**
  - Gerenciamento de eventos com informações como nome, local, data, tipo de evento e vagas disponíveis.

- **CRUD para Inscrições**
  - Cada usuário pode se inscrever em eventos, com regras para controlar o número de inscritos e evitar duplicidade.

- **CRUD para Usuários**
  - Permite listar eventos em que os usuários estão inscritos e ver o histórico de participação.

- **Listagem de Eventos**
  - Permite buscas por data, tipo e localidade.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal.
- **Spring Boot**: Framework para construção de aplicações.
- **MySQL**: Banco de dados relacional.
- **Docker**: Conteinerização da API para facilitar a implantação.

## Pré-requisitos

- Docker e Docker Compose instalados.
- Java 17 ou superior.
- MySQL instalado e configurado.

## Como Rodar o Projeto

1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/event-management-system.git
   cd event-management-system
