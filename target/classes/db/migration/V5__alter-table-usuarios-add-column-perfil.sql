-- dialect: mysql

Alter Table USUARIOS
Add
(
    perfil  VarChar(10)  Not Null
);

Update USUARIOS
Set
    perfil = "USER";