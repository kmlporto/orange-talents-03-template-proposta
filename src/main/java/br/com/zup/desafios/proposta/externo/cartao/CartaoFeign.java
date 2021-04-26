package br.com.zup.desafios.proposta.externo.cartao;

import br.com.zup.desafios.proposta.cartao.AvisoViagemPersist;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static br.com.zup.desafios.proposta.utils.Path.AVISO;
import static br.com.zup.desafios.proposta.utils.Path.BLOQUEIO;

@FeignClient(url = "${externo.cartao}", name="cartao")
public interface CartaoFeign {

    @PostMapping
    NovoCartaoResponse criaCartao(NovoCartaoRequest novoCartaoRequest);

    @PostMapping(value = BLOQUEIO)
    CartaoResponse bloqueaCartao(@PathVariable String id, @RequestBody BloqueioCartaoRequest request);

    @PostMapping(value = AVISO)
    CartaoResponse criaAvisoViagem(@PathVariable String id, @RequestBody AvisoViagemPersist request);

}
