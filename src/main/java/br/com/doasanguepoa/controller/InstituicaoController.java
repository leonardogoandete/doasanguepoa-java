package br.com.doasanguepoa.controller;

import br.com.doasanguepoa.dto.instituicao.InstituicaoDTO;
import br.com.doasanguepoa.dto.instituicao.InstituicaoDTOComSenha;
import br.com.doasanguepoa.model.Instituicao;
import br.com.doasanguepoa.service.InstituicaoService;
import jakarta.annotation.security.RolesAllowed;
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
    @RolesAllowed({ "USUARIO","INSTITUICAO" })
    public List<InstituicaoDTO> listarInstituicoes() {
        List<InstituicaoDTO> instituicoesDTO = new ArrayList<>();

        try{
            List<Instituicao> instituicoes = instituicaoService.listarInstituicoes();
            for(Instituicao instituicao: instituicoes){
                instituicoesDTO.add(new InstituicaoDTO(instituicao.getNome(),instituicao.getEndereco(),instituicao.getEmail(),instituicao.getCnpj(), instituicao.getAvatar()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return instituicoesDTO;
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "USUARIO","INSTITUICAO" })
    public Instituicao buscarInstituicaoPorId(@PathParam Long id) {
        return instituicaoService.buscarInstituicaoPorId(id);
    }

    @POST
    @Transactional
    //@RolesAllowed({ "ADMIN" })
    public void adicionarInstituicao(@Valid InstituicaoDTOComSenha instituicaoDTOComSenha) {
        instituicaoService.adicionarInstituicao(instituicaoDTOComSenha);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({ "ADMIN","INSTITUICAO" })
    public InstituicaoDTOComSenha atualizarInstituicao(@PathParam Long id, @Valid InstituicaoDTOComSenha instituicaoDTOComSenha) {
        Instituicao entity = instituicaoService.buscarInstituicaoPorId(id);
        if (entity == null) {
            throw new WebApplicationException("Instituição com ID " + id + " não encontrada.", 404);
        }

        entity.setNome(instituicaoDTOComSenha.nome());
        entity.setEndereco(instituicaoDTOComSenha.endereco());
        entity.setEmail(instituicaoDTOComSenha.email());
        entity.setSenha(instituicaoDTOComSenha.senha());
        entity.setCnpj(instituicaoDTOComSenha.cnpj());

        return instituicaoDTOComSenha;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed({ "ADMIN" })
    public void deletarInstituicao(@PathParam Long id) {
        Instituicao entity = instituicaoService.buscarInstituicaoPorId(id);
        if (entity == null) {
            throw new WebApplicationException("Instituição com ID " + id + " não encontrada.", 404);
        }
        instituicaoService.deletarInstituicao(id);
    }
}
