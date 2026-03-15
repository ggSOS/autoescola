-- dialect:mysql

Alter Table INSTRUTORES
Add
(
    ativo TinyInt
);

Update INSTRUTORES
Set
    ativo = 1;