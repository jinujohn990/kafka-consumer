package com.jinu.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.jinu.kafka.dto.User;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.18.3:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");
		return new DefaultKafkaConsumerFactory<>(configs);
	}

	@Bean("kafkaListenerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
		return kafkaListenerContainerFactory;
	}

	@Bean
	public ConsumerFactory<String, User> consumerUserFactory() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configs.put(JsonDeserializer.TRUSTED_PACKAGES, "com.jinu.kafka.dto");
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");
		return new DefaultKafkaConsumerFactory<>(configs);
	}

	@Bean("kafkaUserListenerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, User> kafkaUserListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, User> kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<String, User>();
		kafkaListenerContainerFactory.setConsumerFactory(consumerUserFactory());
		return kafkaListenerContainerFactory;
	}
}
