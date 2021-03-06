import styles from './index.css';


export default () => {
  return (
    <div className={styles.normal}>
      <div className={styles.welcome} />
      <ul className={styles.list}>
        <h2>Projeto Super Burger</h2>
        <li><strong>Backend:</strong> Java com <a href="https://spring.io/">Spring</a></li>
        <li><strong>Frontend:</strong> React com <a href="https://umijs.org/">UmiJS</a> e <a href="https://ant.design/">Ant Design</a></li>
      </ul>
      <ul className={styles.list}>
        <li>Frontend responsivo! Experimente acessar por um celular.
          <span aria-label="coffe" role="img" style={{ color: 'black' }}> &#9749;</span>
        </li>
      </ul>
    </div>
  );
}
