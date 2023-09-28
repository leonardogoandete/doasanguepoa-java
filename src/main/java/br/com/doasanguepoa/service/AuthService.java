package br.com.doasanguepoa.service;
import br.com.doasanguepoa.utils.SecurityUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class AuthService {
    public static final Logger LOGGER = Logger.getLogger(AuthService.class.getName());

    @Inject
    UsuarioService usuarioService;

    @Inject
    InstituicaoService instituicaoService;

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
                    .expiresAt(System.currentTimeMillis()+3600)
                    .sign();
        } else if (tipoDocumento.equals("CNPJ")) {
            return Jwt.issuer("http://localhost:8080")
                    .upn(documento)
                    .groups(new HashSet<>(Arrays.asList("INSTITUICAO")))
                    .expiresAt(System.currentTimeMillis()+3600)
                    .sign();
        }
        return null;
    }

    // Métodos de validação de credenciais CPF e CNPJ (substitua por sua lógica)
    private boolean validarCredenciaisCPF(String cpf, String senha) throws Exception {
        // Lógica de validação de credenciais CPF
        // trocar para buscar por CPF
        String pass = usuarioService.buscarUsuarioPorCpf(cpf).getSenha();
        if (SecurityUtil.verifyBCryptPassword(pass, senha)){
            return true; // Simulação de autenticação bem-sucedida
        }

        LOGGER.log(Level.INFO, "Senha do usuario: {0}", pass);
        return false;
    }

    private boolean validarCredenciaisCNPJ(String cnpj, String senha) throws Exception {
        // Lógica de validação de credenciais CNPJ
        String pass = instituicaoService.buscarInstituicaoPorCnpj(cnpj).getSenha();
        if (SecurityUtil.verifyBCryptPassword(pass, senha)){
            return true; // Simulação de autenticação bem-sucedida
        }
        LOGGER.log(Level.INFO, "Senha da instituicao: {0}", pass);
        return false; // Simulação de autenticação bem-sucedida
    }
}

