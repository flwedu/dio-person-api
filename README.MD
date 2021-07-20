# dio-person-api

Um simples projeto de uma API Rest para disponibilizar dados de Pessoas
e seus respectivos números de telefone.
Criado durante o **Santander Bootcamp | Fullstack Developer**, em parceria com a [Digital Innovation One](https://web.digitalinnovation.one/) em 2021.

## Executando a API
Para rodar o projeto localmente, basa executar:
```Bash
mvn spring-boot:run 
```
E então o projeto pode ser acessado em `http://localhost:8080/api/v1/people`.

A API possui apenas alguns endpoints:

### GET

- `api/v1/people`: retorna uma lista com todos os elementos inseridos.
- `api/v1/people/{id}`: substitua `{id}` por algum número para buscar
algum elemento pelo Id.

Estrutura de dados recebida:
```json
[
    {
        "id": 122,
        "firstName": "John",
        "lastName": "Snow",
        "cpf": "000.000.111-22",
        "phones": [
              {
                "type": "MOBILE",
                "number": "(00)00000-0000"
              }
          ]
    },
    {
        "id": 123,
        "firstName": "Bran",
        "lastName": "Snow",
        "cpf": "000.000.111-22",
        "phones": [
              {
                "type": "MOBILE",
                "number": "(00)22222-4444"
              }
          ]
    },
]
```

### POST
- `api/v1/people`: para salvar um novo elemento.

Estrutura de dados aceita:
```json
{
    "firstName": "Ned",
    "lastName": "Stark",
    "cpf": "000.000.111-22",
    "phones": [
          {
            "type": "MOBILE",
            "number": "(00)00000-0000"
          },
          {
            "type": "HOME",
            "number": "(00)00000-0000"
          }
      ]
}
```

### PUT
- `api/v1/people/{id}`: substitua `{id}` pelo id do elemento que se deseja atualizar.
Os novos dados devem ser enviados no body requisição.
  
### DELETE
- `api/v1/people/{id}`: substitua `{id}` pelo id do elemento que se deseja apagar.

## Tecnologias utilizadas para o desenvolvimento
- [Java JDK 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Lombok](https://projectlombok.org/)
- [Hibernate](https://hibernate.org/)
- [H2 Database](https://www.h2database.com/html/main.html)
- [MapStruct](https://mapstruct.org/)
- [Junit 5](https://junit.org/junit5/)

