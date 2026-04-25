-- dialect: mysql

Create Table instrucoes(
    id                      BigInt          Not Null    Auto_increment,
    aluno_id                BigInt          Not Null,
    instrutor_id            BigInt          Not Null,
    data                    DateTime        Not Null,
    motivo_cancelamento     VarChar(100),
    Primary Key(id),
    Constraint fk_instrucoes_aluno_id Foreign Key(aluno_id) References alunos(id),
    Constraint fk_instrucoes_instrutor_id Foreign Key(aluno_id) References instrutores(id)
);