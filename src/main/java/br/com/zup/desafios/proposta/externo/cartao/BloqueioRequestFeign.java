package br.com.zup.desafios.proposta.externo.cartao;

public class BloqueioRequestFeign {
    private String sistemaResponsavel;

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public BloqueioRequestFeign(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}
