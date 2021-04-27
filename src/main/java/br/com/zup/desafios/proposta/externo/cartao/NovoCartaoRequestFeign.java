package br.com.zup.desafios.proposta.externo.cartao;

import br.com.zup.desafios.proposta.utils.annotation.DocumentValid;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NovoCartaoRequestFeign {
    @NotNull
    private Long idProposta;
    @NotEmpty
    private String nome;
    @NotEmpty
    @DocumentValid
    private String documento;

    public NovoCartaoRequestFeign(@NotNull Long idProposta, @NotEmpty String nome, @NotEmpty String documento) {
        this.idProposta = idProposta;
        this.nome = nome;
        this.documento = documento;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }
}
