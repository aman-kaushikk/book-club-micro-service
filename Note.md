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

Rabbit mq binding
```java

    /**
     * Binding book queue to exchange(topic) with routing type book.updated 
     * It matches book.created exactly
     * @return Binding
     */
 	@Bean
	public Binding bookCreatedBinding() {
		return BindingBuilder.bind(bookQueue()).to(bookExchange()).with(rabbitMQ.getCreateRoutingKey());
	}
    /**
     * binding book queue to exchange(topic) with routing type book.updated 
     * It matches book.updated exactly
     * @return Binding
     */
    @Bean
	public Binding bookUpdatedBinding() {
		return BindingBuilder.bind(bookQueue()).to(bookExchange()).with(rabbitMQ.getUpdateRoutingKey());
	}

    /**
     * binding book queue to exchange(topic) with routing type book.*
     * It matches book.created, book.updated any one word matches
     * It doesn't match book, book.created.at means exact one match
     * @return Binding
     */
	@Bean
	public Binding bookBinding() {
		return BindingBuilder.bind(bookQueue()).to(bookExchange()).with(rabbitMQ.getRoutingKey());
	}
    @Bean
    public TopicExchange bookExchange() {
        return new TopicExchange(rabbitMQ.getBookExchange());
    }
    
```