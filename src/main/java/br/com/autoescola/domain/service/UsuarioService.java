package br.com.autoescola.domain.service;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.autoescola.domain.dto.usuario.AlterarSenhaDTO;
import br.com.autoescola.domain.dto.usuario.DadosDetalhamentoUsuarioDTO;
import br.com.autoescola.domain.dto.usuario.ListagemUsuarioDTO;
import br.com.autoescola.domain.dto.usuario.UsuarioCreateDTO;
import br.com.autoescola.domain.dto.usuario.UsuarioUpdateDTO;
import br.com.autoescola.domain.model.Usuario;
import br.com.autoescola.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public DadosDetalhamentoUsuarioDTO cadastrar(UsuarioCreateDTO dados) {
        String senhaEncriptada = passwordEncoder.encode(dados.senha());
        Usuario usuario = new Usuario(dados, senhaEncriptada);
        repository.save(usuario);
        return new DadosDetalhamentoUsuarioDTO(usuario);
    }

    public Page<ListagemUsuarioDTO> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(ListagemUsuarioDTO::new);
    }

    public DadosDetalhamentoUsuarioDTO detalhar(Long id) {
        Usuario usuario = repository.getReferenceById(id);
        return new DadosDetalhamentoUsuarioDTO(usuario);
    }

    @Transactional
    public DadosDetalhamentoUsuarioDTO atualizar(UsuarioUpdateDTO dados) {
        Usuario usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);
        return new DadosDetalhamentoUsuarioDTO(usuario);
    }

    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void alterarSenha(@Nonnull AlterarSenhaDTO dados) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();

        if (!passwordEncoder.matches(dados.senhaAtual(), usuario.getPassword())) {
            throw new RuntimeException("Senha atual incorreta");
        }

        String novaSenhaEncriptada = passwordEncoder.encode(dados.novaSenha());
        usuario.alterarSenha(novaSenhaEncriptada);
        repository.save(usuario);
    }
}
