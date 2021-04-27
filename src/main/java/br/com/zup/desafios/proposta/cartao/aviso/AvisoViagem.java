package br.com.zup.desafios.proposta.cartao.aviso;

import br.com.zup.desafios.proposta.cartao.Cartao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class AvisoViagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String destino;
    @Column(nullable = false)
    private LocalDate validoAte;
    @Column(nullable = false)
    private String responsavel;
    @Column(nullable = false)
    private String ipClient;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(@NotNull Cartao cartao, @NotNull @Valid AvisoViagemPersist avisoViagemPersist, @NotEmpty String responsavel, @NotEmpty String ipClient) {
        this.cartao = cartao;
        this.destino = avisoViagemPersist.getDestino();
        this.validoAte = avisoViagemPersist.getValidoAte();
        this.responsavel = responsavel;
        this.ipClient = ipClient;
    }
}
