-- dialect:mysql

Alter Table instrutores
Add
(
    ativo TinyInt   Not Null
);

Update instrutores
Set
    ativo = 1;