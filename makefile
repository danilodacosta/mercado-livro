# Nome da imagem Docker
IMAGE_NAME=mercado-livro-api
CONTAINER_NAME=mercado-livro-api
NETWORK=mercado-livro_app-network
PORT=8080
MYSQL_HOST=mysql-container
#VERSION=$(shell gradle -q printVersion)

clean:
	-docker rmi $(IMAGE_NAME):latest || true
#:$(VERSION)
build: clean
	docker build -t $(IMAGE_NAME):latest .

run:
	docker run --rm -it \
		--name $(CONTAINER_NAME) \
		--network $(NETWORK) \
		-e MYSQL_HOST=$(MYSQL_HOST) \
		-p $(PORT):8080 \
		-d $(IMAGE_NAME)

rebuild: stop build run

stop:
	-@docker stop $(CONTAINER_NAME)
	-@docker rm $(CONTAINER_NAME)

logs:
	docker logs -f $(CONTAINER_NAME)

compose-up:
	docker-compose up -d --build

compose-down:
	docker-compose down