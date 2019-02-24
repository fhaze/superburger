import api from "../../services/api";

export default {
  namespace: 'pedido',
  state: {
    lanches: [],
    personalizar: null, // lanche sendo personalizado,
    preco: null,
    ingredientes: [],
    finalizar: false,
    simulacao: [],
  },
  reducers: {
    setLanches(state, {payload: lanches}) {
      return {...state, lanches}
    },
    setPersonalizar(state, {payload}) {
      const personalizar = {...payload}
      personalizar.nome = `${personalizar.nome} (personalizado)`
      const ingredientes = state.ingredientes.map(ingrediente => ({...ingrediente, quantidade: 0}))
      personalizar.ingredientes.forEach(ingrediente => {
        ingredientes.find(ing => ing.id === ingrediente.id).quantidade++
      })
      const newPersonalizar = {...personalizar, ingredientes}
      return {...state, personalizar: newPersonalizar}
    },
    removePersonalizar(state, _) {
      return {...state, personalizar: null, preco: null}
    },
    setPreco(state, {payload: preco}) {
      return {...state, preco}
    },
    setIngredientes(state, {payload: ingredientes}) {
      return {...state, ingredientes}
    },
    setPersonalizarIngrediente(state, {payload: {id, quantidade}}) {
      const personalizar = state.personalizar;
      const editedIngrediente = personalizar.ingredientes.find(ing => ing.id === id)
      editedIngrediente.quantidade = !quantidade ? 0 : quantidade; // Usuario invalidou quantidade? zera quantidade
      const newIngredientes = [
        ...state.personalizar.ingredientes
          .filter(ing => ing.id !== id), editedIngrediente].sort((x, y) => x.nome.localeCompare(y.nome))

      return {
        ...state,
        personalizar: {
          ...personalizar,
          ingredientes: newIngredientes
        }
      }
    },
    setFinalizar(state, {payload: finalizar}) {
      return {...state, finalizar}
    },
    setSimulacao(state, {payload: simulacao}) {
      return {...state, simulacao}
    },
  },
  effects: {
    *fetchLanches(_, { put, call }) {
      const lanches = yield call(api.pedido.lanches)
      yield put({ type: "setLanches", payload: lanches })
    },
    *fetchIngredientes(_, { put, call }) {
      const ingredientes = yield call(api.pedido.ingredientes)
      yield put({ type: "setIngredientes", payload: ingredientes })
    },
    *simulacao(_, { put, call, select }) {
      const carrinho = yield select(({ carrinho }) => carrinho)
      const simulacao = yield call(api.pedido.simulacao, carrinho)
      yield put({ type: "setSimulacao", payload: simulacao })
    },
    *simulacaoLanche(_, { put, call, select }) {
      const lanche = yield select(({ pedido }) => pedido.personalizar)
      const simulacaoLanche = yield call(api.pedido.simulacaoLanche, lanche)
      yield put({ type: "setPreco", payload: simulacaoLanche.valor })
    },
    *realizar(_, { put, call, select }) {
      const carrinho = yield select(({ carrinho }) => carrinho)
      yield call(api.pedido.realiza, carrinho)
    },
  },
  subscriptions: {
    setup({dispatch, history}) {
      history.listen(({ pathname }) => {
        if (pathname === "/pedido") {
          dispatch({ type: 'fetchLanches' })
          dispatch({ type: 'fetchIngredientes' })
        }
      })
    }
  }
}
