-- dialect:mysql

Create Table usuarios(
    id              BigInt          Not Null    Auto_increment,
    login           VarChar(100)    Not Null    Unique,
    senha           VarChar(255)    Not Null,
    Primary Key(id)
);