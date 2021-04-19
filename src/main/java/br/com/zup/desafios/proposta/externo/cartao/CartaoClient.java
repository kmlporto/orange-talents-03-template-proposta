package br.com.zup.desafios.proposta.externo.cartao;

import br.com.zup.desafios.proposta.proposta.Proposta;
import org.springframework.stereotype.Component;

@Component
public class CartaoClient {

    private final CartaoFeign cartaoFeign;

    public CartaoClient(CartaoFeign cartaoFeign) {
        this.cartaoFeign = cartaoFeign;
    }

    public CartaoResponse criaCartao(Proposta proposta){
        NovoCartaoRequest request = new NovoCartaoRequest(proposta.getId(), proposta.getNome(), proposta.getDocumento());
        return cartaoFeign.criaCartao(request);
    }

}
