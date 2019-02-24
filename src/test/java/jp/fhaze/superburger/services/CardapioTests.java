package jp.fhaze.superburger.services;

import jp.fhaze.superburger.data.dao.LancheDao;
import jp.fhaze.superburger.data.entities.Lanche;
import jp.fhaze.superburger.data.entities.Pedido;
import jp.fhaze.superburger.utils.PedidoBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    public void givenLanches_calculatePrecos() {
        final Lanche xBacon = lancheDao.getOne(Lanche.ID_X_BACON);
        final Pedido pedidoXBacon = PedidoBuilder.builder()
                .addLanche(xBacon)
                    .build()
                .build();

        Assert.assertEquals("Preço do X-Bacon", new BigDecimal("6.50"), pedidoService.calculatePrecoPedido(pedidoXBacon));

        final Lanche xBurger = lancheDao.getOne(Lanche.ID_X_BURGER);
        final Pedido pedidoXBurger = PedidoBuilder.builder()
                .addLanche(xBurger)
                    .build()
                .build();

        Assert.assertEquals("Preço do X-Burger", new BigDecimal("4.50"), pedidoService.calculatePrecoPedido(pedidoXBurger));

        final Lanche xEgg = lancheDao.getOne(Lanche.ID_X_EGG);
        final Pedido pedidoXEgg = PedidoBuilder.builder()
                .addLanche(xEgg)
                    .build()
                .build();

        Assert.assertEquals("Preço do X-Egg", new BigDecimal("5.30"), pedidoService.calculatePrecoPedido(pedidoXEgg));

        final Lanche xEggBacon = lancheDao.getOne(Lanche.ID_X_EGG_BACON);
        final Pedido pedidoXEggBacon = PedidoBuilder.builder()
                .addLanche(xEggBacon)
                    .build()
                .build();

        Assert.assertEquals("Preço do X-Egg Bacon", new BigDecimal("7.30"), pedidoService.calculatePrecoPedido(pedidoXEggBacon));
    }

}
