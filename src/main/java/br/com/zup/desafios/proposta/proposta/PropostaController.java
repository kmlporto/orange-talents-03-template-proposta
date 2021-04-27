package br.com.zup.desafios.proposta.proposta;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static br.com.zup.desafios.proposta.utils.Path.PROPOSTA;
import static br.com.zup.desafios.proposta.utils.Path.ID;

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

    @GetMapping(value = ID)
    public ResponseEntity<PropostaResponse> consulta(@PathVariable Long id){
        if(!propostaRepository.existsById(id))
            return ResponseEntity.notFound().build();
        Proposta proposta = propostaRepository.getOne(id);

        return ResponseEntity.ok(PropostaResponse.convert(proposta));
    }
}
