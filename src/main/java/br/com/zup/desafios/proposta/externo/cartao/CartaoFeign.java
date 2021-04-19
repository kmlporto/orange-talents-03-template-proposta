package br.com.zup.desafios.proposta.externo.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${externo.cartao}", name="cartao")
public interface CartaoFeign {

    @PostMapping
    CartaoResponse criaCartao(NovoCartaoRequest novoCartaoRequest);

}
