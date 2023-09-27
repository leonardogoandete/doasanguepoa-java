package br.com.doasanguepoa.service;

import br.com.doasanguepoa.dto.usuario.UsuarioDTOComSenha;
import br.com.doasanguepoa.model.Usuario;
import br.com.doasanguepoa.repository.UsuarioRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class UsuarioService {

    public static final Logger LOGGER = Logger.getLogger(UsuarioService.class.getName());
    @Inject
    UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listAll();
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario buscarUsuarioPorCpf(String cpf){
        return usuarioRepository.findByCpf(cpf);
    }

    public void adicionarUsuario(UsuarioDTOComSenha usuarioDTOComSenha) {
        String senhaHash = BcryptUtil.bcryptHash(usuarioDTOComSenha.senha());
        Usuario usuario = new Usuario(usuarioDTOComSenha.nome(), usuarioDTOComSenha.endereco(), usuarioDTOComSenha.email(), senhaHash, usuarioDTOComSenha.cpf());
        LOGGER.log(Level.INFO, "Gravando o usuario: {0}", usuario);
        usuarioRepository.persist(usuario);
    }

    public void deletarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        usuarioRepository.delete(usuario);
    }
}
