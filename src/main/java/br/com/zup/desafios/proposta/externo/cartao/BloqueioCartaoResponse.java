package br.com.zup.desafios.proposta.externo.cartao;

public class BloqueioCartaoResponse {
    public BloqueioStatus getResultado() {
        return resultado;
    }

    public void setResultado(BloqueioStatus resultado) {
        this.resultado = resultado;
    }

    private BloqueioStatus resultado;

    public BloqueioCartaoResponse() {
    }
}
