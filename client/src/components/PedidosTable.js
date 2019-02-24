import {Table, Tag} from "antd"
import {connect} from "dva"
import formatText from "../utils/formatText"

const mapStateToProps = ({ pedidos, loading }) => ({
  pedidos,
  loading: loading.effects["pedidos/fetchPedidos"]
})


export default connect(mapStateToProps)(({ pedidos, loading }) => {

  const columns = [
    {
      dataIndex: "id",
      defaultSortOrder: 'descend',
      title: "Pedido",
      sorter: (a, b) => a.id - b.id
    },
    {
      dataindex: "pedidoLanches",
      key: "pedidoLanches",
      title: "Lanches",
      render: pedido => <ul>
        {pedido.pedidoLanches.map(lanche =>
          <li style={{ paddingBottom: 10 }}>
            <div>{lanche.nome}</div> {lanche.ingredientesAgredados
            .map(ingrediente => <Tag key={ingrediente.id}>{ingrediente.quantidade}x {ingrediente.nome}</Tag>)}
          </li>
        )}
      </ul>
    },
    {
      dataIndex: "valor",
      title: "Valor",
      render: formatText.currency,
      sorter: (a, b) => a.valor - b.valor
    }
  ]

  return (
    <Table
      pagination={{ pageSize: 4 }}
      rowKey="id"
      locale={{ emptyText: "Vazio" }}
      loading={loading}
      size="middle"
      bordered
      columns={columns}
      dataSource={pedidos}
    />
  )
})

