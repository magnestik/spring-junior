Перед запуском необходимо запустить PostgreSQL:

```
docker run --name account-postgres -p 5432:5432 -e POSTGRES_USER=account -e POSTGRES_PASSWORD=account -e POSTGRES_DB=account -d postgres:14
```