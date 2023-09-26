package br.com.doasanguepoa.controller;

import br.com.doasanguepoa.model.Usuario;
import br.com.doasanguepoa.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

    @Inject
    UsuarioService usuarioService;

    @GET
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try{
            usuarios = usuarioService.listarUsuarios();
        }catch (Exception e){
            e.printStackTrace();
        }

        return usuarios;
    }

    @GET
    @Path("/{id}")
    public Usuario buscarUsuarioPorId(@PathParam Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }

    @POST
    @Transactional
    public Usuario adicionarUsuario(@Valid Usuario usuario) {
        usuarioService.adicionarUsuario(usuario);
        return usuario;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Usuario atualizarUsuario(@PathParam Long id, @Valid Usuario usuario) {
        Usuario entity = usuarioService.buscarUsuarioPorId(id);
        if (entity == null) {
            throw new WebApplicationException("Usuário com ID " + id + " não encontrado.", 404);
        }

        entity.setNome(usuario.getNome());
        entity.setEndereco(usuario.getEndereco());
        entity.setCpf(usuario.getCpf());
        entity.setEmail(usuario.getEmail());
        entity.setSenha(usuario.getSenha());

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletarUsuario(@PathParam Long id) {
        Usuario entity = usuarioService.buscarUsuarioPorId(id);
        if (entity == null) {
            throw new WebApplicationException("Usuário com ID " + id + " não encontrado.", 404);
        }
        //entity.delete();
    }
}


