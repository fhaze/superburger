package jp.fhaze.superburger.services;

import jp.fhaze.superburger.data.dao.PedidoDao;
import jp.fhaze.superburger.data.dao.PedidoLancheDao;
import jp.fhaze.superburger.data.entities.Ingrediente;
import jp.fhaze.superburger.data.entities.Pedido;
import jp.fhaze.superburger.data.entities.PedidoLanche;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class PedidoService {

    public  final static BigDecimal DESCONTO_LIGHT            = BigDecimal.valueOf(0.9);
    private final static int        PROMOCAO_MUITO_QUANTIDADE = 3; // Quantidade de um ingrediente para a promoção

    private final PedidoDao pedidoDao;
    private final PedidoLancheDao pedidoLancheDao;

    public PedidoService(PedidoDao pedidoDao, PedidoLancheDao pedidoLancheDao) {
        this.pedidoDao = pedidoDao;
        this.pedidoLancheDao = pedidoLancheDao;
    }

    /**
     * Realiza o pedido. (Muta o pedido)
     *
     * @param pedido Pedido
     * @return Pedido com preços
     */
    @Transactional
    public Pedido realiza(Pedido pedido) {
        pedido.setValor(calculatePrecoPedido(pedido));
        pedidoDao.save(pedido);
        pedidoLancheDao.saveAll(pedido.getPedidoLanches());
        return pedido;
    }

    /**
     * Calcula o preco de um pedido. (Muta o pedido)
     *
     * @param pedido Pedido
     * @return Preço total calculado
     */
    public BigDecimal calculatePrecoPedido(Pedido pedido) {
        final BigDecimal preco = pedido.getPedidoLanches().stream()
                .map(this::caclulatePrecoLanche).reduce(BigDecimal.ZERO, BigDecimal::add);
        pedido.setValor(preco);
        return preco;
    }

    /**
     * Calcula o preco de um lanche. (Muta o pedido de lanche)
     *
     * @param pedidoLanche Pedido de lanche
     * @return Preço total calculado
     */
    public BigDecimal caclulatePrecoLanche(PedidoLanche pedidoLanche) {
        final List<IngredienteAgregado> pedidoIngredientes = IngredienteAgregado.from(pedidoLanche.getIngredientes());

        final BigDecimal preco = pedidoIngredientes.stream()
                .map(this::calculatePromocaoMuito)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedidoLanche.setValor(preco);
        return calculateDescontoLight(pedidoIngredientes, preco);
    }

    private BigDecimal calculateIngredientePorQuantidade(IngredienteAgregado ingrediente) {
        return ingrediente.getValor().multiply(BigDecimal.valueOf(ingrediente.getQuantidade()));
    }

    private BigDecimal calculateDescontoLight(List<IngredienteAgregado> pedidoIngredientes, BigDecimal preco) {
        if (pedidoIngredientes.stream().anyMatch(pedidoLancheIngrediente ->
                pedidoLancheIngrediente.getId().equals(Ingrediente.ID_ALFACE)) &&
                pedidoIngredientes.stream().noneMatch(pedidoLancheIngrediente ->
                pedidoLancheIngrediente.getId().equals(Ingrediente.ID_BACON))) {
            return preco.multiply(DESCONTO_LIGHT).setScale(2, RoundingMode.HALF_EVEN);
        }
        return preco;
    }

    private BigDecimal calculatePromocaoMuito(IngredienteAgregado ingrediente) {
        final BigDecimal  preco = calculateIngredientePorQuantidade(ingrediente);

        if (!ingrediente.getId().equals(Ingrediente.ID_HAMBURGER_DE_CARNE) &&
            !ingrediente.getId().equals(Ingrediente.ID_QUEIJO)) {
            return preco;
        }

        if (ingrediente.getQuantidade() < PROMOCAO_MUITO_QUANTIDADE) {
            return preco;
        }

        return preco.subtract(ingrediente.getValor()
                .multiply(BigDecimal.valueOf(ingrediente.getQuantidade() / PROMOCAO_MUITO_QUANTIDADE)));
    }
}
