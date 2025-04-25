Apply spring java format
```shell
./mvnw spring-javaformat:apply 
```
To fix javadoc
```shell
./mvnw javadoc:fix
```
Generate Java jar with java doc
```shell
./mvnw clean install javadoc:javadoc -DskipTests
```
Redis connect and monitor - run inside docker container
```shell
redis-cli -h redis -p 6379 -a passwor
# monitor command
monitor 
```

Caching usage
```text
@CachePut(value = "bookClubs", key = "#result.clubId")
@Cacheable(value = "bookClubs", key = "#id")
@CacheEvict(value = "bookClubs", key = "#id")

private final CacheManager cacheManager;
var cache = cacheManager.getCache("allBookClubs");
cache.get(key, PageDto.class)
```

Sql query
```postgres-sql
-- drop all table
DO $$ DECLARE
    r RECORD;
BEGIN
    FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public') LOOP
        EXECUTE 'DROP TABLE IF EXISTS public.' || quote_ident(r.tablename) || ' CASCADE';
    END LOOP;
END $$;

-- clean all table data
DO $$ DECLARE
    r RECORD;
BEGIN
    FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public') LOOP
        EXECUTE 'TRUNCATE TABLE public.' || quote_ident(r.tablename) || ' CASCADE';
    END LOOP;
END $$;

-- view current tables
select * from "public".book;
select * from "public".book_buy_link;
select * from "public".book_club;
select * from "public".book_tag;
select * from "public".book_genre;
select * from "public".review;

```