docker-compose.yaml

version: '3.8'

services:
training-db:
image: "postgres:15.3-bullseye"
networks:
- appNetwork
environment:
POSTGRES_USER: marmontDB
POSTGRES_PASSWORD: marmontDB
POSTGRES_DB: marmontDB
command:
- "-c"
- "shared_preload_libraries=pg_stat_statements"
- "-c"
- "pg_stat_statements.track=all"
- "-c"
- "max_connections=200"
ports:
- "5432:5432"
healthcheck:
test: ["CMD-SHELL", "pg_isready -U marmontDB"]
interval: 10s
timeout: 5s
retries: 5
volumes:
- ./data/marmontDB:/var/lib/postgresql/data:z

networks:
appNetwork:
driver: bridge
