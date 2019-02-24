package jp.fhaze.superburger.utils;

import jp.fhaze.superburger.data.entities.Ingrediente;
import jp.fhaze.superburger.data.entities.PedidoLanche;

import java.util.ArrayList;

public class LancheBuilder {
    private final PedidoBuilder pedidoBuilder;
    private final PedidoLanche pedidoLanche;

    private LancheBuilder(PedidoBuilder pedidoBuilder, PedidoLanche pedidoLanche) {
        this.pedidoBuilder = pedidoBuilder;
        this.pedidoLanche = pedidoLanche;
    }

    public static LancheBuilder builder(PedidoBuilder pedidoBuilder, PedidoLanche pedidoLanche) {
        return new LancheBuilder(pedidoBuilder, pedidoLanche);
    }

    public PedidoBuilder build() {
        return pedidoBuilder;
    }

    public LancheBuilder addIngrediente(Ingrediente ingrediente, Long quantidade) {
        if (pedidoLanche.getIngredientes() == null)
            pedidoLanche.setIngredientes(new ArrayList<>());

        for (int i = 0; i < quantidade; i++) {
            pedidoLanche.getIngredientes().add(ingrediente);
        }
        return this;
    }

    public LancheBuilder addIngrediente(Ingrediente ingrediente) {
        return addIngrediente(ingrediente, 1L);
    }
}
