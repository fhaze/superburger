package jp.fhaze.superburger.controllers;

import jp.fhaze.superburger.data.dao.IngredienteDao;
import jp.fhaze.superburger.data.dao.LancheDao;
import jp.fhaze.superburger.data.dao.PedidoDao;
import jp.fhaze.superburger.data.entities.Pedido;
import jp.fhaze.superburger.models.ReqPedidoLanche;
import jp.fhaze.superburger.services.PedidoService;
import jp.fhaze.superburger.utils.LancheBuilder;
import jp.fhaze.superburger.utils.PedidoBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final LancheDao lancheDao;
    private final IngredienteDao ingredienteDao;
    private final PedidoDao pedidoDao;
    private final PedidoService pedidoService;

    public PedidoController(LancheDao lancheDao, IngredienteDao ingredienteDao, PedidoDao pedidoDao, PedidoService pedidoService) {
        this.lancheDao = lancheDao;
        this.ingredienteDao = ingredienteDao;
        this.pedidoDao = pedidoDao;
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<?> listaPedidos() {
        return ResponseEntity.ok(pedidoDao.findAll());
    }

    @GetMapping("/cardapio")
    public ResponseEntity<?> cardapio() {
        return ResponseEntity.ok(lancheDao.findAll());
    }

    @GetMapping("/ingredientes")
    public ResponseEntity<?> ingredientes() {
        return ResponseEntity.ok(ingredienteDao.findAllByOrderByNome());
    }

    @PostMapping("/simulacao/lanche")
    @Transactional
    public ResponseEntity<?> simulacao(@RequestBody ReqPedidoLanche reqPedidoLanche) {
        final Pedido pedido = createPedido(reqPedidoLanche);

        pedidoService.calculatePrecoPedido(pedido);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/simulacao")
    @Transactional
    public ResponseEntity<?> simulacao(@RequestBody List<ReqPedidoLanche> reqPedidoLanches) {
        final Pedido pedido = createPedido(reqPedidoLanches);

        pedidoService.calculatePrecoPedido(pedido);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> realizaPedido(@RequestBody List<ReqPedidoLanche> reqPedidoLanches) {
        pedidoService.realiza(createPedido(reqPedidoLanches));
        return ResponseEntity.ok().build();
    }

    private Pedido createPedido(List<ReqPedidoLanche> reqPedidoLanches) {
        final PedidoBuilder pedidoBuilder = PedidoBuilder.builder();

        reqPedidoLanches.forEach(lanche -> {
            final LancheBuilder lancheBuilder = pedidoBuilder.addLanche(lanche.getNome());
            lanche.getIngredientes().forEach(ingrediente -> lancheBuilder
                    .addIngrediente(ingredienteDao.getOne(ingrediente.getId()), ingrediente.getQuantidade()));
        });

        return pedidoBuilder.build();
    }

    private Pedido createPedido(ReqPedidoLanche reqPedidoLanche) {
        final PedidoBuilder pedidoBuilder = PedidoBuilder.builder();
        final LancheBuilder lancheBuilder = pedidoBuilder.addLanche(reqPedidoLanche.getNome());

        reqPedidoLanche.getIngredientes().forEach(ingrediente -> lancheBuilder
                .addIngrediente(ingredienteDao.getOne(ingrediente.getId()), ingrediente.getQuantidade()));

        return pedidoBuilder.build();
    }
}