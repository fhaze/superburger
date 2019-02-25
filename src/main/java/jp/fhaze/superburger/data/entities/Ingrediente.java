package jp.fhaze.superburger.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(generator = "ingrediente_seq")
    @SequenceGenerator(name = "ingrediente_seq", allocationSize = 1)
    private Long id;
    private String nome;
    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    public static final Long ID_ALFACE = 1L;
    public static final Long ID_BACON = 2L;
    public static final Long ID_HAMBURGER_DE_CARNE = 3L;
    public static final Long ID_OVO = 4L;
    public static final Long ID_QUEIJO = 5L;
}
