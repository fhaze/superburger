package jp.fhaze.superburger.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ResPedidoLanche {
    private BigDecimal valor;
    private List<ResPedidoLancheIngrediente> pedidoLancheIngredientes;
}
