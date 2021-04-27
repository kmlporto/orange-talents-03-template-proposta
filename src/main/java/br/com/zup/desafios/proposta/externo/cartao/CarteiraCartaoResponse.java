package br.com.zup.desafios.proposta.externo.cartao;

public class CarteiraCartaoResponse {

    private RespostaStatus resultado;
    private String id;

    public CarteiraCartaoResponse() {
    }

    public RespostaStatus getResultado() {
        return resultado;
    }

    public void setResultado(RespostaStatus resultado) {
        this.resultado = resultado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
