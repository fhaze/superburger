import request from "./request";

export default {
  pedido: {
    lanches: () =>
      request.get("/pedido/cardapio")
        .then(res => res.data),

    ingredientes: () =>
      request.get("/pedido/ingredientes")
        .then(res => res.data),

    realizados: () =>
      request.get("/pedido")
        .then(res => res.data),

    simulacao: pedido =>
      request.post("/pedido/simulacao", pedido)
        .then(res => res.data),

    simulacaoLanche: lanche =>
      request.post("/pedido/simulacao/lanche", lanche)
        .then(res => res.data),

    realiza: pedidos =>
      request.post("/pedido", pedidos)
        .then(res => res.data)
  }
}
