import api from "../../services/api";

export default {
  namespace: 'pedidos',
  state: [],
  reducers: {
    setPedidos(_, {payload: pedidos}) {
      return pedidos
    }
  },
  effects: {
    *fetchPedidos({payload}, { put, call }) {
      const pedidos = yield call(api.pedido.realizados)
      yield put({ type: "setPedidos", payload: pedidos })
    }
  },
  subscriptions: {
    setup({dispatch, history}) {
      history.listen(({ pathname }) => {
        if (pathname === "/pedidos") {
          dispatch({ type: "fetchPedidos" })
        }
      })
    }
  }
}
