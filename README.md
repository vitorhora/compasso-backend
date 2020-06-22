# backend-springboot
Projeto responsável por implementar microserviços RESTful, utilizando Spring Boot, JPA, Banco de Dados H2 e containers Docker.

## Caso de Uso

Operações expostas como endpoints REST para:

    Cadastrar cidade
    Cadastrar cliente
    Consultar cidade pelo nome
    Consultar cidade pelo estado
    Consultar cliente pelo nome
    Consultar cliente pelo Id
    Remover cliente
    Alterar o nome do cliente

Considere o cadastro com dados básicos:

    Cidades: nome e estado
    Cliente: nome completo, sexo, data de nascimento, idade e cidade onde mora.


## Arquitetura

Visão Arquitetura Projeto.

![Alt text](/artefatos/imagens/Arquitetura_Projeto.png?raw=true "Arquitetura Projeto")



Visão Arquitetura Deployment.

![Alt text](/artefatos/imagens/Arquitetura_Deployment.png?raw=true "Arquitetura Deployment")

## Executando Projeto

Pré-requisito:

* Java 8 
* Maven 
* Docker

### Banco H2 Docker

1. Baixar imagem do H2.
	
	```
	docker pull oscarfonts/h2
	```
2. Executar imagem.

	O comando irá habilitar porta para acesso e configurar volume persistente, com objetivo de tormar o dado não efêmero.
	```
	docker run -d -p 1521:1521 -p 81:81 -v /path/to/local/data_dir:/opt/h2-data -e H2_OPTIONS='-ifNotExists' --name=MyH2Instance oscarfonts/h2
	```
3. Verificar IP que o serviço ficou disponível.

	```
	docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' MyH2Instance
	```
	OBS: Esse IP servirá para configurar o "application.properties", referente a conexão com o BD dos microserviços, que será solicitado no passo 3.2 - Microserviços.

### Microserviços

1. Clonar os projetos.
	```
	git clone https://github.com/vitorhora/compasso-backend.git
	```

2. Realizar "Maven Update" nos três projetos, para download das dependências.
	* ententidades-corporativas
	* cidade
	* cliente

3. Verificar ou ajustar o IP de conexão com o BD.

	3.1. Navergar até os arquivos "application.properties" dos projetos cidade e cliente respectivamente:	
	```
	\cidade\src\main\resources
	\cliente\src\main\resources
	```
	
	3.2. Na propriedade "spring.datasource.url", verificar ou ajustar, conforme IP mostrado no passo 3, referente a configuração de Banco de Dados.

3. Realizar "Maven clean install" para gerar o ".jar" e ".war" na pasta target dos respectivos projetos, respeitando a seguinte ordem.

	1- ententidades-corporativas
	
	2- cidade
	
	3- cliente

4. Via linha de comando, navegar até pasta raíz do projeto cidade e cliente.

5. Criar a imagem Docker, a partir do arquivo Dockerfile, encontrado na raíz dos respectivos projetos.

	cidade
	```
	docker build -f Dockerfile -t api-cidade-spring .
	```

	cliente
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
	```bash
	docker run -p 8081:8081 api-cidade-spring
	```
	Microserviço cliente.
	```bash
	docker run -p 8080:8080 api-cliente-spring
	```
8. Executar as chamadas no seu cliente favorito, exemplo SOAPUI ou via swagger disponibilizado no projeto (http://localhost:8081/swagger-ui.html#/cidade-api e http://localhost:8080/swagger-ui.html#/cliente-api).

	Exemplo de API GET: http://localhost:8081/api/v1/cidades

	Sugestões de inputs poderão ser encontrados no SOAPUI ou Swagger.



## Projeto SOAPUI


Projeto que simula o consumo das APIs via aplicativo.

https://www.soapui.org/downloads/soapui/

Fonte localizado em: https://github.com/vitorhora/compasso-backend/tree/master/artefatos/soapui



## Banco de Dados H2

Configurações encontradas no arquivo "application.properties" do projeto cidade e cliente.

```
#Quando precisar executar o microserviço ou testes locais, descomentar a conexão "localhost" a conexão via "IP".
#spring.datasource.url=jdbc:h2:tcp://localhost:1521/fswdev
spring.datasource.url=jdbc:h2:tcp://172.17.0.2:1521/fswdev
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

Console: http://localhost:81

## Integração Contínua

O repositógio git está integrado com o repositório de imagens Docker Hub. Todo push realizado no git, automaticamente são disponibilizadas duas imagens no repositório Doker Hub, referentes aos microserviços cidade e cliente.

###Endereço Banco de Dados:
	```
	https://hub.docker.com/r/vitorhora/banco_dados
	```

	Pull na Imagem: 
	```
	docker pull vitorhora/banco_dados:banco_h2
	```

	Executar BD: 
	```
	docker run -d -p 1521:1521 -p 81:81 -v /path/to/local/data_dir:/opt/h2-data -e H2_OPTIONS='-ifNotExists' --name=MyH2Instance <DOCKER ID>
	```


###Endereço Microserviços: 
	```
	https://hub.docker.com/r/vitorhora/microservicos_springboot
	```
	
	
	Pull Microserviço Clientes: 
	```
	docker pull vitorhora/microservicos_springboot:api-cliente-spring
	```

	Iniciar Microserviço Clientes:  
	```
	docker run -p 8080:8080 <DOCKER ID>
	```

	Pull Microserviço Cidades: 
	```
	docker pull vitorhora/microservicos_springboot:api-cidade-spring
	```

	Iniciar Microserviço Cidades: 
	```
	docker run -p 8081:8081 <DOCKER ID>
	```
	

