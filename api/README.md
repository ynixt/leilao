# Leilão API

API de um MVP de leilão

## Tecnologias
- Java 14
- SpringBoot
- Hibernate
- Swagger
- Autenticação com JWT
- JUnit

## Executando

###Requisitos
- Java 14
- Banco de dados MYSQL

###Passo a passo

1. Importe este projeto como um projeto Gradle
2. Copie o arquivo `src/main/resources/config/application-dev.yml.modelo`
para `src/main/resources/config/application-dev.yml`
3. Edite o arquivo application-dev.yml e insira as informações do seu banco local
4. Execute a classe principal LeilaoApplication

###Testes unitários
1. Execute `gradlew run tests` ou execute os testes presentes em `src/main/test`

## Swagger - Documentação da API
Para visualizar a documentação da API, execute aplicação e então o swagger poderá ser acessado através
da URL http://localhost:8080/swagger-ui