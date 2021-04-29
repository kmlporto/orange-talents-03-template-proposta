package br.com.zup.desafios.proposta.cartao.aviso;

import br.com.zup.desafios.proposta.cartao.Cartao;
import br.com.zup.desafios.proposta.cartao.CartaoRepository;
import br.com.zup.desafios.proposta.config.handler.ApiErrorException;
import br.com.zup.desafios.proposta.cartao.CartaoClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.zup.desafios.proposta.utils.Path.AVISO;
import static br.com.zup.desafios.proposta.utils.Path.CARTAO;

@RestController
@RequestMapping(value = CARTAO + AVISO)
public class AvisoController {

    private final CartaoRepository cartaoRepository;
    private final CartaoClient cartaoClient;

    public AvisoController(CartaoRepository cartaoRepository, CartaoClient cartaoClient) {
        this.cartaoRepository = cartaoRepository;
        this.cartaoClient = cartaoClient;
    }

    @PostMapping
    public ResponseEntity<?> cadastraAviso(@PathVariable String id, @Valid @RequestBody AvisoViagemPersist avisoViagemPersist, @RequestHeader("User-Agent") String userAgent, @RequestHeader("ip-client") String ipClient){
        if(!cartaoRepository.existsByIdExterno(id)){
            return ResponseEntity.notFound().build();
        }
        Cartao cartao = cartaoRepository.findByIdExterno(id);

        AvisoViagem aviso = cartaoClient.criaAvisoViagemCartao(cartao, avisoViagemPersist, userAgent, ipClient)
                .orElseThrow(() -> new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível adicionar aviso de viagem"));

        cartao.addAviso(aviso);

        cartaoRepository.save(cartao);

        return ResponseEntity.ok().build();

    }
}
