package br.com.autoescola.config.security;

import br.com.autoescola.config.security.filter.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
// @EnableWebSecurity permitiria autorizazao nos proprios controllers
public class SecurityConfigurations {

        private final SecurityFilter securityFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(AbstractHttpConfigurer::disable)
                                // .cors(cors -> cors
                                //                 .configurationSource(request -> {
                                //                         CorsConfiguration corsConfiguration = new CorsConfiguration();
                                //                         corsConfiguration.setAllowedOrigins(
                                //                                         List.of(
                                //                                                         "https://meusite.com.br",
                                //                                                         "https://127.128.0.10:8080"));
                                //                         corsConfiguration.setAllowedMethods(
                                //                                         List.of(
                                //                                                         "GET",
                                //                                                         "POST"));
                                //                         corsConfiguration.setAllowedHeaders(
                                //                                         List.of(
                                //                                                         "Authorization",
                                //                                                         "Content-TYP"));
                                //                         corsConfiguration.setAllowCredentials(true);
                                //                         return corsConfiguration;
                                //                 }))
                                // alternativa para o spring security
                                .sessionManagement(sm -> sm
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(
                                                                "/login",
                                                                "/health-check",
                                                                "/error",
                                                                "/v3/api-docs/**",
                                                                "/swagger-ui/**",
                                                                "/swagger-ui.html")
                                                .permitAll()

                                                .requestMatchers(HttpMethod.GET,
                                                                "/instrutores/**",
                                                                "/alunos/**")
                                                .permitAll()

                                                .requestMatchers(
                                                                "/instrutores/**",
                                                                "/alunos/**",
                                                                "/instrucoes/**")
                                                .hasAnyRole("ADMIN", "OWNER")

                                                .anyRequest()
                                                .authenticated())
                                .addFilterBefore(
                                                securityFilter,
                                                UsernamePasswordAuthenticationFilter.class)
                                .build();
        }

        @Bean
        public AuthenticationManager authenticationManager(
                        AuthenticationConfiguration configuration) throws Exception {
                return configuration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
