package br.com.zup.desafios.proposta.externo.cartao;

import br.com.zup.desafios.proposta.cartao.Cartao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CartaoResponse {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<Bloqueio> bloqueios;
    private List<Aviso> avisos;
    private List<Carteira> carteiras;
    private List<Parcela> parcelas;
    private BigDecimal limite;
    private Renegociacao renegociacao;
    private Vencimento vencimento;
    private String idProposta;

    public CartaoResponse(Cartao cartao) {
        this.id = cartao.getIdExterno();
        this.emitidoEm = cartao.getEmitidoEm();
        this.titular = cartao.getTitular();
        this.limite = cartao.getLimite();
    }

    public CartaoResponse() {
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public List<Aviso> getAvisos() {
        return avisos;
    }

    public List<Carteira> getCarteiras() {
        return carteiras;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public static CartaoResponse convert(Cartao cartao){
        return new CartaoResponse(cartao);
    }
    public Cartao convert(){
        return new Cartao(id, emitidoEm, titular, limite);
    }
}

class Bloqueio{
    private String id;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;

    public String getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Bloqueio() {
    }
}

class Aviso{
    private LocalDate validoAte;
    private String destino;

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public Aviso() {
    }
}

class Carteira{
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }

    public Carteira() {
    }
}
class Parcela{
    private String id;
    private int quantidade;
    private BigDecimal valor;

    public String getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Parcela() {
    }
}

class Renegociacao{
    private String id;
    private int quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    public String getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public Renegociacao() {
    }
}

class Vencimento{
    private String id;
    private int dia;
    private LocalDateTime dataDeCriacao;

    public String getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public Vencimento() {
    }
}