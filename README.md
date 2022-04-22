# Desafio Reskilling Java

Apresentação e entrega do Desafio Reskilling Java.

## Conteúdo

- [Pré-requisitos](#pré-requisitos)
- [Execução Aplicação](#execução)
- [Execução Sonarqube](#sonarqube)
- [Links](#links)
- [Tarefa Principal](#tarefa-principal)
- [Tarefa Bônus](#tarefa-bônus)

## Pré-requisitos

É necessário que você tenha instalado em sua máquina:

- [Java SE](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html) (11)
- [Apache Tomcat](https://tomcat.apache.org/download-10.cgi) (10.0.20)
- Recomendamos a IDE [Eclipse](https://www.eclipse.org/downloads/)
   _A instalação deve ser Eclipse IDE for C/C++ Developers

## Execução

1. Clonar repositório git utilizando o comando:

         - git clone https://github.com/elayneargollo/votacao_solutis.git
      
2. Abra o Eclipse e importe o projeto
3. Instale/atualize as dependências do Maven       
4. Execute o programa 

         - Duplo clique em votacao > src/main/java > votacao > VotacaoApplication.java
         - Clique em Run VotacaoApplication
  
5. Acesse a API

         - https://localhost:8080 
         - swagger http://localhost:8080/swagger-ui.html#/

## Sonarqube

1. Realize o dowloand no link:

         - https://www.sonarqube.org/downloads/
      
2. Start o sonar e realize o login:
         
         - http://localhost:9000 por default
         - login padrao (admin/admin)

4. Crie um projeto manualmente e escolha um nome para ele
5. Configure o projeto manualmente inserindo um token válido
6. Execute a análise do Sonarqube na raiz do projeto seguindo as instruções abaixo:

        - mvn clean verificar sonar:sonar \
        - Dsonar.projectKey=<nomeDoProjeto> \
        - Dsonar.host.url=http://localhost:9000\
        - Dsonar.login=<keyDoProjeto>


## Links

###### GitHub - https://github.com/elayneargollo/votacao_solutis
###### Swagger - http://localhost:8080/swagger-ui.html#/
###### Coleção Postman - https://github.com/elayneargollo/votacao_solutis/blob/main/votacao.postman_collection.json
###### Sonarqube - http://localhost:9000/

## Tarefa Principal

Em uma cooperativa, cada associado possui um voto e as decisões são tomadas através de assembleias, por votação. A partir disso, você precisa criar o backend para gerenciar essas sessões de votação. A solução deve atender os seguintes requisitos através de uma API REST: 

- RF1. Cadastrar pauta.

- RF2. Abrir uma sessão de votação para uma pauta.
  
      - Cada pauta deve comportar apenas uma sessão de votação
      - A sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por padrão
      
- RF3. Receber votos dos associados em pautas abertas

      - Os votos são apenas 'Sim'/'Não'
      - Cada associado é identificado por um id único e pode votar apenas uma vez por pauta
      - Registre a data/hora do voto
      
- RF4. Contabilizar os votos e dar o resultado da votação na pauta

      - Exibir vencedor por maioria simples
      - Exibir quantidade de votos para cada um dos grupos 'Sim'/'Não'
      - Exibir percentual para cada um dos grupos 'Sim'/'Não
      
- Para fins de exercício

       - A segurança pode ser abstraída e qualquer chamada para a API pode ser considerada autorizada.
       - Para fins de exercício, você pode cadastrar previamente um conjunto de associados.
       - Implemente testes unitários com ao menos 50% de cobertura de código.
       - Utilize Swagger para documentar sua API.
       - Disponibilize uma coleção do Postman com todos endpoints.
       - Se utilizar um banco de dados diferente do H2, um dockerfile deve ser disponibilizado para o banco de dados.

## Tarefa Bônus

- Tarefa Bônus 1 - Integração com sistemas externos (GET: https://cpf-api-almfelipe.herokuapp.com/cpf/12345678901)
- Tarefa Bônus 2 - Contabilização automática (A rotina de contabilização deve ser executada a cada minuto)
- Tarefa Bônus 3 - Mensageria e filas (Kafka, RebbitMQ ou qualquer outra)
- Tarefa Bônus 4 - Hospede sua API na nuven (Free Tier AWS, Heroku ou outro)
- Tarefa Bônus 5 - Análise de qualidade do código (Sonarqube)
- Tarefa Bônus 6 - Versionamento da API

### Autora

<img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/48841005?s=40&v=4" width="100px;" alt=""/>
 
Feito por Elayne Natália 👋🏽 Entre em contato!

[![Linkedin Badge](https://img.shields.io/badge/-Elayne-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/elayne/)](https://www.linkedin.com/in/elayne-nat%C3%A1lia/) 

