export default {
  currency: valor => valor?.toLocaleString('pt-br', {style: 'currency', currency: 'BRL'})
}
