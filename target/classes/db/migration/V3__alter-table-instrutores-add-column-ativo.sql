-- dialect:mysql

Alter Table INSTRUTORES
Add
(
    ativo TinyInt   Not Null
);

Update INSTRUTORES
Set
    ativo = 1;