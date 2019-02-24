package jp.fhaze.superburger.data.dao;

import jp.fhaze.superburger.data.entities.Lanche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancheDao extends JpaRepository<Lanche, Long> {
}
