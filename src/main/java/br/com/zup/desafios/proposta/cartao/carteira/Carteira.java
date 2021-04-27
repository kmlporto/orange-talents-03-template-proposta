package br.com.zup.desafios.proposta.cartao.carteira;

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
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String idExterno;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private LocalDateTime associadaEm = LocalDateTime.now();
    @Column(nullable = false)
    private String emissor;
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(@NotNull Cartao cartao, @Valid @NotNull CarteiraCartaoRequest request, @NotEmpty String idExterno) {
        this.cartao = cartao;
        this.email = request.getEmail();
        this.emissor = request.getCarteira();
        this.idExterno = idExterno;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carteira)) return false;
        Carteira carteira = (Carteira) o;
        return email.equals(carteira.email) && emissor.equals(carteira.emissor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, emissor);
    }
}
