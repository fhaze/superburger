
// ref: https://umijs.org/config/
export default {
  treeShaking: true,
  targets: {
    ie: 11
  },
  plugins: [
    // ref: https://umijs.org/plugin/umi-plugin-react.html
    ['umi-plugin-react', {
      antd: true,
      dva: true,
      dynamicImport: false,
      title: 'client',
      dll: false,
      routes: {
        exclude: [
        
          /models\//,
          /services\//,
          /model\.(t|j)sx?$/,
          /service\.(t|j)sx?$/,
        
          /components\//,
        ],
      },
    }],
  ],
  proxy: {
    "/api": {
      target: "http://localhost:8080/",
      changeOrigin: true,
      pathRewrite: { "^/api" : "" }
    }
  }
}
