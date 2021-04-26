package br.com.zup.desafios.proposta.cartao;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemPersist {
    @NotEmpty
    private String destino;

    @NotNull
    private LocalDate validoAte;

    public AvisoViagemPersist(@NotEmpty String destino, @NotNull LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
