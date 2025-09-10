docker run --name blog-postgres -e POSTGRES_DB=blogdb -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5433:5432 -v blog_pgdata:/var/lib/postgresql/data -d postgres:15
CREATE DATABASE blogdb;
gradle bootRun

-- 1. Топ тегов
SELECT * FROM tag_post_count;

-- 2. Активные пользователи за 10 дней
SELECT * FROM active_users_last_10_days;

-- 3. Проверка план запроса (вручную)
EXPLAIN ANALYZE
SELECT u.username, COUNT(p.id)
FROM users u
JOIN posts p ON u.id = p.user_id
WHERE p.created_at >= CURRENT_DATE - INTERVAL '10 days'
GROUP BY u.id, u.username
ORDER BY COUNT(p.id) DESC
LIMIT 5;