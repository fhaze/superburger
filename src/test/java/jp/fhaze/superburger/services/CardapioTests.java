package jp.fhaze.superburger.services;

import jp.fhaze.superburger.data.dao.IngredienteDao;
import jp.fhaze.superburger.data.dao.LancheDao;
import jp.fhaze.superburger.data.entities.Ingrediente;
import jp.fhaze.superburger.data.entities.Lanche;
import jp.fhaze.superburger.data.entities.Pedido;
import jp.fhaze.superburger.utils.PedidoBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CardapioTests {
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private LancheDao lancheDao;
    @Autowired
    private IngredienteDao ingredienteDao;

    @Before
    public void setUp() {
        // Define valores padroes para os ingredientes para evitar problemas com a inflação.
        final Ingrediente alface = ingredienteDao.getOne(Ingrediente.ID_ALFACE);
        alface.setValor(new BigDecimal("0.40"));
        ingredienteDao.save(alface);

        final Ingrediente bacon = ingredienteDao.getOne(Ingrediente.ID_BACON);
        bacon.setValor(new BigDecimal("2.00"));
        ingredienteDao.save(bacon);

        final Ingrediente hamburger = ingredienteDao.getOne(Ingrediente.ID_HAMBURGER_DE_CARNE);
        hamburger.setValor(new BigDecimal("3.00"));
        ingredienteDao.save(hamburger);

        final Ingrediente ovo = ingredienteDao.getOne(Ingrediente.ID_OVO);
        ovo.setValor(new BigDecimal("0.80"));
        ingredienteDao.save(ovo);

        final Ingrediente queijo = ingredienteDao.getOne(Ingrediente.ID_QUEIJO);
        queijo.setValor(new BigDecimal("1.50"));
        ingredienteDao.save(queijo);
    }

    @Test
    public void givenLanches_calculatePrecos() {
        final Lanche xBacon = lancheDao.getOne(Lanche.ID_X_BACON);
        final Pedido pedidoXBacon = PedidoBuilder.builder()
                .addLanche(xBacon)
                    .build()
                .build();

        Assert.assertEquals("Preço do X-Bacon", new BigDecimal("6.50"),
                pedidoService.calculatePrecoPedido(pedidoXBacon));

        final Lanche xBurger = lancheDao.getOne(Lanche.ID_X_BURGER);
        final Pedido pedidoXBurger = PedidoBuilder.builder()
                .addLanche(xBurger)
                    .build()
                .build();

        Assert.assertEquals("Preço do X-Burger", new BigDecimal("4.50"),
                pedidoService.calculatePrecoPedido(pedidoXBurger));

        final Lanche xEgg = lancheDao.getOne(Lanche.ID_X_EGG);
        final Pedido pedidoXEgg = PedidoBuilder.builder()
                .addLanche(xEgg)
                    .build()
                .build();

        Assert.assertEquals("Preço do X-Egg", new BigDecimal("5.30"),
                pedidoService.calculatePrecoPedido(pedidoXEgg));

        final Lanche xEggBacon = lancheDao.getOne(Lanche.ID_X_EGG_BACON);
        final Pedido pedidoXEggBacon = PedidoBuilder.builder()
                .addLanche(xEggBacon)
                    .build()
                .build();

        Assert.assertEquals("Preço do X-Egg Bacon", new BigDecimal("7.30"),
                pedidoService.calculatePrecoPedido(pedidoXEggBacon));
    }

}
