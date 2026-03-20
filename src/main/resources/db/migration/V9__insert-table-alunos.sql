-- dialect: mysql

Insert Into `auto_escola`.`alunos`
(`id`, `nome`, `email`, `telefone`, `cpf`, `logradouro`, `numero`, `complemento`, `bairro`, `cidade`, `uf`, `cep`, `ativo`)
Values
('1', 'Leticio', 'leticio@email.com', '837495305', '34567890964', 'rua daqui perto', '1', 'torre 1', 'vila perto', 'Osasco', 'SP', '93726837', '1');

Insert Into `auto_escola`.`alunos`
(`id`, `nome`, `email`, `telefone`, `cpf`, `logradouro`, `bairro`, `cidade`, `uf`, `cep`, `ativo`)
Values
('2', 'Emilton', 'emilton@email.com', '273946173', '23836384632', 'rua bem longe', 'jardim longe', 'Belford Roxo', 'RJ', '17362567', '0');