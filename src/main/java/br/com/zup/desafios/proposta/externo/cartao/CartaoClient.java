package br.com.zup.desafios.proposta.externo.cartao;

import br.com.zup.desafios.proposta.cartao.AvisoViagem;
import br.com.zup.desafios.proposta.cartao.AvisoViagemPersist;
import br.com.zup.desafios.proposta.cartao.Cartao;
import br.com.zup.desafios.proposta.cartao.Bloqueio;
import br.com.zup.desafios.proposta.proposta.Proposta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.stereotype.Component;

import static br.com.zup.desafios.proposta.externo.cartao.RespostaStatus.BLOQUEADO;
import static br.com.zup.desafios.proposta.externo.cartao.RespostaStatus.CRIADO;

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
        NovoCartaoResponse cartaoCriado = cartaoFeign.criaCartao(request);

        return cartaoCriado.convert();
    }

    public Bloqueio bloqueiaCartao(Cartao cartao, String responsavel){
        CartaoResponse cartaoResponse = null;

        try {
            cartaoResponse = cartaoFeign.bloqueaCartao(cartao.getIdExterno(), new BloqueioCartaoRequest(responsavel));
        }catch (FeignException.UnprocessableEntity exception){
            try{
                String body = exception.contentUTF8();
                cartaoResponse = objectMapper.readValue(body, CartaoResponse.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if(cartaoResponse.getResultado().equals(BLOQUEADO)){
            return new Bloqueio(responsavel, cartao);
        }
        return null;
    }


    public AvisoViagem criaAvisoViagemCartao(Cartao cartao, AvisoViagemPersist avisoViagemPersist, String responsavel, String ip){
        CartaoResponse cartaoResponse = null;

        try {
            cartaoResponse = cartaoFeign.criaAvisoViagem(cartao.getIdExterno(), avisoViagemPersist);
        }catch (FeignException.UnprocessableEntity exception){
            try{
                String body = exception.contentUTF8();
                cartaoResponse = objectMapper.readValue(body, CartaoResponse.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if(cartaoResponse.getResultado().equals(CRIADO)){
            return new AvisoViagem(cartao, avisoViagemPersist, responsavel, ip);
        }
        return null;
    }
}
