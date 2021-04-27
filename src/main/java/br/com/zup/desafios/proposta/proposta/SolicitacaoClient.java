package br.com.zup.desafios.proposta.proposta;

import br.com.zup.desafios.proposta.externo.solicitacao.SolicitacaoFeign;
import br.com.zup.desafios.proposta.externo.solicitacao.SolicitacaoRequest;
import br.com.zup.desafios.proposta.externo.solicitacao.SolicitacaoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.stereotype.Component;

@Component
public class SolicitacaoClient {

    private final SolicitacaoFeign solicitacaoFeign;
    private final ObjectMapper objectMapper;

    public SolicitacaoClient(SolicitacaoFeign solicitacaoFeign, ObjectMapper objectMapper) {
        this.solicitacaoFeign = solicitacaoFeign;
        this.objectMapper = objectMapper;
    }

    public SolicitacaoResponse consultaSolicitacao(SolicitacaoRequest request){
        try {
            return solicitacaoFeign.consulta(request);
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
