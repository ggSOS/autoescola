-- dialect: mysql

Create Table alunos(
    id              BigInt          Not Null    Auto_increment,
    nome			VarChar(100)    Not Null,
    email		    VarChar(100)    Not Null    Unique,
	telefone	    VarChar(20) 	Not Null,
	cpf             VarChar(11)     Not Null    Unique,
	logradouro      VarChar(255)    Not Null,
    numero          VarChar(20),
	complemento     VarChar(100),
    bairro          VarChar(100)    Not Null,
    cidade          VarChar(100)    Not Null,
    uf              VarChar(2)      Not Null,
    cep             VarChar(8)      Not Null,
	ativo		    TinyInt,
    Primary Key(id)
);