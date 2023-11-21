package br.com.doasanguepoa;

import br.com.doasanguepoa.enuns.Role;
import br.com.doasanguepoa.model.CadastroPF;
import br.com.doasanguepoa.model.CadastroPJ;
import br.com.doasanguepoa.repository.CadastroPFRepository;
import br.com.doasanguepoa.repository.CadastroPJRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/carga")
public class Main {

    @Inject
    CadastroPFRepository cadastroPFRepository;

    @Inject
    CadastroPJRepository cadastroPJRepository;
    @GET
    @Transactional
    public void carga() {
        Logger logger = Logger.getLogger(Main.class.getName());

        String senha = BcryptUtil.bcryptHash("leonardo");
        CadastroPF usuario = new CadastroPF("leonardo","trav a","leonardo@leonardo.com",senha,"12345678912", Role.ADMIN);
        logger.log(Level.INFO,"Inserindo usuario: {0}", usuario);
        cadastroPFRepository.persist(usuario);


        String senhaHash = BcryptUtil.bcryptHash("clinicas");
        CadastroPJ instituicao = new CadastroPJ("Clinicas","trav oswaldo aranha","clinicas@clinicas.com",senhaHash,"87020517000120", Role.INSTITUICAO);
        logger.log(Level.INFO,"Inserindo instituicao: {0}", instituicao);
        cadastroPJRepository.persist(instituicao);

        String senhaHash2 = BcryptUtil.bcryptHash("hps");
        CadastroPJ instituicao2 = new CadastroPJ("Hospital Pronto Socorro","Av. Goethe","hps@hps.com",senhaHash2,"93712735000200",Role.INSTITUICAO);
        logger.log(Level.INFO,"Inserindo instituicao: {0}", instituicao2);
        cadastroPJRepository.persist(instituicao2);

    }

}
