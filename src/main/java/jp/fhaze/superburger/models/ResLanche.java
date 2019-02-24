package jp.fhaze.superburger.models;

import jp.fhaze.superburger.data.entities.Ingrediente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class ResLanche {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private List<Ingrediente> ingredientes;
}
