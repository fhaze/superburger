package jp.fhaze.superburger.data.entities;

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
public class Pedido {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToMany
    @JoinColumn
    private List<PedidoLanche> pedidoLanches;
    private BigDecimal valor;
}
