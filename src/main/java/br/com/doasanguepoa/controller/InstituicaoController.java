package br.com.doasanguepoa.controller;

import br.com.doasanguepoa.dto.instituicao.InstituicaoDTO;
import br.com.doasanguepoa.model.Instituicao;
import br.com.doasanguepoa.service.InstituicaoService;
import jakarta.inject.Inject;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Path("/instituicoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstituicaoController {

    @Inject
    InstituicaoService instituicaoService;

    @GET
    public List<Instituicao> listarInstituicoes() {
        List<Instituicao> instituicoes = new ArrayList<>();

        try{
            instituicoes = instituicaoService.listarInstituicoes();
        }catch (Exception e){
            e.printStackTrace();
        }

        return instituicoes;
    }

    @GET
    @Path("/{id}")
    public Instituicao buscarInstituicaoPorId(@PathParam Long id) {
        return instituicaoService.buscarInstituicaoPorId(id);
    }

    @POST
    @Transactional
    public void adicionarInstituicao(@Valid InstituicaoDTO instituicaoDTO) {
        instituicaoService.adicionarInstituicao(instituicaoDTO);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public InstituicaoDTO atualizarInstituicao(@PathParam Long id, @Valid InstituicaoDTO instituicaoDTO) {
        Instituicao entity = instituicaoService.buscarInstituicaoPorId(id);
        if (entity == null) {
            throw new WebApplicationException("Instituição com ID " + id + " não encontrada.", 404);
        }

        entity.setNome(instituicaoDTO.nome());
        entity.setEndereco(instituicaoDTO.endereco());
        entity.setEmail(instituicaoDTO.email());
        entity.setSenha(instituicaoDTO.senha());
        entity.setCnpj(instituicaoDTO.cnpj());

        return instituicaoDTO;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletarInstituicao(@PathParam Long id) {
        Instituicao entity = instituicaoService.buscarInstituicaoPorId(id);
        if (entity == null) {
            throw new WebApplicationException("Instituição com ID " + id + " não encontrada.", 404);
        }
        instituicaoService.deletarInstituicao(id);
    }
}
