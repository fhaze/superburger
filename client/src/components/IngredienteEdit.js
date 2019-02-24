import {Divider, InputNumber} from "antd";

export default ({id, nome, valor, quantidade, onChange}) => (
  <div style={{ textAlign: "right"}}>
    <div style={{ float: "left", marginTop: 4 }}>{nome}</div>
    <InputNumber min={0} max={9} onChange={e => onChange(e, id)} value={quantidade}/>
    <Divider style={{ marginTop: 8, marginBottom: 8 }} />
  </div>
);
