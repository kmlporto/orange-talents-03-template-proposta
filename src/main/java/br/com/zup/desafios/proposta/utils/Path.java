package br.com.zup.desafios.proposta.utils;

public class Path {
    public final static String API = "/api";
    public final static String ID = "/{id}";
    public final static String PROPOSTA = API + "/propostas";
    public final static String CARTAO = API + "/cartoes";
    public final static String CARTAO_BIOMETRIA = CARTAO + ID + "/biometrias";
    public final static String BLOQUEIO = "/bloqueios";
    public final static String CARTAO_BLOQUEIO = CARTAO + ID + BLOQUEIO;
}
