package br.com.zup.desafios.proposta.externo.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static br.com.zup.desafios.proposta.utils.Path.BLOQUEIO;
import static br.com.zup.desafios.proposta.utils.Path.ID;

@FeignClient(url = "${externo.cartao}", name="cartao")
public interface CartaoFeign {

    @PostMapping
    CartaoResponse criaCartao(NovoCartaoRequest novoCartaoRequest);

    @PostMapping(value = ID + BLOQUEIO)
    BloqueioCartaoResponse bloqueaCartao(@PathVariable String id, @RequestBody BloqueioCartaoRequest request);

}
