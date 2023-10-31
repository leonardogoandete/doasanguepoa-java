package br.com.doasanguepoa.controller;

import br.com.doasanguepoa.dto.usuario.UsuarioDTO;
import br.com.doasanguepoa.dto.usuario.UsuarioDTOComSenha;
import br.com.doasanguepoa.model.Usuario;
import br.com.doasanguepoa.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
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
    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        try{
           List<Usuario> usuarios = usuarioService.listarUsuarios();

            for (Usuario usuario: usuarios) {
                usuariosDTO.add(new UsuarioDTO(usuario.getNome(), usuario.getEndereco(), usuario.getEmail(), usuario.getCpf(), usuario.getAvatar()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return usuariosDTO;
    }

    @GET
    @Path("/{id}")
    public Usuario buscarUsuarioPorId(@PathParam Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }

    @POST
    @Transactional
    public UsuarioDTOComSenha adicionarUsuario(@Valid UsuarioDTOComSenha usuarioDTOComSenha) {
        usuarioService.adicionarUsuario(usuarioDTOComSenha);
        return usuarioDTOComSenha;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({ "USUARIO","ADMIN" })
    public UsuarioDTOComSenha atualizarUsuario(@PathParam Long id, @Valid UsuarioDTOComSenha usuarioDTOComSenha) {
        Usuario entity = usuarioService.buscarUsuarioPorId(id);
        if (entity == null) {
            throw new WebApplicationException("Usuário com ID " + id + " não encontrado.", 404);
        }

        entity.setNome(usuarioDTOComSenha.nome());
        entity.setEndereco(usuarioDTOComSenha.endereco());
        entity.setCpf(usuarioDTOComSenha.cpf());
        entity.setEmail(usuarioDTOComSenha.email());
        entity.setSenha(usuarioDTOComSenha.senha());

        return usuarioDTOComSenha;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed({ "ADMIN" })
    public void deletarUsuario(@PathParam Long id) {
        Usuario entity = usuarioService.buscarUsuarioPorId(id);
        if (entity == null) {
            throw new WebApplicationException("Usuário com ID " + id + " não encontrado.", 404);
        }
        usuarioService.deletarUsuario(id);
    }
}


