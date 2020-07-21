FROM node:12-alpine as builder

WORKDIR /frontend
COPY ./package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod

FROM node:12-alpine
WORKDIR /shared
COPY --from=builder /frontend/dist/leilao .