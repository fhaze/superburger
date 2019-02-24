import {notification} from "antd";

export const dva = {
  config: {
    onError(err) {
      err.preventDefault()

      if (err.response?.status === 504) {
        notification.error({
          message: "Erro de comunicação",
          description: "Nao foi possivel atender sua solicitação.",
          duration: 10
        })
      }
      console.error(err.message)
    },
  },
};
