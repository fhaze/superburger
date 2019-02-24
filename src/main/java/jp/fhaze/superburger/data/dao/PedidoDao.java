package jp.fhaze.superburger.data.dao;

import jp.fhaze.superburger.data.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoDao extends JpaRepository<Pedido, Long> {
}
