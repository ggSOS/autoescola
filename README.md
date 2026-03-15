# javaprojects
- projeto Java Back End com Spring Boot para uma Auto Escola

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
    - USERS Básicos para Login
        - SQL
            ```sql
            INSERT INTO `auto_escola`.`usuarios` (`id`, `login`, `senha`, `perfil`, `email`, `telefone`, `cpf`, `logradouro`, `numero`, `complemento`, `bairro`, `cidade`, `uf`, `cep`, `ativo`) VALUES ('1', 'adminLogin', '$2a$10$nGkOrbgR4iGHOPbR/ikPdO.RfhqB/XoH6//lTfjO6Ipmiy2G7iQ36', 'ADMIN', 'admin@email.com', '12345', '12345678909', 'rua aqui', '76', 'asdasdasd', 'jardim aqui', 'São Paulo', 'SP', '12345678', '1');
            INSERT INTO `auto_escola`.`usuarios` (`id`, `login`, `senha`, `perfil`, `email`, `telefone`, `cpf`, `logradouro`, `numero`, `complemento`, `bairro`, `cidade`, `uf`, `cep`, `ativo`) VALUES ('2', 'commonLogin', '$2a$10$Riola5Oeo6aNdG7MU11OrexuBSHO7vgOZa8jA3G2OpQbqGheqYODq', 'USER', 'user@email.com', '54321', '09876543212', 'rua ali', '23', '', 'vila ali', 'São Paulo', 'SP', '09876543', '1');
            ```
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


# Dados para POSTMAN
```json
{
    "nome": "Charles",
    "email": "Charles@gmail.com",
    "cnh": "01234567890",
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

- Body da requisição Post (Instrutor: http://localhost:8080/instrutores)

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

# Endpoint
- Ex
    - http://localhost:8080/instrutores?size=5&page=0
        - página 1(0) da lista de instrutores paginados de 5 em 5
    - http://localhost:8080/instrutores?sort=nome&size=2&page=1
        - página 2(1) da lista de instrutores paginados de 2 em 2 e ordenados por nome
