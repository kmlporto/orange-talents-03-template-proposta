package br.com.zup.desafios.proposta.proposta;

import br.com.zup.desafios.proposta.utils.annotation.DocumentValid;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaPersist {
    @NotEmpty
    private String nome;
    @NotEmpty
    @DocumentValid
    private String documento;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String endereco;
    @NotNull
    @Positive
    private BigDecimal salarioBruto;

    public PropostaPersist(@NotEmpty String nome, @NotEmpty String documento, @NotEmpty String email, @NotEmpty String endereco, @NotNull @Positive BigDecimal salarioBruto, @NotEmpty String analise) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.endereco = endereco;
        this.salarioBruto = salarioBruto;
    }

    public Proposta convert() {
        return new Proposta(nome, documento, email, endereco, salarioBruto);
    }

    public String getDocumento() {
        return documento;
    }
}
