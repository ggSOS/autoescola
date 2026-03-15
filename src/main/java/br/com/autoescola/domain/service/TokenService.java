package br.com.autoescola.domain.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.autoescola.domain.model.Usuario;
import br.com.autoescola.infra.exception.TokenGenerationException;
import br.com.autoescola.infra.exception.TokenValidationException;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;


    private String issuer = "autoescola;";


    public String gerarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new TokenGenerationException(
                "Erro ao gerar o Token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new TokenValidationException(
                "Token inválido ou expirado", exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00"));
    }

}
