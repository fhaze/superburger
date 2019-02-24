import {connect} from "dva";
import {Button, Icon, List, message} from "antd";
import formatText from "../utils/formatText";

const mapStateroProps  = ({ carrinho }) => ({
  lanches: carrinho
})

export default connect(mapStateroProps)(({ lanches, dispatch }) => {
  return <List
    locale={{ emptyText: "Vazio" }}
    style={{ backgroundColor: 'white' }}
    header={<div><Icon type="shopping-cart" /> <strong>Carrinho</strong></div>}
    footer={
      lanches.length === 0 ? null :
      <div style={{textAlign: 'right' }}>
        <span style={{ float: 'left' }}>
          Total: {formatText.currency(lanches.map(lanche => lanche.valor).reduce((acc, cur) => acc + cur))}
        </span>
        <Button
          icon="shopping-cart"
          type="primary"
          onClick={() => {
            dispatch({ type: "pedido/setFinalizar", payload: true })
            dispatch({ type: "pedido/simulacao" })
          }}>
          Finalizar Pedido
        </Button>
      </div>

    }
    bordered
    dataSource={lanches}
    renderItem={lanche =>
      <List.Item>
        <List.Item.Meta
          title={lanche.nome}
          description={formatText.currency(lanche.valor)}
          />
        <Button
          ghost
          type="danger"
          onClick={() => {
            message.warn(<span><strong>{lanche.nome}</strong> removido do carrinho.</span>)
            dispatch({ type: 'carrinho/remove', payload: lanche })
          }}>
          Remover
        </Button>
      </List.Item>}
  />
})
