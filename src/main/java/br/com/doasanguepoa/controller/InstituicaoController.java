package br.com.doasanguepoa.controller;

import br.com.doasanguepoa.model.Instituicao;
import io.quarkus.panache.common.Sort;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;

@Path("/instituicoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstituicaoController {

    @GET
    public List<Instituicao> listarInstituicoes() {
        return Instituicao.listAll(Sort.by("nome"));
    }

    @GET
    @Path("/{id}")
    public Instituicao buscarInstituicaoPorId(@PathParam Long id) {
        return Instituicao.findById(id);
    }

    @POST
    @Transactional
    public Instituicao adicionarInstituicao(@Valid Instituicao instituicao) {
        instituicao.persist();
        return instituicao;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Instituicao atualizarInstituicao(@PathParam Long id, @Valid Instituicao instituicao) {
        Instituicao entity = Instituicao.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Instituição com ID " + id + " não encontrada.", 404);
        }

        entity.setNome(instituicao.getNome());

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletarInstituicao(@PathParam Long id) {
        Instituicao entity = Instituicao.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Instituição com ID " + id + " não encontrada.", 404);
        }
        entity.delete();
    }
}
