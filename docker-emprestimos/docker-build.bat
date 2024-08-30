cd "%~dp0"

REM Criando containers configurando utilizando docker-compose.yml
docker compose -f docker-compose.yml up --detach

pause
