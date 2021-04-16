package br.com.zup.desafios.proposta.proposta;

import br.com.zup.desafios.proposta.externo.solicitacao.SolicitacaoClient;
import org.springframework.http.HttpStatus;
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
    private final SolicitacaoClient solicitacaoClient;

    public PropostaController(PropostaRepository propostaRepository, SolicitacaoClient solicitacaoClient) {
        this.propostaRepository = propostaRepository;
        this.solicitacaoClient = solicitacaoClient;
    }

    @PostMapping
    public ResponseEntity<Void> cadastra(@Valid @RequestBody PropostaPersist propostaPersist){
        if(propostaRepository.existsByDocumento(propostaPersist.getDocumento()))
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

        Proposta proposta = propostaRepository.save(propostaPersist.convert()).solicita(solicitacaoClient, propostaRepository);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
