package br.com.zup.desafios.proposta.cartao.biometria;

import br.com.zup.desafios.proposta.cartao.Cartao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String biometria;

    @Column(nullable = false)
    private LocalDateTime dataAssociacao = LocalDateTime.now();

    @ManyToOne
    private Cartao cartao;

    public Biometria(@NotNull String biometria, @NotNull Cartao cartao) {
        this.biometria = biometria;
        this.cartao = cartao;
    }

    @Deprecated
    public Biometria() {
    }

    public Long getId() {
        return id;
    }
}
