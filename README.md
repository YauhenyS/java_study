docker run --name blog-postgres -e POSTGRES_DB=blogdb -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5433:5432 -v blog_pgdata:/var/lib/postgresql/data -d postgres:15
CREATE DATABASE blogdb;
gradle bootRun