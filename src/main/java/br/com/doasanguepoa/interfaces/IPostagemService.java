package br.com.doasanguepoa.interfaces;

import br.com.doasanguepoa.dto.postagem.PostagemCadastroDTO;
import io.quarkus.oidc.token.propagation.AccessToken;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@AccessToken
@RegisterRestClient
@Path("/postagens")
public interface IPostagemService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<PostagemCadastroDTO> listarPostagens();
}
