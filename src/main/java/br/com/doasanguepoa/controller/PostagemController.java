package br.com.doasanguepoa.controller;

import br.com.doasanguepoa.model.Postagem;
import io.quarkus.panache.common.Sort;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


import java.util.List;

@Path("/postagens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostagemController {

    @GET
    public List<Postagem> listarPostagens() {
        return Postagem.listAll(Sort.by("titulo"));
    }

    @GET
    @Path("/{id}")
    public Postagem buscarPostagemPorId(@PathParam Long id) {
        return Postagem.findById(id);
    }

    @POST
    @Transactional
    public Postagem adicionarPostagem(@Valid Postagem postagem) {
        postagem.persist();
        return postagem;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Postagem atualizarPostagem(@PathParam Long id, @Valid Postagem postagem) {
        Postagem entity = Postagem.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Postagem com ID " + id + " não encontrada.", 404);
        }

        entity.setTitulo(postagem.getTitulo());
        entity.setMensagem(postagem.getMensagem());

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletarPostagem(@PathParam Long id) {
        Postagem entity = Postagem.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Postagem com ID " + id + " não encontrada.", 404);
        }
        entity.delete();
    }
}
