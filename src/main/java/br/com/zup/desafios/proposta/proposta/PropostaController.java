package br.com.zup.desafios.proposta.proposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static br.com.zup.desafios.proposta.utils.Path.PROPOSTA;

@RestController
@RequestMapping(value = PROPOSTA)
public class PropostaController {

    private final PropostaRepository propostaRepository;

    public PropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @PostMapping
    public ResponseEntity<URI> cadastra(@Valid @RequestBody PropostaPersist propostaPersist){
        Proposta proposta = propostaRepository.save(propostaPersist.convert());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(uri);
    }
}
