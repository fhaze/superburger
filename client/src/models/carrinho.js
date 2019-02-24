export default {
  namespace: 'carrinho',
  state: [],
  reducers: {
    add(state, {payload}) {
      const lanche = { ...payload.lanche}
      lanche.valor = payload.total;
      lanche.ingredientes.forEach(ingrediente => {
        if (ingrediente.quantidade === undefined) {
          ingrediente.quantidade = 1;
        }
      })
      return [...state, lanche]
    },
    remove(state, {payload: lanche}) {
      return state.filter(lancheCarrinho => lancheCarrinho !== lanche)
    },
    clear() {
      return [];
    }
  }
}
