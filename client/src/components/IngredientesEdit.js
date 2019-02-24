import IngredienteEdit from "./IngredienteEdit";

export default ({lancheIngredientes, onChange}) => (
  lancheIngredientes.map(ingrediente => {
    return <IngredienteEdit
      onChange={onChange}
      id={ingrediente.id}
      nome={ingrediente.nome}
      valor={ingrediente.valor}
      quantidade={ingrediente.quantidade}/>
  }
));
