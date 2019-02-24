package jp.fhaze.superburger;

import jp.fhaze.superburger.data.dao.IngredienteDao;
import jp.fhaze.superburger.data.dao.LancheDao;
import jp.fhaze.superburger.data.entities.Ingrediente;
import jp.fhaze.superburger.data.entities.Lanche;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class Bootstrap {

    private final IngredienteDao ingredienteDao;
    private final LancheDao lancheDao;

    private Ingrediente bacon;
    private Ingrediente hamburgerDeCarne;
    private Ingrediente ovo;
    private Ingrediente queijo;

    public Bootstrap(IngredienteDao ingredienteDao, LancheDao lancheDao) {
        this.ingredienteDao = ingredienteDao;
        this.lancheDao = lancheDao;
    }

    @PostConstruct
    private void init() {
        ingredientesIniciais();
        lanchesIniciais();
    }

    private void ingredientesIniciais() {
        ingredienteDao.save(Ingrediente.builder()
                .id(Ingrediente.ID_ALFACE)
                .nome("Alface")
                .valor(new BigDecimal("0.40"))
                .build());

        bacon = Ingrediente.builder()
                .id(Ingrediente.ID_BACON)
                .nome("Bacon")
                .valor(new BigDecimal("2.00"))
                .build();

        hamburgerDeCarne = Ingrediente.builder()
                .id(Ingrediente.ID_HAMBURGER_DE_CARNE)
                .nome("Hambúrguer de carne")
                .valor(new BigDecimal("3.00"))
                .build();

        ovo = Ingrediente.builder()
                .id(Ingrediente.ID_OVO)
                .nome("Ovo")
                .valor(new BigDecimal("0.80"))
                .build();

        queijo = Ingrediente.builder()
                .id(Ingrediente.ID_QUEIJO)
                .nome("Queijo")
                .valor(new BigDecimal("1.50"))
                .build();

        ingredienteDao.save(bacon);
        ingredienteDao.save(hamburgerDeCarne);
        ingredienteDao.save(ovo);
        ingredienteDao.save(queijo);
    }

    private void lanchesIniciais() {
        lancheDao.save(Lanche.builder()
                .id(Lanche.ID_X_BACON)
                .nome("X-Bacon")
                .build()
                .addIngrediente(bacon)
                .addIngrediente(hamburgerDeCarne)
                .addIngrediente(queijo));

        lancheDao.save(Lanche.builder()
                .id(Lanche.ID_X_BURGER)
                .nome("X-Burger")
                .build()
                .addIngrediente(hamburgerDeCarne)
                .addIngrediente(queijo));

        lancheDao.save(Lanche.builder()
                .id(Lanche.ID_X_EGG)
                .nome("X-Egg")
                .build()
                .addIngrediente(ovo)
                .addIngrediente(hamburgerDeCarne)
                .addIngrediente(queijo));

        lancheDao.save(Lanche.builder()
                .id(Lanche.ID_X_EGG_BACON)
                .nome("X-Egg Bacon")
                .build()
                .addIngrediente(ovo)
                .addIngrediente(bacon)
                .addIngrediente(hamburgerDeCarne)
                .addIngrediente(queijo));
    }
}
