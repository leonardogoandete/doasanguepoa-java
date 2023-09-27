package br.com.doasanguepoa.service;
import br.com.doasanguepoa.repository.InstituicaoRepository;
import br.com.doasanguepoa.repository.UsuarioRepository;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Logger;

import static br.com.doasanguepoa.utils.SecurityUtil.verifyBCryptPassword;

@ApplicationScoped
public class AuthService {

    public static final Logger LOGGER = Logger.getLogger(AuthService.class.getName());

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    InstituicaoRepository instituicaoRepository;

    // Essa chave é usada para assinar o token JWT. Substitua por uma chave real em produção.
    private static final String SECRET_KEY = "SuaChaveSecreta";

    public String autenticarPorCPF(String cpf, String senha) throws Exception {
        // Lógica de autenticação com CPF (substitua por sua lógica de autenticação)
        if (validarCredenciaisCPF(cpf, senha)) {
            return gerarTokenJWT(cpf, "CPF");
        }
        return null;
    }

    public String autenticarPorCNPJ(String cnpj, String senha) throws Exception {
        // Lógica de autenticação com CNPJ (substitua por sua lógica de autenticação)
        if (validarCredenciaisCNPJ(cnpj, senha)) {
            return gerarTokenJWT(cnpj, "CNPJ");
        }
        return null;
    }

    // Método para gerar um token JWT com base no documento (CPF ou CNPJ)
    private String gerarTokenJWT(String documento, String tipoDocumento) {

        if (tipoDocumento.equals("CPF")) {
            return Jwt.issuer("http://localhost:8080")
                    .upn(documento)
                    .groups(new HashSet<>(Arrays.asList("USUARIO")))
                    .sign();
        } else if (tipoDocumento.equals("CNPJ")) {
            return Jwt.issuer("http://localhost:8080")
                    .upn(documento)
                    .groups(new HashSet<>(Arrays.asList("INSTITUICAO")))
                    .sign();
        }
        return null;
    }

    // Métodos de validação de credenciais CPF e CNPJ (substitua por sua lógica)
    private boolean validarCredenciaisCPF(String cpf, String senha) throws Exception {
        // Lógica de validação de credenciais CPF

        String pass = usuarioRepository.findByCpf(cpf).getSenha();
        if (verifyBCryptPassword(pass, senha)){
            return true; // Simulação de autenticação bem-sucedida
        }

        LOGGER.info("Senha do usuario: " + pass);
        return false;
    }

    private boolean validarCredenciaisCNPJ(String cnpj, String senha) throws Exception {
        // Lógica de validação de credenciais CNPJ
        String pass = instituicaoRepository.findByCnpj(cnpj).getSenha();
        if (verifyBCryptPassword(pass, senha)){
            return true; // Simulação de autenticação bem-sucedida
        }
        LOGGER.info("Senha da instituicao: " + pass);
        return false; // Simulação de autenticação bem-sucedida
    }
}

