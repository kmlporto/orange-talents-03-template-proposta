package br.com.zup.desafios.proposta.externo.cartao;

import br.com.zup.desafios.proposta.cartao.carteira.CarteiraCartaoRequest;
import br.com.zup.desafios.proposta.cartao.carteira.TipoCarteira;
import br.com.zup.desafios.proposta.utils.annotation.ValueOfEnum;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CarteiraRequestFeign {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @ValueOfEnum(enumClass = TipoCarteira.class)
    private String carteira;

    public CarteiraRequestFeign(CarteiraCartaoRequest request) {
        this.email = request.getEmail();
        this.carteira = request.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
