package jp.fhaze.superburger.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jp.fhaze.superburger.services.IngredienteAgregado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PedidoLanche {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    @ManyToMany
    @JoinColumn
    @JsonIgnore
    private List<Ingrediente> ingredientes;
    private BigDecimal valor;

    public List<IngredienteAgregado> getIngredientesAgredados() {
        return IngredienteAgregado.from(ingredientes);
    }
}
