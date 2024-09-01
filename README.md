# Emprestimo API

Este é um projeto Java com Spring Boot para gerenciar empréstimos. A aplicação fornece uma API RESTful que permite criar, atualizar, buscar e deletar pessoas, pagamentos e empréstimos.

## Tecnologias Usadas

- **Spring Boot 3.3.3**: Framework principal para a construção da aplicação.
- **Spring Data JPA**: Utilizado para persistência de dados com o banco de dados relacional.
- **Spring Boot Starter Tomcat**: Usado para empacotar o aplicativo como um arquivo WAR para implantação em um servidor Tomcat.
- **PostgreSQL**: Banco de dados relacional usado em produção.
- **SpringDoc OpenAPI**: Gera documentação da API automaticamente.
- **ModelMapper**: Utilizado para converter DTOs para entidades e vice-versa.
- **Docker**: Para containerização e gerenciamento de serviços como o banco de dados PostgreSQL.

## Requisitos

- **Java 17** ou superior
- **Maven 3.8.1** ou superior
- **Docker** instalado e em execução

## Instalação e Configuração

1. **Clone o repositório:**  
   git clone https://github.com/axandrade/emprestimo-api.git
   cd emprestimo-api
   git checkout master
   
2. **Configuração do Banco de Dados com Docker:**
	Certifique-se de que o Docker está instalado e em execução em sua máquina.
	Execute o script docker-build.bat para configurar e iniciar o banco de dados PostgreSQL em um contêiner Docker.
	Este script irá:
		Mudar para o diretório do script.
		Usar o arquivo docker-compose.yml para criar e iniciar o container PostgreSQL.
		Configurar o banco de dados emprestimo-db com as credenciais especificadas.		

3. **Compilar e executar a aplicação:**
	mvn clean install
	
## Documentação da API

A documentação da API é gerada automaticamente usando o SpringDoc OpenAPI. Você pode acessar a documentação da API quando o aplicativo estiver em execução em http://localhost:8080/swagger-ui.html.

	
