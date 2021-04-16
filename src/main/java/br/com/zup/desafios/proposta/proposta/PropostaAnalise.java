package br.com.zup.desafios.proposta.proposta;

public enum PropostaAnalise {
    COM_RESTRICAO, SEM_RESTRICAO;

    public PropostaStatus convert(){
        if(this.equals(SEM_RESTRICAO))
            return PropostaStatus.ELEGIVEL;
        else
            return PropostaStatus.NAO_ELEGIVEL;
    }


}
