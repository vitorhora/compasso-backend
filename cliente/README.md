# front-github
Levando em considera��o o �Time to Market� curto, dispon�vel para entrega, venho elencar alguns pontos do MVP entregue. 

![alt text](./imagens/Arquitetura_Back.png)

## Pontos de Destaque
```
    -DTO para proteger as entidades de persist�ncia. 

    -Camadas com responsabilidades bem definidas, exposi��o, neg�cio e persist�ncia.  

    -Swagger para facilitar a leitura das APIs.  

    -Contexto funcional bem demarcado, juntamente com versionamento de API.  

    -Implementa��o de HTTP Codes minimamente necess�rios.  

    -Uso eficiente do framework para acelerar a entrega.  

    -Banco de dados embutido no projeto, visando agilidade no desenvolvimento.  

    -SOAPUI e Swagger-UI como op��es entregues para realizar testes e entendimento dos inputs. 
```

## Melhorias
```
    -Implementar planos de Throttle.  

    -Levando em considera��o apenas elementos t�cnicos, seria mais elegante o desenvolvimento de 2 	projetos distintos, cada um com seu contexto funcional (Cliente ou Cidade), realizando a 	comunica��o entre eles atrav�s da API (Cliente -> Cidade), e n�o via reposit�rio.Essa escolha 	iria incrementar elementos como alta coes�o, representar uma API por tabela, escalabilidade 	independente, etc. Optei por implementar em apenas um projeto, pois n�o ira prejudicar o resultado 	final (dados requisitos iniciais), e ajudou na velocidade de desenvolvimento.  

    -Criar compartilhamento corporativo de entidades.  

    -Criar pol�ticas padronizadas de tratamento de erros de neg�cio e t�cnico.  

    -Defini��o de pol�ticas n�o intrusivas (de prefer�ncia), para monitoramento da sa�de do WS. 

    -Implementa��o casos de testes. 
```



## Backends consumidos. 

```
Endpoint user: https://api.github.com/users/USER_GITHUB

Endpoint repos: https://api.github.com/users/USER_GITHUB/repos

Endpoint starred: https://api.github.com/users/USER_GITHUB/starred{/owner}{/repo}
```
## Projeto SOAPUI

```
Projeto que simula o consumo das APIs, via aplicativo.
https://www.soapui.org/downloads/soapui/

Fonte localizaod em: ./soapui/Cliente API Compasso.xml

```

