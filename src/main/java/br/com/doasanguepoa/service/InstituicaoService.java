package br.com.doasanguepoa.service;

import br.com.doasanguepoa.dto.instituicao.InstituicaoDTOComSenha;
import br.com.doasanguepoa.model.Instituicao;
import br.com.doasanguepoa.repository.InstituicaoRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class InstituicaoService {

    public static final Logger LOGGER = Logger.getLogger(InstituicaoService.class.getName());

    @Inject
    InstituicaoRepository instituicaoRepository;
    public List<Instituicao> listarInstituicoes() {
        return instituicaoRepository.listAll();
    }

    public Instituicao buscarInstituicaoPorId(Long id) {
        return instituicaoRepository.findById(id);
    }


    //USAR DTOs AQUI
    public void adicionarInstituicao(InstituicaoDTOComSenha instituicaoDTOComSenha) {
        String hashSenha = BcryptUtil.bcryptHash(instituicaoDTOComSenha.senha());
        Instituicao instituicao = new Instituicao(instituicaoDTOComSenha.nome(), instituicaoDTOComSenha.endereco(), instituicaoDTOComSenha.email(), hashSenha, instituicaoDTOComSenha.cnpj());
        LOGGER.log(Level.INFO, "Gravando a instituicao: {0}", instituicao);
        instituicaoRepository.persist(instituicao);
    }

    public void deletarInstituicao(Long id) {
        instituicaoRepository.deleteById(id);
    }

    public Instituicao buscarInstituicaoPorCnpj(String cnpj) {
        return instituicaoRepository.findByCnpj(cnpj);
    }
}
