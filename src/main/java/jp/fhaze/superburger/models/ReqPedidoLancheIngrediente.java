package jp.fhaze.superburger.models;

import lombok.Data;

import javax.validation.constraints.Max;

@Data
public class ReqPedidoLancheIngrediente {
    private Long id;
    @Max(10)
    private Long quantidade;
}
