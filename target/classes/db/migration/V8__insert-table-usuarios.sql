-- dialect: mysql

Insert Into `auto_escola`.`usuarios`
(`id`, `login`, `senha`, `perfil`)
Values
('1', 'adminLogin', '$2a$10$nGkOrbgR4iGHOPbR/ikPdO.RfhqB/XoH6//lTfjO6Ipmiy2G7iQ36', 'ADMIN');

Insert Into `auto_escola`.`usuarios`
(`id`, `login`, `senha`, `perfil`)
Values
('2', 'commonLogin', '$2a$10$Riola5Oeo6aNdG7MU11OrexuBSHO7vgOZa8jA3G2OpQbqGheqYODq', 'USER');