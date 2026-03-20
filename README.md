# autoescola
- projeto Java Back End com Spring Boot para uma Auto Escola

# Integrantes
- rm553187 - Gabriel Borba
- rm553842 - Gustavo Gouvêa
- rm554223 - Pedro Henrique Mello


# MySQL
- iniciar servidor por cmd
    ```powershell
        net start mysql95
    ```
- Alterar Username e Password em application.properties
- Manipulação do banco pelo MySQL Workbench
    - criar banco de dados
        - Drop Database auto_escola
        - Create Database auto_escola


# Dados para POSTMAN
- Usuários Básicos para Login
    - Postman
        ```json
        {
            "login": "adminLogin",
            "senha": "admin"
        }
        ```
        ou
        ```json
        {
            "login": "commonLogin",
            "senha": "common"
        }
        ```


- Body da requisição Post (Instrutor: http://localhost:8080/instrutores)
    ```json
    {
        "nome": "Serjo",
        "email": "serjo@gmail.com",
        "cnh": "93847352617",
        "especialidade": "ONIBUS",
        "endereco":{
            "logradouro": "Rua Ibo",
            "numero": "300",
            "complemento": "Apto. 91",
            "bairro": "Vila Regente Feijo",
            "cidade": "São Paulo",
            "uf": "SP",
            "cep": "03346000"
        }
    }
    ```
    ou

    ```json
    {
        "nome": "{{$randomFullName}}",
        "email": "{{$randomEmail}}",
        "telefone": "{{$randomPhoneNumber}}",
        "cnh": "01234567890",
        "especialidade": "CAMINHOES",
        "endereco": {
            "logradouro": "{{$randomStreetName}}",
            "numero": "{{$randomInt}}",
            "complemento": "Apto. {{$randomInt}}",
            "bairro": "{{$randomCity}}",
            "cidade": "{{$randomCity}}",
            "uf": "SP",
            "cep": "87654321"
        }
    }
    ```

# Endpoints
- Ex
    - http://localhost:8080/instrutores?size=5&page=0
        - página 1(0) da lista de instrutores paginados de 5 em 5
    - http://localhost:8080/instrutores?sort=nome&size=2&page=1
        - página 2(1) da lista de instrutores paginados de 2 em 2 e ordenados por nome
