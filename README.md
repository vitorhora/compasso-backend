# backend-springboot
Projeto responsável por implementar estudo de caso com APIs REST, utilizando Spring Boot, JPA e Banco de Dados H2.

Desafio: https://github.com/gustavodallanora/spring-boot-interview

## Arquitetura

Visão de arquitetura.

![alt text](https://github.com/vitorhora/compasso-backend/blob/master/cliente/imagens/Arquitetura_Back.png)

## Executando o Projeto

Pré-requisito:

* Java 8 
* Maven configurado 
* Spring Tool Suite 4.


1. git clone https://github.com/vitorhora/compasso-backend.git

2. Maven Update no projeto.

3. Na classe "ClienteApplication", botão direito (Run ou Debug as Spring Boot App).

4. Verificar qual porta a aplicação foi implantada, normalmente é 8080.

5. Executar as chamadas no seu cliente favorito, exemplo SOAPUI ou via swagger disponibilizado no projeto (http://localhost:8080/swagger-ui.html#/cidade-api e http://localhost:8080/swagger-ui.html#/cliente-api).

Exemplo de API GET: http://localhost:8080/api/v1/cidades

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



