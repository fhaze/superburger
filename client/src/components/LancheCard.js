import {Button, Card} from "antd";
import dummy from "../assets/dummy-burger.jpg"
import Row from "antd/es/grid/row";
import Col from "antd/es/grid/col";
import formatText from "../utils/formatText";

const { Meta } = Card;

const listaIngredientes = ingredientes => (
  <ul style={{ marginTop: 10 }}>
    {ingredientes.map(ingrediente => <li key={ingrediente.id}>{ingrediente.nome}</li>)}
  </ul>
)

export default ({ lanche, onAdicionar, onPersonalizar }) => {
  const { nome, valor, ingredientes } = lanche

  return (
    <Card style={{ marginBottom:  10 }} bodyStyle={{ height: 200 }} actions={[
      <Button ghost type="primary" onClick={() => onAdicionar(lanche)}>Adicionar</Button>,
      <Button onClick={() => onPersonalizar(lanche)}>Personalizar</Button>
    ]}>
      <Row>
        <Col span={6}>
          <img alt="burger" style={{ maxWidth: "100%", maxHeight: "100px" }} src={dummy}/>
        </Col>
        <Col span={18}>
          <div style={{ paddingLeft: 10 }}>
            <h3>{nome}</h3>
            <Meta description={formatText.currency(valor)}/>
            { listaIngredientes(ingredientes) }
          </div>
        </Col>
      </Row>
    </Card>
  )
}
