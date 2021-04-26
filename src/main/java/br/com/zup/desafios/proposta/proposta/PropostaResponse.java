package br.com.zup.desafios.proposta.proposta;

import br.com.zup.desafios.proposta.externo.cartao.NovoCartaoResponse;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class PropostaResponse {
    private String nome;
    private String documento;
    private String email;
    private String endereco;
    private BigDecimal salarioBruto;
    private PropostaStatus status;
    private NovoCartaoResponse cartao;

    public PropostaResponse(@NotNull Proposta proposta) {
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.endereco = proposta.getEndereco();
        this.salarioBruto = proposta.getSalarioBruto();
        this.status = proposta.getStatus();
        this.cartao = Objects.isNull(proposta.getCartao()) ? null : NovoCartaoResponse.convert(proposta.getCartao());
    }

    public static PropostaResponse convert(Proposta proposta){
        return new PropostaResponse(proposta);
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalarioBruto() {
        return salarioBruto;
    }

    public PropostaStatus getStatus() {
        return status;
    }

    public NovoCartaoResponse getCartao() {
        return cartao;
    }
}
