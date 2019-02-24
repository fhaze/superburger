import {Layout, Menu} from 'antd'
import {Link} from "dva/router"
import styles from "./index.css"

const { Header, Content, Footer } = Layout;

function BasicLayout({ location, children }) {
  return (
    <Layout className="layout" style={{ minHeight: '100vh' }}>
      <Header className={styles.menuPadding}>
        <div className="logo" />
        <Menu
          theme="dark"
          mode="horizontal"
          selectedKeys={[location.pathname]}
          style={{ lineHeight: '64px' }}>
          <Menu.Item key="/"><Link to="/">Home</Link></Menu.Item>
          <Menu.Item key="/pedido"><Link to="/pedido">Novo Pedido</Link></Menu.Item>
          <Menu.Item key="/pedidos"><Link to="/pedidos">Realizados</Link></Menu.Item>
        </Menu>
      </Header>
      <Content className={styles.padding}>
        {children}
      </Content>
      <Footer style={{ textAlign: 'center' }}>
        Super Burger 2019
      </Footer>
    </Layout>
  );
}

export default BasicLayout;
