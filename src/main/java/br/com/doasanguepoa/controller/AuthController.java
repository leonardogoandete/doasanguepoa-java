package br.com.doasanguepoa.controller;

import java.util.HashMap;
import java.util.Map;

import br.com.doasanguepoa.dto.autenticacao.LoginDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.FormParam; // Importe a anotação FormParam
import br.com.doasanguepoa.service.AuthService;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON) // Define o tipo de mídia para formulário URL codificado
    public Response login(LoginDTO loginDTO) throws Exception {
        if (loginDTO.documento() == null || loginDTO.senha() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Documento e senha são obrigatórios.").build();
        }

        String token = null;

        if (isCPF(loginDTO.documento())) {
            // Autenticar com CPF
            token = authService.autenticarPorCPF(loginDTO.documento(), loginDTO.senha());
        } else if (isCNPJ(loginDTO.documento())) {
            // Autenticar com CNPJ
            token = authService.autenticarPorCNPJ(loginDTO.documento(), loginDTO.senha());
        }

        if (token != null) {
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return Response.ok(response).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Autenticação falhou").build();
        }
    }

    private boolean isCPF(String documento) {
        return documento.length() == 11;
    }

    private boolean isCNPJ(String documento) {
        return documento.length() == 14;
    }
}
