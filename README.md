# Super Burger

Aplicação para controle de pedidos.

## Pré-Requisitos

### Executar os testes ou modo Desenvolvimento
- Java 8
- Node 8 ou superior

### Executar em modo Produção
- Docker
- Docker Compose

## Executando os testes 
Execute os testes utilizando o maven.
```
mvn test
```

## Executando a aplicação

### Modo Desenvolvimento
Execute a aplicação spring boot utilizando o maven.
```
mvn clean spring-boot:run
```
Se tudo correr bem, ao acessar a url http://localhost:8080/pedido/cardapio
será possível visualizar uma saida de lanches em json.
Caso não esteja vendo essa saida, verifique se não possui outra aplicação utilizando a porta 8080.

Agora vá ao diretório da aplicação React e instale as dependências e execute a aplicação.
```
cd ./client
npm install
npm start
```
Acesse a url http://localhost:8000 e você estará no site do Super Burger.

### Modo Produção
Execute o comando up do docker-compose para executar a aplicação.
```
docker-compose up
```
Pode demorar um pouco para o docker baixar as dependências.
Assim que terminar, acesse a url  http://localhost

## Observação 
* A quantidade máxima que um cliente pode pedir de um ingrediente é 10. 

## Construído com

* [Spring Boot](https://spring.io/projects/spring-boot) - Spring Web Framework
* [React](https://reactjs.org/) - A JavaScript library for building user interfaces
* [UmiJS](https://umijs.org/) - Pluggable enterprise-level react application framework
* [Ant Design](https://ant.design/) - Out-of-box UI solution for enterprise applications
