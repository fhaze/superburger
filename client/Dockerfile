FROM node:8 AS builder

RUN mkdir -p /client/src

COPY package.json /client
COPY webpack.config.js /client
COPY .umirc.js /client
COPY /src/. /client/src

WORKDIR /client

RUN npm install
RUN npm run build

FROM nginx:1.15
RUN mkdir -p /www/app
COPY --from=builder /client/dist/. /www/app
