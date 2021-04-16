package br.com.zup.desafios.proposta.externo.solicitacao;

import br.com.zup.desafios.proposta.proposta.PropostaAnalise;

public class SolicitacaoResponse {
    private Long idProposta;
    private String nome;
    private String documento;
    private PropostaAnalise resultadoSolicitacao;

    public SolicitacaoResponse() {
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

    public PropostaAnalise getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
