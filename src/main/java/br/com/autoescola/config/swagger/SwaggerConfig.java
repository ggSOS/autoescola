package br.com.autoescola.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ChatBot DataBase BackEnd - API REST")
                        .version("1.0.0")
                        .description(
                                """
                                        # autoescola
                                        - projeto Java Back End com Spring Boot para uma Auto Escola

                                        # Integrantes
                                        - rm553187 - Gabriel Borba
                                        - rm553842 - Gustavo Gouvêa
                                        - rm554223 - Pedro Henrique Mello

                                        # MySQL
                                        - iniciar servidor por cmd
                                            ```powershell
                                                net start mysql95
                                            ```
                                        - Alterar Username e Password em application.properties
                                        - Manipulação do banco pelo MySQL Workbench
                                            - criar banco de dados
                                                - Drop Database auto_escola
                                                - Create Database auto_escola

                                        # Dados para POSTMAN
                                        - Usuários Básicos para Login
                                            - Postman
                                                ```json
                                                {
                                                    "login": "adminLogin",
                                                    "senha": "admin"
                                                }
                                                ```
                                                ou
                                                ```json
                                                {
                                                    "login": "commonLogin",
                                                    "senha": "common"
                                                }
                                                ```
                                            """)
                        .contact(new Contact()
                                .name("GustavoG")
                                .url("https://github.com/ggSOS")));
    }
}
