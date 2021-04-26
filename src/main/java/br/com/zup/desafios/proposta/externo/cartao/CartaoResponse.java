package br.com.zup.desafios.proposta.externo.cartao;

public class CartaoResponse {
    public RespostaStatus getResultado() {
        return resultado;
    }

    public void setResultado(RespostaStatus resultado) {
        this.resultado = resultado;
    }

    private RespostaStatus resultado;

    public CartaoResponse() {
    }
}
