package br.com.zup.desafios.proposta.cartao;

import br.com.zup.desafios.proposta.proposta.Proposta;
import br.com.zup.desafios.proposta.proposta.PropostaRepository;
import br.com.zup.desafios.proposta.proposta.PropostaStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartaoPropostaSchendule {

    private final PropostaRepository propostaRepository;
    private final CartaoClient cartaoClient;

    public CartaoPropostaSchendule(PropostaRepository propostaRepository, CartaoClient cartaoClient) {
        this.propostaRepository = propostaRepository;
        this.cartaoClient = cartaoClient;
    }

    @Scheduled(fixedDelayString = "${periodicidade.schendule.proposta-cartao}")
    private void operacao() {
        List<Proposta> propostasLegiveisSemCartao = propostaRepository.findByStatusAndCartaoIsNull(PropostaStatus.ELEGIVEL);
        adicionaCartao(propostasLegiveisSemCartao);
    }

    private void adicionaCartao(List<Proposta> propostas){
        propostas.forEach(proposta -> {
            Cartao cartao = cartaoClient.criaCartao(proposta);
            proposta.addCartao(cartao);

            propostaRepository.save(proposta);
        });
    }
}
