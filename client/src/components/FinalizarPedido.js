import {notification, List, Modal,  Spin} from "antd";
import formatText from "../utils/formatText";
import {connect} from "dva";

const mapStateToProps = ({ loading }) => ({
  loading: loading.effects["pedido/simulacao"] || loading.effects["pedido/realizar"]
})

export default connect(mapStateToProps)(({finalizar, pedido, loading, dispatch}) => (
  <Modal
    locale={{ emptyText: "Vazio" }}
    title="Confirme seu Pedido!"
    visible={!!finalizar}
    okButtonProps={{ disabled: loading }}
    okText="Confirmar Pedido"
    cancelText="Voltar"
    onOk={() => {
      dispatch({ type: "pedido/realizar" }).then(() => {
        dispatch({ type: "pedido/setFinalizar", payload: false })
        dispatch({ type: "carrinho/clear" })

        notification.success({
          message: "Pedido realizado com sucesso!",
          description: "Seu pedido foi enviado para o Super Burger. Agora é só esperar.",
          duration: 8,
        })
      })
    }}
    onCancel={() => dispatch({ type: "pedido/setFinalizar", payload: false })}
  >
    <List
      loading={loading}
      dataSource={pedido.pedidoLanches}
      footer={
        <div style={{ textAlign: "right" }}>
          {loading
            ? <Spin size="small"/>
            : <span>Total: {formatText.currency(pedido.valor)}</span>}
        </div>
      }
      renderItem={lanche =>
        <List.Item>
          <List.Item.Meta
            title={lanche.nome}
            description={formatText.currency(lanche.valor)}
          />
        </List.Item>
      }
    />
  </Modal>
))
