import {Table, Tag} from "antd";
import {connect} from "dva";
import formatText from "../utils/formatText";

const mapStateToProps = ({ pedidos, loading }) => ({
  pedidos,
  loading: loading.effects["pedidos/fetchPedidos"]
})


export default connect(mapStateToProps)(({ pedidos, loading }) => {

  const columns = [
    {
      dataIndex: "id",
      title: "ID",
      sorter: (a, b) => a.id - b.id
    },
    {
      dataindex: "pedidoLanches",
      key: "pedidoLanches",
      title: "Lanches",
      render: pedido => pedido.pedidoLanches.map(lanche => <Tag color="blue">{lanche.nome}</Tag>)
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
      loading={loading}
      size="middle"
      bordered
      columns={columns}
      dataSource={pedidos}
    />
  )
})

