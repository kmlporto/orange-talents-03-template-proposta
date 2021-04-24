package br.com.zup.desafios.proposta.cartao;

import javax.validation.constraints.NotEmpty;
import java.util.Base64;

public class BiometriaPersist {
    @NotEmpty
    private String biometria;

    public BiometriaPersist() {
    }

    public String getBiometria() {
        return biometria;
    }

    public void setBiometria(String biometria) {
        this.biometria = biometria;
    }

    public Biometria convert(Cartao cartao) {
        biometria = Base64.getEncoder().encodeToString(biometria.getBytes());
        return new Biometria(biometria, cartao);
    }
}
