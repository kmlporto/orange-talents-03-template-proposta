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
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.zup.desafios.proposta.externo.cartao.RespostaFeign.ASSOCIADA;
import static br.com.zup.desafios.proposta.externo.cartao.RespostaFeign.BLOQUEADO;
import static br.com.zup.desafios.proposta.externo.cartao.RespostaFeign.CRIADO;

@Component
public class CartaoClient {

    private final CartaoFeign cartaoFeign;

    public CartaoClient(CartaoFeign cartaoFeign) {
        this.cartaoFeign = cartaoFeign;
    }

    public Cartao criaCartao(Proposta proposta){
        NovoCartaoRequestFeign request = new NovoCartaoRequestFeign(proposta.getId(), proposta.getNome(), proposta.getDocumento());
        NovoCartaoResponseFeign cartaoCriado = cartaoFeign.criaCartao(request);

        return cartaoCriado.convert();
    }

    public Optional<Bloqueio> bloqueiaCartao(Cartao cartao, String responsavel){
        CartaoResponseFeign cartaoResponseFeign = cartaoFeign.bloqueaCartao(cartao.getIdExterno(), new BloqueioRequestFeign(responsavel));
        if(cartaoResponseFeign.getResultado().equals(BLOQUEADO)){
            return Optional.of(new Bloqueio(responsavel, cartao));
        }
        return Optional.empty();
    }


    public Optional<AvisoViagem> criaAvisoViagemCartao(Cartao cartao, AvisoViagemPersist avisoViagemPersist, String responsavel, String ip){
        CartaoResponseFeign cartaoResponseFeign = cartaoFeign.criaAvisoViagem(cartao.getIdExterno(), avisoViagemPersist);
        if(cartaoResponseFeign.getResultado().equals(CRIADO)){
            return Optional.of(new AvisoViagem(cartao, avisoViagemPersist, responsavel, ip));
        }
        return Optional.empty();
    }

    public Optional<Carteira> criaCarteiraCartao(Cartao cartao, CarteiraCartaoRequest request){
        CarteiraResponseFeign response = cartaoFeign.criaCarteira(cartao.getIdExterno(), new CarteiraRequestFeign(request));
        if(response.getResultado().equals(ASSOCIADA)){
            return Optional.of(new Carteira(cartao, request, response.getId()));
        }
        return Optional.empty();
    }
}
