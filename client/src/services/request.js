import Axios from "axios";

const instance = Axios.create({
  baseURL: '/api'
});

export default instance;
