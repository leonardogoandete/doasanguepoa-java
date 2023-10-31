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
        Usuario usuario = new Usuario("leonardo","trav a","leonardo@leonardo.com",senha,"12345678912", Role.ADMIN, "https://api.dicebear.com/7.x/adventurer/svg?seed=b");
        logger.log(Level.INFO,"Inserindo usuario: {0}", usuario);
        usuarioRepository.persist(usuario);


        String senhaHash = BcryptUtil.bcryptHash("clinicas");
        Instituicao instituicao = new Instituicao("Clinicas","trav oswaldo aranha","clinicas@clinicas.com",senhaHash,"87020517000120", "https://api.dicebear.com/7.x/adventurer/svg?seed=d");
        logger.log(Level.INFO,"Inserindo instituicao: {0}", instituicao);
        instituicaoRepository.persist(instituicao);

        String senhaHash2 = BcryptUtil.bcryptHash("hps");
        Instituicao instituicao2 = new Instituicao("Hospital Pronto Socorro","Av. Goethe","hps@hps.com",senhaHash2,"93712735000200","https://api.dicebear.com/7.x/adventurer/svg?seed=auhs");
        logger.log(Level.INFO,"Inserindo instituicao: {0}", instituicao2);
        instituicaoRepository.persist(instituicao2);

    }

}
