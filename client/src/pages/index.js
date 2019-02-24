import styles from './index.css';

export default function() {
  return (
    <div className={styles.normal}>
      <div className={styles.welcome} />
      <ul className={styles.list}>
        <h2>Projeto Superburger</h2>
        <li>Java com <a href="https://spring.io/">Spring</a></li>
        <li>React com <a href="https://umijs.org/guide/getting-started.html">UmiJS</a> e <a href="https://ant.design/">Ant Design</a></li>
      </ul>
    </div>
  );
}
