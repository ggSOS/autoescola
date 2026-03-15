-- dialect: mysql

Alter Table USUARIOS
Add
(
	email		    VarChar(100)    Not Null    Unique,
	telefone	    VarChar(20) 	Not Null,
	cpf             VarChar(11)     Not Null    Unique,
	logradouro      VarChar(255)    Not Null,
    numero          VarChar(20)    	Not Null,
	complemento     VarChar(100),
    bairro          VarChar(100)    Not Null,
    cidade          VarChar(100)    Not Null,
    uf              VarChar(2)      Not Null,
    cep             VarChar(8)      Not Null,
	ativo		    TinyInt
);

Update USUARIOS
Set	telefone	= "0",
	logradouro 	= "naoRegistrado",
	numero		= "0",
	complemento	= "naoRegistrado",
	bairro		= "naoRegistrado",
	cidade		= "naoRegistrado",
	uf		    = "n",
	cep		    = "0",
	ativo		= 1;
