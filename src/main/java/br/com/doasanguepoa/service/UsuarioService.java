package br.com.doasanguepoa.service;

import br.com.doasanguepoa.model.Usuario;
import br.com.doasanguepoa.repository.UsuarioRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.wildfly.security.password.interfaces.BCryptPassword;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listAll();
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void adicionarUsuario(Usuario usuario) {

        String senhaHash = BcryptUtil.bcryptHash(usuario.getSenha());

        usuario.setSenha(senhaHash);

        usuarioRepository.persist(usuario);
    }
}
