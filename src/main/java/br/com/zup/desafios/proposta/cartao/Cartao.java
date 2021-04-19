package br.com.zup.desafios.proposta.cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idExterno;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;

    public Long getId() {
        return id;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public Cartao() {
    }

    public Cartao(String idExterno, LocalDateTime emitidoEm, String titular, BigDecimal limite) {
        this.idExterno = idExterno;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
    }
}
