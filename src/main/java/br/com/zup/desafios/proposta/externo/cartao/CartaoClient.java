package br.com.zup.desafios.proposta.externo.cartao;

import br.com.zup.desafios.proposta.cartao.Cartao;
import br.com.zup.desafios.proposta.cartao.Bloqueio;
import br.com.zup.desafios.proposta.proposta.Proposta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.stereotype.Component;

import static br.com.zup.desafios.proposta.externo.cartao.BloqueioStatus.BLOQUEADO;

@Component
public class CartaoClient {

    private final CartaoFeign cartaoFeign;
    private final ObjectMapper objectMapper;

    public CartaoClient(CartaoFeign cartaoFeign, ObjectMapper objectMapper) {
        this.cartaoFeign = cartaoFeign;
        this.objectMapper = objectMapper;
    }

    public Cartao criaCartao(Proposta proposta){
        NovoCartaoRequest request = new NovoCartaoRequest(proposta.getId(), proposta.getNome(), proposta.getDocumento());
        CartaoResponse cartaoCriado = cartaoFeign.criaCartao(request);

        return cartaoCriado.convert();
    }

    public Bloqueio bloqueiaCartao(Cartao cartao, String responsavel){
        BloqueioCartaoResponse bloqueioCartaoResponse = null;

        try {
            bloqueioCartaoResponse = cartaoFeign.bloqueaCartao(cartao.getIdExterno(), new BloqueioCartaoRequest(responsavel));
        }catch (FeignException.UnprocessableEntity exception){
            try{
                String body = exception.contentUTF8();
                bloqueioCartaoResponse = objectMapper.readValue(body, BloqueioCartaoResponse.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if(bloqueioCartaoResponse.getResultado().equals(BLOQUEADO)){
            return new Bloqueio(responsavel, cartao);
        }
        return null;
    }

}
