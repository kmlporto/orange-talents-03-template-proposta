package br.com.zup.desafios.proposta.proposta;

import br.com.zup.desafios.proposta.cartao.Cartao;
import br.com.zup.desafios.proposta.config.security.EncryptorConverter;
import br.com.zup.desafios.proposta.externo.solicitacao.SolicitacaoRequest;
import br.com.zup.desafios.proposta.externo.solicitacao.SolicitacaoResponse;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

import static br.com.zup.desafios.proposta.proposta.PropostaStatus.ANALISE;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Convert(converter = EncryptorConverter.class)
    @Column(nullable = false, unique = true)
    private String documento;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private BigDecimal salarioBruto;
    @Enumerated(value = EnumType.STRING)
    private PropostaStatus status = ANALISE;
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

    public Cartao getCartao() {
        return cartao;
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
