package br.com.doasanguepoa;

import br.com.doasanguepoa.enuns.Role;
import br.com.doasanguepoa.model.Instituicao;
import br.com.doasanguepoa.model.Usuario;
import br.com.doasanguepoa.repository.InstituicaoRepository;
import br.com.doasanguepoa.repository.UsuarioRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/carga")
public class Main {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    InstituicaoRepository instituicaoRepository;
    @POST
    @Transactional
    public void carga() {
        Logger logger = Logger.getLogger(Main.class.getName());

        String senha = BcryptUtil.bcryptHash("leonardo");
        Usuario usuario = new Usuario("leonardo","trav a","leonardo@leonardo.com",senha,"12345678912", Role.ADMIN);
        logger.log(Level.INFO,"Inserindo usuario: {0}", usuario);
        usuarioRepository.persist(usuario);


        String senhaHash = BcryptUtil.bcryptHash("clinicas");
        Instituicao instituicao = new Instituicao("Clinicas","trav oswaldo aranha","clinicas@clinicas.com",senhaHash,"87020517000120");
        logger.log(Level.INFO,"Inserindo instituicao: {0}", instituicao);
        instituicaoRepository.persist(instituicao);

    }

}
