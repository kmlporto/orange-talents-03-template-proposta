package br.com.zup.desafios.proposta.cartao.carteira;

import br.com.zup.desafios.proposta.cartao.Cartao;
import br.com.zup.desafios.proposta.cartao.CartaoRepository;
import br.com.zup.desafios.proposta.config.handler.ApiErrorException;
import br.com.zup.desafios.proposta.cartao.CartaoClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static br.com.zup.desafios.proposta.utils.Path.CARTAO;
import static br.com.zup.desafios.proposta.utils.Path.CARTEIRA;

@RestController
@RequestMapping(value = CARTAO + CARTEIRA)
public class CarteiraController {

    private final CartaoRepository cartaoRepository;
    private final CarteiraRepository carteiraRepository;
    private final CartaoClient cartaoClient;

    public CarteiraController(CartaoRepository cartaoRepository, CarteiraRepository carteiraRepository, CartaoClient cartaoClient) {
        this.cartaoRepository = cartaoRepository;
        this.carteiraRepository = carteiraRepository;
        this.cartaoClient = cartaoClient;
    }

    @PostMapping
    public ResponseEntity<?> cadastraCarteira(@PathVariable String id, @Valid @RequestBody CarteiraCartaoRequest carteiraCartaoRequest){
        if(!cartaoRepository.existsByIdExterno(id)){
            return ResponseEntity.notFound().build();
        }
        Cartao cartao = cartaoRepository.findByIdExterno(id);

        Carteira carteira = cartaoClient.criaCarteiraCartao(cartao, carteiraCartaoRequest)
                .orElseThrow(() -> new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já associado com essa carteira"));

        carteira = carteiraRepository.save(carteira);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(carteira.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
