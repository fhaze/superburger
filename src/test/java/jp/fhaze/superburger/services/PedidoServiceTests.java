package jp.fhaze.superburger.services;

import jp.fhaze.superburger.data.entities.Ingrediente;
import jp.fhaze.superburger.data.entities.Pedido;
import jp.fhaze.superburger.utils.PedidoBuilder;
import org.junit.Assert;
import org.junit.Before;
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
public class PedidoServiceTests {
    @Autowired
    private PedidoService pedidoService;


    private Ingrediente hamburger;
    private Ingrediente alface;
    private Ingrediente queijo;

    @Before
    public void setUp() {
        hamburger = Ingrediente.builder().id(Ingrediente.ID_HAMBURGER_DE_CARNE).valor(new BigDecimal("3.00")).build();
        alface    = Ingrediente.builder().id(Ingrediente.ID_ALFACE).valor(new BigDecimal("0.40")).build();
        queijo    = Ingrediente.builder().id(Ingrediente.ID_QUEIJO).valor(new BigDecimal("1.50")).build();
    }

    @Test
    public void givenPedido_calculatePromocaoLight() {
        final Pedido pedido_1 = PedidoBuilder.builder()
                .addLanche("X-Especial")
                    .addIngrediente(hamburger)
                    .addIngrediente(alface)
                    .addIngrediente(queijo)
                    .build()
                .build();

        Assert.assertEquals("Preço com promoção", new BigDecimal("4.41"), pedidoService.calculatePrecoPedido(pedido_1));

        final Pedido pedido_2 = PedidoBuilder.builder()
                .addLanche("X-Lunático")
                    .addIngrediente(hamburger)
                    .addIngrediente(queijo)
                    .build()
                .build();

        Assert.assertEquals("Preço sem promoção ", new BigDecimal("4.50"),pedidoService.calculatePrecoPedido(pedido_2));
    }

    @Test
    public void givenPedido_calculateDesconto() {
        final Pedido pedido_1 = PedidoBuilder.builder()
                .addLanche("X-Carnudo")
                    .addIngrediente(hamburger, 3L)
                    .addIngrediente(queijo)
                    .build()
                .build();

        Assert.assertEquals(String.format("Preço com desconto de %.2f hamburger", PedidoService.DESCONTO_LIGHT),
                new BigDecimal("7.50"), pedidoService.calculatePrecoPedido(pedido_1));

        final Pedido pedido_2 = PedidoBuilder.builder()
                .addLanche("X-Queijada")
                    .addIngrediente(hamburger, 1L)
                    .addIngrediente(queijo, 3L)
                    .build()
                .build();

        Assert.assertEquals(String.format("Preço com desconto de %.2f queijo", PedidoService.DESCONTO_LIGHT),
                new BigDecimal("6.00"), pedidoService.calculatePrecoPedido(pedido_2));

        final Pedido pedido_3 = PedidoBuilder.builder()
                .addLanche("X-Everything")
                    .addIngrediente(hamburger, 3L)
                    .addIngrediente(queijo, 3L)
                    .build()
                .build();

        Assert.assertEquals(String.format("Preço com desconto de %.2f queijo & hamburger",PedidoService.DESCONTO_LIGHT),
                new BigDecimal("9.00"), pedidoService.calculatePrecoPedido(pedido_3));

        final Pedido pedido_4 = PedidoBuilder.builder()
                .addLanche("X-Tiny")
                .addIngrediente(hamburger, 1L)
                .build()
                .build();

        Assert.assertEquals("Preço normal sem desconto",
                new BigDecimal("3.00"), pedidoService.calculatePrecoPedido(pedido_4));
    }
}
