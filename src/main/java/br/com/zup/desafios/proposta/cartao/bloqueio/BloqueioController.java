package br.com.zup.desafios.proposta.cartao.bloqueio;

import br.com.zup.desafios.proposta.cartao.Cartao;
import br.com.zup.desafios.proposta.cartao.CartaoRepository;
import br.com.zup.desafios.proposta.config.handler.ApiErrorException;
import br.com.zup.desafios.proposta.cartao.CartaoClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.zup.desafios.proposta.utils.Path.BLOQUEIO;
import static br.com.zup.desafios.proposta.utils.Path.CARTAO;

@RestController
@RequestMapping(value = CARTAO + BLOQUEIO)
public class BloqueioController {

    private final CartaoRepository cartaoRepository;
    private final CartaoClient cartaoClient;

    public BloqueioController(CartaoRepository cartaoRepository, CartaoClient cartaoClient) {
        this.cartaoRepository = cartaoRepository;
        this.cartaoClient = cartaoClient;
    }

    @PostMapping
    public ResponseEntity<?> bloqueia(@PathVariable String id, @RequestHeader("User-Agent") String userAgent){
        if(!cartaoRepository.existsByIdExterno(id)){
            return ResponseEntity.notFound().build();
        }
        Cartao cartao = cartaoRepository.findByIdExterno(id);

        if(cartao.bloqueado()){
            return ResponseEntity.unprocessableEntity().build();
        }
        Bloqueio bloqueio = cartaoClient.bloqueiaCartao(cartao, userAgent)
                .orElseThrow(() -> new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão não foi bloqueado"));

        cartao.addBloqueio(bloqueio);

        cartaoRepository.save(cartao);

        return ResponseEntity.ok().build();
    }

}
