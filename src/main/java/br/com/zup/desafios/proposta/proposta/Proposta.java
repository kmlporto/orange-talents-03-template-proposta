package br.com.zup.desafios.proposta.proposta;

import br.com.zup.desafios.proposta.cartao.Cartao;
import br.com.zup.desafios.proposta.externo.solicitacao.SolicitacaoClient;
import br.com.zup.desafios.proposta.externo.solicitacao.SolicitacaoRequest;
import br.com.zup.desafios.proposta.externo.solicitacao.SolicitacaoResponse;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String documento;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private BigDecimal salarioBruto;
    @Enumerated(value = EnumType.STRING)
    private PropostaStatus status;
    @OneToOne(cascade = CascadeType.ALL)
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(@NotEmpty String nome, @NotEmpty String documento, @NotEmpty String email, @NotEmpty String endereco, @NotNull @Positive BigDecimal salarioBruto) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.salarioBruto = salarioBruto;
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public Proposta solicita(SolicitacaoClient solicitacaoClient, PropostaRepository propostaRepository) {
        SolicitacaoRequest request = new SolicitacaoRequest(id, nome, documento);
        SolicitacaoResponse response = solicitacaoClient.consultaSolicitacao(request);

        status = response.getResultadoSolicitacao().convert();

        return propostaRepository.save(this);
    }

    public void addCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
