package br.com.doasanguepoa.controller;

import br.com.doasanguepoa.dto.postagem.PostagemCadastroDTO;
import br.com.doasanguepoa.dto.postagem.PostagemDTO;
import br.com.doasanguepoa.model.Instituicao;
import br.com.doasanguepoa.model.Postagem;
import br.com.doasanguepoa.service.InstituicaoService;
import br.com.doasanguepoa.service.PostagemService;
import br.com.doasanguepoa.interfaces.IPostagemService;
import io.quarkus.oidc.token.propagation.AccessToken;
import jakarta.annotation.security.PermitAll;
import org.eclipse.microprofile.jwt.JsonWebToken;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
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

    Logger logger = Logger.getLogger(PostagemController.class.getName());
    @Inject
    PostagemService postagemService;

    @Inject
    InstituicaoService instituicaoService;

    @Inject
    @RestClient
    IPostagemService postagemServiceClient;

    @Inject
    JsonWebToken jwt;
    @GET
    @RolesAllowed({ "USUARIO","INSTITUICAO" })
    public List<PostagemDTO> listarPostagens(@HeaderParam("Host") String host) {
        List<PostagemDTO> postagensDTO = new ArrayList<>();

        try{
            List<Postagem> postagens = postagemService.listarPostagens();
            for(Postagem postagem: postagens){
                postagensDTO.add(new PostagemDTO(postagem.getTitulo(),postagem.getMensagem(),postagem.getInstituicao().getNome()));
            }
            logger.log(Level.INFO,"Exibindo postagens para o front {0}", host);
        }catch (Exception e){
            e.printStackTrace();
        }
        return postagensDTO;
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
    public PostagemCadastroDTO adicionarPostagem(@Valid PostagemCadastroDTO postagemCadastroDTO) {
        String cnpjInstituicao = jwt.getClaim("upn");
        Instituicao instituicao = instituicaoService.buscarInstituicaoPorCnpj(cnpjInstituicao);
        logger.log(Level.INFO,"Adicionando postagem da instituicao: {0}", instituicao.getNome());
        Postagem postagem = new Postagem(postagemCadastroDTO.titulo(), postagemCadastroDTO.mensagem(), instituicao);
        postagemService.adicionarPostagem(postagem);
        return postagemCadastroDTO;
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
        postagemService.deletarPostagem(id);
    }

    @GET
    @Path("/teste")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "USUARIO","INSTITUICAO" })
    public List<PostagemCadastroDTO> teste() {
        //logger.log(Level.INFO, "{0}",postagemServiceClient.listarPostagens());
        //postagemServiceClient.listarPostagens();
        return postagemServiceClient.listarPostagens();

    }
}
