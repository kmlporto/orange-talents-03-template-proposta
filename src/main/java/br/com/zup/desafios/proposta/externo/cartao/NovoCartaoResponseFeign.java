package br.com.zup.desafios.proposta.externo.cartao;

import br.com.zup.desafios.proposta.cartao.Cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NovoCartaoResponseFeign {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;
    private String idProposta;

    @Deprecated
    public NovoCartaoResponseFeign() {
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public Cartao convert(){
        return new Cartao(id, emitidoEm, titular, limite);
    }
}