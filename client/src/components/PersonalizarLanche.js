import {message, Modal} from "antd";
import {connect} from "dva";
import formatText from "../utils/formatText";
import IngredientesEdit from "./IngredientesEdit";

const mapStateToProps = ({ pedido, loading }) => ({
  ingredientes: pedido.ingredientes,
  total: pedido.preco,
  loadingPreco: loading.effects["pedido/simulacaoLanche"]
});

export default connect(mapStateToProps)(({lanche, ingredientes, total, loadingPreco, dispatch}) => (
 <Modal
   title="Personalize do seu jeito!"
   visible={!!lanche}
   okButtonProps={{
     disabled: lanche === null
       ? true
       : lanche.ingredientes.map(ingrediente => ingrediente.quantidade)
       .reduce((acc, cur) => acc + cur, 0) === 0 || loadingPreco
   }}
   okText="Adicionar"
   cancelText="Voltar"
   onCancel={() => dispatch({ type: "pedido/removePersonalizar" })}
   onOk={() => {
     message.success(<span><strong>{lanche.nome}</strong> adicionado no carrinho.</span>)
    dispatch({ type: "carrinho/add", payload: {lanche, total} })
    dispatch({ type: "pedido/removePersonalizar" })
   }}>
   <IngredientesEdit
     onChange={(quantidade, id) => {
       if (loadingPreco) return
       dispatch({ type: 'pedido/setPersonalizarIngrediente', payload: { id, quantidade } })
       dispatch({ type: 'pedido/simulacaoLanche' })
     }}
     lancheIngredientes={lanche ? lanche.ingredientes : []}
   />
   <div style={{ textAlign: 'right', marginTop: 10 }}>
     <span>{formatText.currency(total === null ? lanche?.valor : total)}</span>
   </div>
 </Modal>
));
