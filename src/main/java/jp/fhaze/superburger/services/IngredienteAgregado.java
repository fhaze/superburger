package jp.fhaze.superburger.services;

import jp.fhaze.superburger.data.entities.Ingrediente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe auxiliar que agrega ingredientes para facilitar o calculo de promoções e descontos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredienteAgregado {
    private Long id;
    private BigDecimal valor;
    private String nome;
    private Long quantidade;

    public static List<IngredienteAgregado> from(List<Ingrediente> ingredientes) {
        final Set<Long> ids = ingredientes.stream().map(Ingrediente::getId).collect(Collectors.toSet());
        return ids.stream().map(id -> {
            final IngredienteAgregado ingredienteAgregado = new IngredienteAgregado();
            final Ingrediente ingrediente = ingredientes.stream()
                    .filter(ing -> ing.getId().equals(id))
                    .findFirst()
                    .get();

            ingredienteAgregado.setId(ingrediente.getId());
            ingredienteAgregado.setNome(ingrediente.getNome());
            ingredienteAgregado.setValor(ingrediente.getValor());
            ingredienteAgregado.setQuantidade(ingredientes.stream().filter(ing -> ing.getId().equals(id)).count());
            return ingredienteAgregado;
        }).collect(Collectors.toList());
    }
}
