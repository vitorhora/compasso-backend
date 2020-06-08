# backend-springboot
Levando em consideração o “Time to Market” curto disponível para entrega, venho elencar alguns pontos do MVP. 

![alt text](https://github.com/vitorhora/compasso-backend/blob/master/cliente/imagens/Arquitetura_Back.png)

## Executando o Projeto

```
Pré-requisito:
Java 8
Maven configurado
Spring Tool Suite 4

1-git clone https://github.com/vitorhora/compasso-backend.git
2-Na classe "ClienteApplication", botão direito (Run ou Debug as Spring Boot App)
3-Verificar qual porta a aplicação foi deployada, normalmente é 8080.
4-Executar os requests no seu cliente favorito, exemplo SOAPUI, ou via swagger (http://localhost:8080/swagger-ui.html#/cidade-api e http://localhost:8080/swagger-ui.html#/cliente-api)

Exemplo de API GET: http://localhost:8080/http://localhost:8080

```

## Pontos de Destaque
```
    -DTO para proteger as entidades de persistência. 

    -Camadas com responsabilidades bem definidas, exposição, negócio e persistência.  

    -Swagger para facilitar a leitura das APIs.  

    -Contexto funcional bem demarcado, juntamente com versionamento de API.  

    -Implementação de HTTP Codes minimamente necessários.  

    -Uso eficiente do framework para acelerar a entrega.  

    -Banco de dados embutido no projeto, visando agilidade no desenvolvimento.  

    -SOAPUI e Swagger-UI como opções entregues para realizar testes e entendimento dos inputs. 
```

## Melhorias
```
    -Implementar planos de Throttle.  

    -Levando em consideração apenas elementos técnicos, seria mais elegante o desenvolvimento de 2 projetos distintos, cada um com seu contexto funcional (Cliente ou Cidade), realizando a comunicação entre eles através da API (Cliente -> Cidade), e não via repositório. Essa escolha iria incrementar elementos como alta coesão, representar uma API por tabela, escalabilidade 	independente, etc. Optei por implementar em apenas um projeto, pois não ira prejudicar o resultado 	final (dados requisitos iniciais), e ajudou na velocidade de desenvolvimento.  

    -Criar compartilhamento corporativo de entidades.  

    -Criar políticas padronizadas de tratamento de erros de negócio e técnico.  

    -Definição de políticas não intrusivas (de preferência), para monitoramento da saúde do WS. 

    -Implementação casos de testes. 
```


## Projeto SOAPUI

```
Projeto que simula o consumo das APIs, via aplicativo.
https://www.soapui.org/downloads/soapui/

Fonte localizaod em: ./soapui/Cliente API Compasso.xml

```


## Banco de Dados H2

```
Ao iniciar o projeto, ele será criado automaticamente na pasta ./data

```

