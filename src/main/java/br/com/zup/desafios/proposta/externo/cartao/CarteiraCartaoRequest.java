package br.com.zup.desafios.proposta.externo.cartao;

import br.com.zup.desafios.proposta.cartao.TipoCarteira;
import br.com.zup.desafios.proposta.utils.annotation.ValueOfEnum;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CarteiraCartaoRequest {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @ValueOfEnum(enumClass = TipoCarteira.class)
    private String carteira;

    public CarteiraCartaoRequest(@NotEmpty @Email String email, @NotEmpty @ValueOfEnum(enumClass = TipoCarteira.class) String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
