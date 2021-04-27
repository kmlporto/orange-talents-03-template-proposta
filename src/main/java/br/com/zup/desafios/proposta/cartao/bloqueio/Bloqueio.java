package br.com.zup.desafios.proposta.cartao.bloqueio;

import br.com.zup.desafios.proposta.cartao.Cartao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime bloqueadoEm = LocalDateTime.now();
    @Column(nullable = false)
    private String responsavel;
    @Column(nullable = false)
    private boolean ativo = true;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String responsavel, Cartao cartao) {
        this.responsavel = responsavel;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
