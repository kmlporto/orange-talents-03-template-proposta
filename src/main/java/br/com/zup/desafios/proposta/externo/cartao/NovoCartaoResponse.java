package br.com.zup.desafios.proposta.externo.cartao;

import br.com.zup.desafios.proposta.cartao.Cartao;
import br.com.zup.desafios.proposta.cartao.Bloqueio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NovoCartaoResponse {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<BloqueioResponse> bloqueios;
    private List<Aviso> avisos;
    private List<Carteira> carteiras;
    private List<Parcela> parcelas;
    private BigDecimal limite;
    private Renegociacao renegociacao;
    private Vencimento vencimento;
    private String idProposta;

    public NovoCartaoResponse(Cartao cartao) {
        this.id = cartao.getIdExterno();
        this.emitidoEm = cartao.getEmitidoEm();
        this.titular = cartao.getTitular();
        this.limite = cartao.getLimite();
        this.bloqueios = getBloqueiosResponse(cartao);
    }

    private List<BloqueioResponse> getBloqueiosResponse(Cartao cartao){
        List<BloqueioResponse> responseList = new ArrayList<>();
        if(!cartao.getBloqueios().isEmpty()){
            cartao.getBloqueios().forEach(bloqueio -> {
                BloqueioResponse response = new BloqueioResponse(bloqueio);
                responseList.add(response);
            });
        }
        return responseList;
    }

    public NovoCartaoResponse() {
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

    public List<BloqueioResponse> getBloqueios() {
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

    public static NovoCartaoResponse convert(Cartao cartao){
        return new NovoCartaoResponse(cartao);
    }
    public Cartao convert(){
        return new Cartao(id, emitidoEm, titular, limite);
    }
}

class BloqueioResponse {
    private String id;
    private Long idInterno;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;

    public BloqueioResponse(Bloqueio bloqueio) {
        this.idInterno = bloqueio.getId();
        this.bloqueadoEm = bloqueio.getBloqueadoEm();
        this.sistemaResponsavel = bloqueio.getResponsavel();
        this.ativo = bloqueio.isAtivo();
    }

    public String getId() {
        return id;
    }

    public Long getIdInterno() {
        return idInterno;
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

    public BloqueioResponse() {
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