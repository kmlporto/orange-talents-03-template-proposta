package br.com.zup.desafios.proposta.proposta;

import br.com.zup.desafios.proposta.cartao.Cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PropostaCartaoResponse {

    private Long id;
    private String idExterno;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;

    public PropostaCartaoResponse(Cartao cartao) {
        this.id = cartao.getId();
        this.idExterno = cartao.getIdExterno();
        this.emitidoEm = cartao.getEmitidoEm();
        this.titular = cartao.getTitular();
        this.limite = cartao.getLimite();
    }

    public static PropostaCartaoResponse convert(Cartao cartao){
        return new PropostaCartaoResponse(cartao);
    }
}
