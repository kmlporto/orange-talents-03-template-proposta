package br.com.zup.desafios.proposta.proposta;

import br.com.zup.desafios.proposta.utils.annotation.DocumentValid;
import br.com.zup.desafios.proposta.utils.annotation.Unique;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaPersist {
    @NotEmpty
    @DocumentValid
    @Unique(clazz = Proposta.class, field = "documento")
    private String documento;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String endereco;
    @NotNull
    @Positive
    private BigDecimal salarioBruto;

    public PropostaPersist(@NotEmpty String documento, @NotEmpty String email, @NotEmpty String endereco, @NotNull @Positive BigDecimal salarioBruto) {
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.salarioBruto = salarioBruto;
    }

    public Proposta convert() {
        return new Proposta(documento, email, endereco, salarioBruto);
    }

}
