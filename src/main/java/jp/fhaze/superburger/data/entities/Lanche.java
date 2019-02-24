package jp.fhaze.superburger.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lanche {
    @Id
    private Long id;
    private String nome;
    @ManyToMany
    @JoinColumn
    @OrderBy("nome ASC")
    private List<Ingrediente> ingredientes;

    public Lanche addIngrediente(Ingrediente ingrediente) {
        if (ingredientes == null)
            ingredientes = new ArrayList<>();

        ingredientes.add(ingrediente);
        return this;
    }

    public BigDecimal getValor() {
        return ingredientes.stream().map(Ingrediente::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static final Long ID_X_BACON = 1L;
    public static final Long ID_X_BURGER = 2L;
    public static final Long ID_X_EGG = 3L;
    public static final Long ID_X_EGG_BACON = 4L;
}
