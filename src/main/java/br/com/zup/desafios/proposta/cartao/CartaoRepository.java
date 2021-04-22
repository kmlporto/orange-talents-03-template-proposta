package br.com.zup.desafios.proposta.cartao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    boolean existsByIdExterno(String idExterno);

    Cartao findByIdExterno(String idExterno);

}
