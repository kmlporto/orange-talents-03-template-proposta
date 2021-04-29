package br.com.zup.desafios.proposta.externo.solicitacao;

import io.opentracing.contrib.spring.cloud.feign.FeignTracingAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(url = "${externo.solicitacao}", name="solicitacao", configuration = {FeignTracingAutoConfiguration.class})
public interface SolicitacaoFeign {
    @PostMapping
    SolicitacaoResponse consulta(SolicitacaoRequest request);
}

