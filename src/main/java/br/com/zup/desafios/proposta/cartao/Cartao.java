package br.com.zup.desafios.proposta.cartao;

import br.com.zup.desafios.proposta.cartao.biometria.Biometria;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String idExterno;
    @Column(nullable = false)
    private LocalDateTime emitidoEm;
    @Column(nullable = false)
    private String titular;
    @Column(nullable = false)
    private BigDecimal limite;
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<Biometria> biometrias;

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
