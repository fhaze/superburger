package jp.fhaze.superburger.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResPedidoLancheIngrediente {
    private String nome;
    private Long quantidade;
    private String valor;
}
