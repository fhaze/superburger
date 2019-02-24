package jp.fhaze.superburger.models;

import lombok.Data;

import java.util.List;

@Data
public class ReqPedidoLanche {
    private String nome;
    private List<ReqPedidoLancheIngrediente> ingredientes;
}
