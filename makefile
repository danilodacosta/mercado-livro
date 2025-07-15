# Nome da imagem Docker
IMAGE_NAME_DEPLOY=danilodacosta/mercado-livro-api
IMAGE_NAME=mercado-livro-api
CONTAINER_NAME=mercado-livro-api
NETWORK=mercado-livro_app-network
PORT=8080
MYSQL_HOST=mysql-container
TAG=latest
FULL_IMAGE=$(IMAGE_NAME_DEPLOY):$(TAG)
#VERSION=$(shell gradle -q printVersion)

deploy:
	docker build -t $(FULL_IMAGE) .
	docker push $(FULL_IMAGE)

clean:
	-docker rmi $(IMAGE_NAME):latest || true
#:$(VERSION)
build: clean
	docker build -t $(IMAGE_NAME):$(TAG) .

run:
	docker run --rm -it \
		--name $(CONTAINER_NAME) \
		--network $(NETWORK) \
		-e MYSQL_HOST=$(MYSQL_HOST) \
		-p $(PORT):8080 \
		-d $(IMAGE_NAME):$(TAG)

rebuild: stop build run

stop:
	-@docker stop $(CONTAINER_NAME)
	-@docker rm $(CONTAINER_NAME)

logs:
	docker logs -f $(CONTAINER_NAME)

push:
	docker push $(IMAGE_NAME):$(TAG)

#tag:
#	docker tag $(FULL_IMAGE) $(IMAGE_NAME):$(TAG)

compose-up:
	docker-compose up -d --build

compose-down:
	docker-compose down