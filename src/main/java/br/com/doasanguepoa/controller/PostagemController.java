package br.com.doasanguepoa.controller;

import br.com.doasanguepoa.dto.postagem.PostagemDTO;
import br.com.doasanguepoa.model.Instituicao;
import br.com.doasanguepoa.model.Postagem;
import br.com.doasanguepoa.service.AuthService;
import br.com.doasanguepoa.service.InstituicaoService;
import br.com.doasanguepoa.service.PostagemService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/postagens")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(scheme = "Bearer",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT")
public class PostagemController {

    Logger LOGGER = Logger.getLogger(PostagemController.class.getName());
    @Inject
    @Claim(standard = Claims.upn)
    String upn;
    @Inject
    PostagemService postagemService;

    @Inject
    InstituicaoService instituicaoService;


    @GET
    @RolesAllowed({ "USUARIO","INSTITUICAO" })
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
    @RolesAllowed({ "USUARIO","INSTITUICAO" })
    public Postagem buscarPostagemPorId(@PathParam Long id) {
        return postagemService.buscarPostagemPorId(id);
    }

    @POST
    @Transactional
    @RolesAllowed({ "INSTITUICAO" })
    public PostagemDTO adicionarPostagem(@Valid PostagemDTO postagemDTO) {
        Instituicao instituicao = instituicaoService.buscarInstituicaoPorCnpj(upn);
        LOGGER.log(Level.INFO,"Exibindo info instituicao {0}", instituicao);
        Postagem postagem = new Postagem(postagemDTO.titulo(), postagemDTO.mensagem(), instituicao);
        postagemService.adicionarPostagem(postagem);
        return postagemDTO;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({ "INSTITUICAO" })
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
    @RolesAllowed({ "ADMIN","INSTITUICAO" })
    public void deletarPostagem(@PathParam Long id) {
        Postagem entity = postagemService.buscarPostagemPorId(id);
        if (entity == null) {
            throw new WebApplicationException("Postagem com ID " + id + " não encontrada.", 404);
        }
        //entity.delete();
    }
}
