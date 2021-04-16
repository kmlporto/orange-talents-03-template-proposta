package br.com.zup.desafios.proposta.externo.solicitacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${externo.solicitacao}", name="solicitacao")
public interface Solicitacao {
    @PostMapping
    SolicitacaoResponse consulta(SolicitacaoRequest request);
}

