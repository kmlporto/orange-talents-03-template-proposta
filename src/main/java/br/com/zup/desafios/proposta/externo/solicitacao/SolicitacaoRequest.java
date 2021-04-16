package br.com.zup.desafios.proposta.externo.solicitacao;

import br.com.zup.desafios.proposta.utils.annotation.DocumentValid;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SolicitacaoRequest {

    @NotNull
    private Long idProposta;
    @NotEmpty
    private String nome;
    @NotEmpty
    @DocumentValid
    private String documento;

    public SolicitacaoRequest(@NotNull Long idProposta, @NotEmpty String nome, @NotEmpty String documento) {
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
