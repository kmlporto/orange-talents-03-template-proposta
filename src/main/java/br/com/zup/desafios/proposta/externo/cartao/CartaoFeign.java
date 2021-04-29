package br.com.zup.desafios.proposta.externo.cartao;

import br.com.zup.desafios.proposta.cartao.aviso.AvisoViagemPersist;
import io.opentracing.contrib.spring.cloud.feign.FeignTracingAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static br.com.zup.desafios.proposta.utils.Path.AVISO;
import static br.com.zup.desafios.proposta.utils.Path.BLOQUEIO;
import static br.com.zup.desafios.proposta.utils.Path.CARTEIRA;

@FeignClient(url = "${externo.cartao}", name="cartao", configuration = {FeignTracingAutoConfiguration.class})
public interface CartaoFeign {

    @PostMapping
    NovoCartaoResponseFeign criaCartao(NovoCartaoRequestFeign request);

    @PostMapping(value = BLOQUEIO)
    CartaoResponseFeign bloqueaCartao(@PathVariable String id, @RequestBody BloqueioRequestFeign request);

    @PostMapping(value = AVISO)
    CartaoResponseFeign criaAvisoViagem(@PathVariable String id, @RequestBody AvisoViagemPersist request);

    @PostMapping(value = CARTEIRA)
    CarteiraResponseFeign criaCarteira(@PathVariable String id, @RequestBody CarteiraRequestFeign request);

}
