package br.com.zup.desafios.proposta.externo.solicitacao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.stereotype.Component;

@Component
public class SolicitacaoClient {

    private final Solicitacao solicitacao;
    private final ObjectMapper objectMapper;

    public SolicitacaoClient(Solicitacao solicitacao, ObjectMapper objectMapper) {
        this.solicitacao = solicitacao;
        this.objectMapper = objectMapper;
    }

    public SolicitacaoResponse consultaSolicitacao(SolicitacaoRequest request){
        try {
            return solicitacao.consulta(request);
        }catch (FeignException.UnprocessableEntity exception){
            try {
                String body = exception.contentUTF8();
                return objectMapper.readValue(body, SolicitacaoResponse.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
