package br.com.autoescola;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptadorDeSenha {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("common");
        System.out.println(hash);
    }
}
