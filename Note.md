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