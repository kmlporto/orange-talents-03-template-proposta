package br.com.zup.desafios.proposta.cartao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.zup.desafios.proposta.utils.Path.CARTAO_AVISO;

@RestController
@RequestMapping(value = CARTAO_AVISO)
public class AvisoViagemController {

    private final CartaoRepository cartaoRepository;

    public AvisoViagemController(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @PostMapping
    public ResponseEntity<?> aviso(@PathVariable String id, @Valid @RequestBody AvisoViagemPersist avisoViagemPersist, @RequestHeader("User-Agent") String userAgent, @RequestHeader("ip-client") String ipClient){
        if(!cartaoRepository.existsByIdExterno(id)){
            return ResponseEntity.notFound().build();
        }
        Cartao cartao = cartaoRepository.findByIdExterno(id);
        AvisoViagem aviso = new AvisoViagem(cartao, avisoViagemPersist, userAgent, ipClient);
        cartao.addAviso(aviso);

        cartaoRepository.save(cartao);

        return ResponseEntity.ok().build();

    }
}
