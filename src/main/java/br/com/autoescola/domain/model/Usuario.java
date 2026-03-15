package br.com.autoescola.domain.model;

import java.util.Collection;
import java.util.List;

import jakarta.annotation.Nonnull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.autoescola.domain.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Role perfil;

    public Usuario(@Nonnull br.com.autoescola.domain.dto.usuario.UsuarioCreateDTO dados, String senhaEncriptada) {
        this.login = dados.login();
        this.senha = senhaEncriptada;
        this.perfil = dados.perfil();
    }

    public void atualizarInformacoes(@Nonnull br.com.autoescola.domain.dto.usuario.UsuarioUpdateDTO dados) {
        if (dados.login() != null) {
            this.login = dados.login();
        }
        if (dados.perfil() != null) {
            this.perfil = dados.perfil();
        }
    }

    public void alterarSenha(String novaSenhaEncriptada) {
        this.senha = novaSenhaEncriptada;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + perfil));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;// default: UserDetails.super.isAccountNonExpired()
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;//default: UserDetails.super.isAccountNonLocked()
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;//default: UserDetails.super.isCredentialsNonExpired()
    }

    @Override
    public boolean isEnabled() {
        return true;//default: UserDetails.super.isEnabled()
    }
}
