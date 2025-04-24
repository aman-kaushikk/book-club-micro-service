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
