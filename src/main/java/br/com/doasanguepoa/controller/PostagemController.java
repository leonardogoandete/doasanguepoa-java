package br.com.doasanguepoa.controller;

import br.com.doasanguepoa.model.Postagem;
import br.com.doasanguepoa.service.PostagemService;
import jakarta.inject.Inject;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Path("/postagens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostagemController {

    @Inject
    PostagemService postagemService;

    @GET
    public List<Postagem> listarPostagens() {
        List<Postagem> postagens = new ArrayList<>();

        try{
            postagens = postagemService.listarPostagens();
        }catch (Exception e){
            e.printStackTrace();
        }

        return postagens;
    }


    @GET
    @Path("/{id}")
    public Postagem buscarPostagemPorId(@PathParam Long id) {
        return postagemService.buscarPostagemPorId(id);
    }

    @POST
    @Transactional
    public Postagem adicionarPostagem(@Valid Postagem postagem) {
        postagemService.adicionarPostagem(postagem);
        return postagem;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Postagem atualizarPostagem(@PathParam Long id, @Valid Postagem postagem) {
        Postagem entity = postagemService.buscarPostagemPorId(id);
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
        Postagem entity = postagemService.buscarPostagemPorId(id);
        if (entity == null) {
            throw new WebApplicationException("Postagem com ID " + id + " não encontrada.", 404);
        }
        //entity.delete();
    }
}
