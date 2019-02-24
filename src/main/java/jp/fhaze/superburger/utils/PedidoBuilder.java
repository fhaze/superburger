package jp.fhaze.superburger.utils;

import jp.fhaze.superburger.data.entities.*;

import java.util.ArrayList;
import java.util.List;

public class PedidoBuilder {

    private final List<PedidoLanche> pedidoLanches;

    private PedidoBuilder() {
        pedidoLanches = new ArrayList<>();
    }

    public static PedidoBuilder builder() {
        return new PedidoBuilder();
    }

    public LancheBuilder addLanche(String nome) {
        final PedidoLanche pedidoLanche = new PedidoLanche();
        pedidoLanche.setNome(nome);
        pedidoLanches.add(pedidoLanche);
        return LancheBuilder.builder(this, pedidoLanche);
    }

    public LancheBuilder addLanche(Lanche lanche) {
        final PedidoLanche pedidoLanche = new PedidoLanche();
        pedidoLanche.setNome(lanche.getNome());
        pedidoLanche.setIngredientes(lanche.getIngredientes());
        pedidoLanches.add(pedidoLanche);
        return LancheBuilder.builder(this, pedidoLanche);
    }

    public Pedido build() {
        return Pedido.builder().pedidoLanches(pedidoLanches).build();
    }
}
