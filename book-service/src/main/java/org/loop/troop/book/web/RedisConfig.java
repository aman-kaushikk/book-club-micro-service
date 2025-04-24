package org.loop.troop.book.web;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.RedisCodec;
import lombok.val;

/**
 * The type Redis config.
 */
@Configuration
@EnableCaching
public class RedisConfig {

	/**
	 * Cache manager redis cache manager.
	 * @param redisConnectionFactory the redis connection factory
	 * @param mapper the mapper
	 * @return the redis cache manager
	 */
	@Bean
	RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory, ObjectMapper mapper) {
		val objectMapper = mapper.copy()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.registerModule(new Hibernate6Module().enable(Hibernate6Module.Feature.FORCE_LAZY_LOADING)
				.enable(Hibernate6Module.Feature.REPLACE_PERSISTENT_COLLECTIONS))
			.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL,
					JsonTypeInfo.As.PROPERTY);
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
			.entryTtl(Duration.ofMinutes(10)) // Cache expiry time
			.serializeValuesWith(RedisSerializationContext.SerializationPair
				.fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper)));

		return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
	}

	/**
	 * Redis client redis client.
	 * @param lettuceConnectionFactory the lettuce connection factory
	 * @return the redis client
	 */
	@Bean
	RedisClient redisClient(LettuceConnectionFactory lettuceConnectionFactory) {
		RedisURI redisURI = RedisURI.create(lettuceConnectionFactory.getHostName(), lettuceConnectionFactory.getPort());
		return RedisClient.create(redisURI);
	}

	/**
	 * Stateful redis connection stateful redis connection.
	 * @param redisClient the redis client
	 * @return the stateful redis connection
	 */
	@Bean
	StatefulRedisConnection<String, byte[]> statefulRedisConnection(RedisClient redisClient) {
		RedisCodec<String, byte[]> codec = new RedisCodec<>() {
			@Override
			public String decodeKey(ByteBuffer buffer) {
				return StandardCharsets.UTF_8.decode(buffer).toString();
			}

			@Override
			public byte[] decodeValue(ByteBuffer buffer) {
				byte[] bytes = new byte[buffer.remaining()];
				buffer.get(bytes);
				return bytes;
			}

			@Override
			public ByteBuffer encodeKey(String key) {
				return StandardCharsets.UTF_8.encode(key);
			}

			@Override
			public ByteBuffer encodeValue(byte[] value) {
				return ByteBuffer.wrap(value);
			}
		};

		return redisClient.connect(codec);
	}

}
