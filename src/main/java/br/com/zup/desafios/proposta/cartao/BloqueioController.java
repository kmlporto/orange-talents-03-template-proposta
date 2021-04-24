package br.com.zup.desafios.proposta.cartao;

import br.com.zup.desafios.proposta.config.handler.ApiErrorException;
import br.com.zup.desafios.proposta.externo.cartao.CartaoClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static br.com.zup.desafios.proposta.utils.Path.CARTAO_BLOQUEIO;

@RestController
@RequestMapping(value = CARTAO_BLOQUEIO)
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
        Bloqueio bloqueio = cartaoClient.bloqueiaCartao(cartao, userAgent);

        if(Objects.isNull(bloqueio))
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão não foi bloqueado");

        cartao.addBloqueio(bloqueio);

        cartaoRepository.save(cartao);

        return ResponseEntity.ok().build();
    }
}
