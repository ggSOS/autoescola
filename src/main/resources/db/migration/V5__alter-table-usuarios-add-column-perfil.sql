-- dialect: mysql

Alter Table usuarios
Add
(
    perfil  VarChar(10)  Not Null
);

Update usuarios
Set
    perfil = "USER";