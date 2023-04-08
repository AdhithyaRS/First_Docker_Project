FROM alpine
RUN apk add --update nodejs npm
RUN npm install -g http-server
COPY . /src
WORKDIR /src
EXPOSE 8082
ENTRYPOINT ["http-server","-p","8082"]