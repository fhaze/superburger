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
    @GeneratedValue(generator = "pedido_seq")
    @SequenceGenerator(name = "pedido_seq", allocationSize = 1)
    private Long id;
    @OneToMany
    @JoinColumn
    private List<PedidoLanche> pedidoLanches;
    private BigDecimal valor;
}
