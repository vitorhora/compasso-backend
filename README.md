# backend-springboot
Projeto responsável por implementar estudo de caso com APIs REST, utilizando Spring Boot, JPA e Banco de Dados H2.

Desafio: https://github.com/gustavodallanora/spring-boot-interview

## Arquitetura

Visão de arquitetura.

![alt text](https://github.com/vitorhora/compasso-backend/blob/master/cliente/imagens/Arquitetura_Back.png)

## Executando o Projeto

Pré-requisito:

* Java 8 
* Maven 
* Docker


1. Clonar os projetos.
	```
	git clone https://github.com/vitorhora/compasso-backend.git
	```

2. Maven Update nos três projetos, para realizar download das dependências.
	* ententidades-corporativas
	* cidade
	* cliente

3. Maven "clean install" para gerar o .war na pasta target dos respectivos projetos, respeitando a seguinte ordem.

	1- ententidades-corporativas
	
	2- cidade
	
	3- cliente

4. Via linha de comando, navegar até pasta raíz do projeto.

5. Criar a imagem Docker, a partir do arquivo Dockerfile, encontrado na raíz dos respectivos projetos.

	Microserviço cidade
	```
	docker build -f Dockerfile -t api-cidade-spring .
	```

	Microserviço cliente
	```
	docker build -f Dockerfile -t api-cliente-spring .
	```
	OBS: Necessário espaço com "ponto" no final.

6. Verificar a imagem criada.
	```
	docker images
	```
7. Iniciar a aplicação referente a imagem.

	Microserviço cidade.
	```
	docker run -p 8081:8081 api-cidade-spring
	```
	Microserviço cliente.
	```
	docker run -p 8080:8080 api-cliente-spring
	```
8. Executar as chamadas no seu cliente favorito, exemplo SOAPUI ou via swagger disponibilizado no projeto (http://localhost:8081/swagger-ui.html#/cidade-api e http://localhost:8080/swagger-ui.html#/cliente-api).

	Exemplo de API GET: http://localhost:8081/api/v1/cidades

	Sugestões de inputs poderão ser encontrados no SOAPUI ou Swagger.



## Considerações

Tomando como referência o “Time to Market” curto disponível para entrega, elenco alguns pontos do MVP. 

### Destaque

* DTO para trafegar objetos e proteger as entidades de persistência. 

* Camadas com responsabilidades bem definidas ( exposição, negócio e persistência). 

* Contexto funcional das APIs coeso, juntamente com versionamento.  

* Implementação de HTTP Codes minimamente necessários.  

* Uso eficiente do framework para acelerar a entrega.  

* Banco de dados embutido no projeto, visando agilidade no desenvolvimento.  

* SOAPUI e Swagger-UI como opções para realizar testes e entendimento das APIs/Inputs. 



### Melhorias

* Implementar planos de Throttle.  

* Levando em consideração apenas elementos técnicos, seria mais elegante o desenvolvimento de 2 projetos distintos. Cada um com seu contexto funcional (Cliente ou Cidade), realizando possíveis comunicações entre eles através da API (Cliente -> Cidade), e não via repositório, incrementando boas práticas como alta coesão, baixo acoplamento, representação de uma API por tabela, escalabilidade e 	independente. Optei por implementar em apenas um projeto, pois não prejudica o resultado 	final (dados requisitos iniciais), e ajudou na velocidade de desenvolvimento.  

* Criar compartilhamento corporativo de entidades.  

* Criar políticas padronizadas de tratamento de erros de negócio e técnico.  

* Definição de políticas não intrusivas (de preferência), para monitoramento da saúde do WS. 

* Implementação casos de testes. 



## Projeto SOAPUI


Projeto que simula o consumo das APIs, via aplicativo.

https://www.soapui.org/downloads/soapui/

Fonte localizado em: ./soapui/ClienteAPICompasso.xml



## Banco de Dados H2


Ao iniciar o projeto, será criado automaticamente na pasta "./data".



