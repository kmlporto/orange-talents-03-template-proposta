package br.com.zup.desafios.proposta.externo.cartao;

public class CartaoResponseFeign {
    public RespostaFeign getResultado() {
        return resultado;
    }

    public void setResultado(RespostaFeign resultado) {
        this.resultado = resultado;
    }

    private RespostaFeign resultado;

    public CartaoResponseFeign() {
    }
}
