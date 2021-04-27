package br.com.zup.desafios.proposta.cartao;

import br.com.zup.desafios.proposta.cartao.aviso.AvisoViagem;
import br.com.zup.desafios.proposta.cartao.aviso.AvisoViagemPersist;
import br.com.zup.desafios.proposta.cartao.bloqueio.Bloqueio;
import br.com.zup.desafios.proposta.cartao.carteira.Carteira;
import br.com.zup.desafios.proposta.cartao.carteira.CarteiraCartaoRequest;
import br.com.zup.desafios.proposta.externo.cartao.BloqueioRequestFeign;
import br.com.zup.desafios.proposta.externo.cartao.CartaoFeign;
import br.com.zup.desafios.proposta.externo.cartao.CartaoResponseFeign;
import br.com.zup.desafios.proposta.externo.cartao.CarteiraResponseFeign;
import br.com.zup.desafios.proposta.externo.cartao.CarteiraRequestFeign;
import br.com.zup.desafios.proposta.externo.cartao.NovoCartaoRequestFeign;
import br.com.zup.desafios.proposta.externo.cartao.NovoCartaoResponseFeign;
import br.com.zup.desafios.proposta.proposta.Proposta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.stereotype.Component;

import static br.com.zup.desafios.proposta.externo.cartao.RespostaFeign.ASSOCIADA;
import static br.com.zup.desafios.proposta.externo.cartao.RespostaFeign.BLOQUEADO;
import static br.com.zup.desafios.proposta.externo.cartao.RespostaFeign.CRIADO;

@Component
public class CartaoClient {

    private final CartaoFeign cartaoFeign;
    private final ObjectMapper objectMapper;

    public CartaoClient(CartaoFeign cartaoFeign, ObjectMapper objectMapper) {
        this.cartaoFeign = cartaoFeign;
        this.objectMapper = objectMapper;
    }

    public Cartao criaCartao(Proposta proposta){
        NovoCartaoRequestFeign request = new NovoCartaoRequestFeign(proposta.getId(), proposta.getNome(), proposta.getDocumento());
        NovoCartaoResponseFeign cartaoCriado = cartaoFeign.criaCartao(request);

        return cartaoCriado.convert();
    }

    public Bloqueio bloqueiaCartao(Cartao cartao, String responsavel){
        CartaoResponseFeign cartaoResponseFeign = null;

        try {
            cartaoResponseFeign = cartaoFeign.bloqueaCartao(cartao.getIdExterno(), new BloqueioRequestFeign(responsavel));
        }catch (FeignException.UnprocessableEntity exception){
            try{
                String body = exception.contentUTF8();
                cartaoResponseFeign = objectMapper.readValue(body, CartaoResponseFeign.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if(cartaoResponseFeign.getResultado().equals(BLOQUEADO)){
            return new Bloqueio(responsavel, cartao);
        }
        return null;
    }


    public AvisoViagem criaAvisoViagemCartao(Cartao cartao, AvisoViagemPersist avisoViagemPersist, String responsavel, String ip){
        CartaoResponseFeign cartaoResponseFeign = null;

        try {
            cartaoResponseFeign = cartaoFeign.criaAvisoViagem(cartao.getIdExterno(), avisoViagemPersist);
        }catch (FeignException.UnprocessableEntity exception){
            try{
                String body = exception.contentUTF8();
                cartaoResponseFeign = objectMapper.readValue(body, CartaoResponseFeign.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if(cartaoResponseFeign.getResultado().equals(CRIADO)){
            return new AvisoViagem(cartao, avisoViagemPersist, responsavel, ip);
        }
        return null;
    }

    public Carteira criaCarteiraCartao(Cartao cartao, CarteiraCartaoRequest request){
        CarteiraResponseFeign response = null;
        try {
            response = cartaoFeign.criaCarteira(cartao.getIdExterno(), new CarteiraRequestFeign(request));
        }catch (FeignException.UnprocessableEntity exception){
            try{
                String body = exception.contentUTF8();
                response = objectMapper.readValue(body, CarteiraResponseFeign.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if(response.getResultado().equals(ASSOCIADA)){
            return new Carteira(cartao, request, response.getId());
        }
        return null;
    }
}
