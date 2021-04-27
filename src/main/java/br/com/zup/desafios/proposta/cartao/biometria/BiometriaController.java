package br.com.zup.desafios.proposta.cartao.biometria;

import br.com.zup.desafios.proposta.cartao.Cartao;
import br.com.zup.desafios.proposta.cartao.CartaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static br.com.zup.desafios.proposta.utils.Path.BIOMETRIA;
import static br.com.zup.desafios.proposta.utils.Path.CARTAO;

@RestController
@RequestMapping(value = CARTAO + BIOMETRIA)
public class BiometriaController {

    private final CartaoRepository cartaoRepository;
    private final BiometriaRepository biometriaRepository;

    public BiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.biometriaRepository = biometriaRepository;
    }

    @PostMapping
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
