package jp.fhaze.superburger.data.dao;

import jp.fhaze.superburger.data.entities.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredienteDao extends JpaRepository<Ingrediente, Long> {
    List<Ingrediente> findAllByOrderByNome();
}
