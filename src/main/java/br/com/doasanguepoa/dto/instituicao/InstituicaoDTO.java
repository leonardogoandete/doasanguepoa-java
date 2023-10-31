package br.com.doasanguepoa.dto.instituicao;

public record InstituicaoDTO (
        String nome,
        String endereco,
        String email,
        String cnpj,

        String avatar
){}
