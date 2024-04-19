DOCKER_COMPOSE_PROJECT_NAME=simple-auth-server

# db 기동
.PHONY:up
up:
	docker compose -p $(DOCKER_COMPOSE_PROJECT_NAME) up -d --build

# db 정지
.PHONY:down
down:
	docker compose -p $(DOCKER_COMPOSE_PROJECT_NAME) down

# db 기본 출력 로그 확인
.PHONY:log
log:
	docker compose -p $(DOCKER_COMPOSE_PROJECT_NAME) logs -f

# db 정지 및 초기화
.PHONY:clean
clean:
	docker compose -p $(DOCKER_COMPOSE_PROJECT_NAME) down -v
