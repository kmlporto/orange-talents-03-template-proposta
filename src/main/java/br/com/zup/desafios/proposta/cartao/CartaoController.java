package br.com.zup.desafios.proposta.cartao;

import br.com.zup.desafios.proposta.config.handler.ApiErrorException;
import br.com.zup.desafios.proposta.externo.cartao.CartaoClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;

import static br.com.zup.desafios.proposta.utils.Path.AVISO;
import static br.com.zup.desafios.proposta.utils.Path.BIOMETRIA;
import static br.com.zup.desafios.proposta.utils.Path.BLOQUEIO;
import static br.com.zup.desafios.proposta.utils.Path.CARTAO;

@RestController
@RequestMapping(value = CARTAO)
public class CartaoController {

    private final CartaoRepository cartaoRepository;
    private final CartaoClient cartaoClient;
    private final BiometriaRepository biometriaRepository;

    public CartaoController(CartaoRepository cartaoRepository, CartaoClient cartaoClient, BiometriaRepository biometriaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.cartaoClient = cartaoClient;
        this.biometriaRepository = biometriaRepository;
    }

    @PostMapping(value = BLOQUEIO)
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

    @PostMapping(value = AVISO)
    public ResponseEntity<?> cadastraAviso(@PathVariable String id, @Valid @RequestBody AvisoViagemPersist avisoViagemPersist, @RequestHeader("User-Agent") String userAgent, @RequestHeader("ip-client") String ipClient){
        if(!cartaoRepository.existsByIdExterno(id)){
            return ResponseEntity.notFound().build();
        }
        Cartao cartao = cartaoRepository.findByIdExterno(id);

        AvisoViagem aviso = cartaoClient.criaAvisoViagemCartao(cartao, avisoViagemPersist, userAgent, ipClient);
        if(Objects.isNull(aviso))
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Não foi possível adicionar aviso de viagem");

        cartao.addAviso(aviso);

        cartaoRepository.save(cartao);

        return ResponseEntity.ok().build();

    }

    @PostMapping(value = BIOMETRIA)
    public ResponseEntity<?> cadastraBiometria(@PathVariable String id, @Valid @RequestBody BiometriaPersist biometriaPersist){
        if(!cartaoRepository.existsByIdExterno(id)){
            return ResponseEntity.notFound().build();
        }
        Cartao cartao = cartaoRepository.findByIdExterno(id);

        Biometria biometria = biometriaRepository.save(biometriaPersist.convert(cartao));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
