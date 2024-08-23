# Sistema de Adoção de Animais - UNINTER

Este projeto é um sistema de adoção de animais desenvolvido como parte de uma atividade extensionista da UNINTER. Ele utiliza Spring Boot e PostgreSQL para gerenciar informações de animais disponíveis para adoção, com cadastro de usuários e autenticação via JWT.

## Funcionalidades

- Registro de animais para adoção com imagens.
- Listagem e filtragem de animais por espécie, gênero e porte.
- API REST para interação com o backend.
- Armazenamento seguro de imagens.

## Tecnologias
- **Backend**: Java, Spring Boot, Spring Security, Spring Data JPA
- **Banco de Dados**: PostgreSQL
- **Containerização**: Docker

## Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/GuiAmaralD/sistema-adocao-uninter.git
   
1. Navegue até o diretório do projeto e instale as dependências:
   ```bash
   cd sistema-adocao-uninter
    ./mvnw clean install
3. Configure o banco de dados PostgreSQL no application.properties.
4. Inicie a aplicação:
   ```bash
   ./mvnw spring-boot:run

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
   
