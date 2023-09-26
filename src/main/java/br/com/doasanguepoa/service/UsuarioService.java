package br.com.doasanguepoa.service;

import br.com.doasanguepoa.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UsuarioService {

    public List<Usuario> listarUsuarios() {
        return Usuario.listAll();
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return Usuario.findById(id);
    }
}
