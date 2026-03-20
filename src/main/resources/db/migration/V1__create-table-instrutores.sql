-- dialect:mysql

Create Table INSTRUTORES(
    id              BigInt          Not Null    Auto_increment,
    nome            VarChar(100)    Not Null,
    email           VarChar(100)    Not Null    Unique,
    cnh             VarChar(11)     Not Null    Unique,
    especialidade   VarChar(100)    Not Null,
    logradouro      VarChar(255)    Not Null,
    numero          VarChar(20),
    complemento     VarChar(100),
    bairro          VarChar(100)    Not Null,
    cidade          VarChar(100)    Not Null,
    uf              VarChar(2)      Not Null,
    cep             VarChar(8)      Not Null,
    Primary Key(id)
);