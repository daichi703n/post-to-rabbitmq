# POST to RabbitMQ
Publish posted message to RabbitMQ.

## Get started
### Run RabbitMQ
```
docker-compose up -d
```

### Run app
```
./mvnw spring-boot:run
```

### Publish messages
POST to `/publish` with `id`, `message`. (`id`, `message` are required)

```
curl -XPOST http://localhost:8080/publish -H'Content-Type: application/json' -d'{"id":"1","message":"Hello"}'
```

Loop example: 

```
for i in `seq 1 100`; do echo; echo $i | tr '\n' '\t'; curl -XPOST http://localhost:8080/publish -H'Content-Type: application/json' -d'{"id":"'$i'","message":"'`date -Is`'"}' & sleep 1; done
```

## Settings
`app.is-confirm-enabled`: (boolean) Enable Publisher Confirm. The default value is `false`.

`spring.rabbitmq.host`, `spring.rabbitmq.port` / `spring.rabbitmq.addresses`: Toggle where to connect.
