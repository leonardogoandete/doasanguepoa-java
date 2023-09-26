package br.com.doasanguepoa.controller;

import br.com.doasanguepoa.model.Usuario;
import io.quarkus.panache.common.Sort;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

    @GET
    public List<Usuario> listarUsuarios() {
        return Usuario.listAll(Sort.by("nome"));
    }

    @GET
    @Path("/{id}")
    public Usuario buscarUsuarioPorId(@PathParam Long id) {
        return Usuario.findById(id);
    }

    @POST
    @Transactional
    public Usuario adicionarUsuario(@Valid Usuario usuario) {
        usuario.persist();
        return usuario;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Usuario atualizarUsuario(@PathParam Long id, @Valid Usuario usuario) {
        Usuario entity = Usuario.findById(id);
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
        Usuario entity = Usuario.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Usuário com ID " + id + " não encontrado.", 404);
        }
        entity.delete();
    }
}


