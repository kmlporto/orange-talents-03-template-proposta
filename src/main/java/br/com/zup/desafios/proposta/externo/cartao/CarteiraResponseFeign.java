package br.com.zup.desafios.proposta.externo.cartao;

public class CarteiraResponseFeign {

    private RespostaFeign resultado;
    private String id;

    public CarteiraResponseFeign() {
    }

    public RespostaFeign getResultado() {
        return resultado;
    }

    public void setResultado(RespostaFeign resultado) {
        this.resultado = resultado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
