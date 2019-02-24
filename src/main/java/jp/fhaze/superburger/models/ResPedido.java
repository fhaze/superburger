package jp.fhaze.superburger.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResPedido {
    private BigDecimal valor;
    private List<ResPedidoLanche> pedidoLanches;
}
