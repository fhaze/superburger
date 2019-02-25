import {connect} from "dva";
import LancheCard from "../../components/LancheCard";
import {Col, Row, message} from "antd";
import Carrinho from "../../components/Carrinho";
import PersonalizarLanche from "../../components/PersonalizarLanche";
import FinalizarPedido from "../../components/FinalizarPedido";

const mapStateToProps = ({ pedido }) => ({
  lanches: pedido.lanches,
  personalizar: pedido.personalizar,
  finalizar: pedido.finalizar,
  simulacao: pedido.simulacao
})


export default connect(mapStateToProps)(({ lanches, personalizar, finalizar, simulacao, dispatch }) => {

  const addLanche = lanche => {
      message.success(<span><strong>{lanche.nome}</strong> adicionado no carrinho.</span>)
      dispatch({ type: "carrinho/add", payload: {lanche,  total: lanche.valor} })
  }

  const setPersonalizar = lanche => {
    dispatch({ type: "pedido/setPersonalizar", payload: lanche })
    dispatch({ type: 'pedido/simulacaoLanche' })
  }

  return (
    <div>
      <h1>Escolha seu lanche</h1>
      <Row gutter={16}>
        <Col lg={24} xl={16} xxl={18}>
          <Row gutter={16}>
            {lanches.map(lanche =>
              <Col key={lanche.id} sm={24} xl={12} xxl={8}>
                <LancheCard lanche={lanche} onAdicionar={addLanche} onPersonalizar={setPersonalizar}/>
              </Col>
            )}
          </Row>
        </Col>
        <Col lg={24} xl={8} xxl={6}>
          <Carrinho/>
        </Col>
      </Row>
      <PersonalizarLanche lanche={personalizar}/>
      <FinalizarPedido finalizar={finalizar} pedido={simulacao}/>
    </div>
  )
})

