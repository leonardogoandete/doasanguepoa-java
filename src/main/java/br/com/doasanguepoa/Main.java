package br.com.doasanguepoa;

import br.com.doasanguepoa.enuns.Role;
import br.com.doasanguepoa.model.Pessoa;
import br.com.doasanguepoa.repository.PessoaRepository;
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
    PessoaRepository pessoaRepository;
    @POST
    @Transactional
    public void carga() {
        Logger logger = Logger.getLogger(Main.class.getName());

        String senha = BcryptUtil.bcryptHash("leonardo");
        Pessoa usuario = new Pessoa("leonardo","trav a","leonardo@leonardo.com",senha, Role.ADMIN,"12345678912");
        logger.log(Level.INFO,"Inserindo usuario: {0}", usuario);
        pessoaRepository.persist(usuario);


        String senhaHash = BcryptUtil.bcryptHash("clinicas");
        Pessoa instituicao = new Pessoa("Clinicas","trav oswaldo aranha","clinicas@clinicas.com",senhaHash, Role.INSTITUICAO, "87020517000120");
        logger.log(Level.INFO,"Inserindo instituicao: {0}", instituicao);
        pessoaRepository.persist(instituicao);

        String senhaHash2 = BcryptUtil.bcryptHash("hps");
        Pessoa instituicao2 = new Pessoa("Hospital Pronto Socorro","Av. Goethe","hps@hps.com",senhaHash2, Role.INSTITUICAO, "93712735000200");
        logger.log(Level.INFO,"Inserindo instituicao: {0}", instituicao2);
        pessoaRepository.persist(instituicao2);

    }

}
