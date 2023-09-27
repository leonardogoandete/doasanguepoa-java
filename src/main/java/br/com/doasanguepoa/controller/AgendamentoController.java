package br.com.doasanguepoa.controller;

import br.com.doasanguepoa.dto.agendamento.AgendamentoDTO;
import br.com.doasanguepoa.model.Agendamento;
import br.com.doasanguepoa.model.Instituicao;
import br.com.doasanguepoa.model.Usuario;
import br.com.doasanguepoa.service.AgendamentoService;
import br.com.doasanguepoa.service.InstituicaoService;
import br.com.doasanguepoa.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/agendamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AgendamentoController {
    Logger logger = Logger.getLogger(AgendamentoController.class.getName());

    @Inject
    @Claim(standard = Claims.upn)
    String upn;
    @Inject
    AgendamentoService agendamentoService;

    @Inject
    InstituicaoService instituicaoService;

    @Inject
    UsuarioService usuarioService;

    @GET
    @RolesAllowed({ "USUARIO","INSTITUICAO"})
    public List<Agendamento> listarAgendamentos(){
        List<Agendamento> agendamentos = new ArrayList<>();

        try {
            agendamentos = agendamentoService.listarAgendamentos();
        }catch (Exception e){
            e.printStackTrace();
        }
        return agendamentos;
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "USUARIO","INSTITUICAO" })
    public Agendamento buscarAgendamentoPorId(@PathParam Long id) {
        return agendamentoService.buscarAgendamentoPorId(id);
    }

    @POST
    @Transactional
    @RolesAllowed({ "USUARIO" })
    public void adicionarAgendamento(@Valid AgendamentoDTO agendamentoDTO) {
        Usuario usuario = usuarioService.buscarUsuarioPorCpf(upn);
        Instituicao instituicao = instituicaoService.buscarInstituicaoPorId(agendamentoDTO.idInstituicao());
        Agendamento agendamento = new Agendamento(agendamentoDTO.date(),instituicao,agendamentoDTO.hora(),usuario);
        logger.log(Level.INFO,"Exibindo info agendamento {0}", agendamento);
        agendamentoService.adicionarAgendamento(agendamento);
    }

}
