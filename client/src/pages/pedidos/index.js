import PedidosTable from "../../components/PedidosTable";
import {Card} from "antd";

export default () => (
  <div>
    <h1>Pedidos realizados</h1>
    <Card bodyStyle={{ padding: 0 }}>
      <PedidosTable/>
    </Card>
  </div>
)

