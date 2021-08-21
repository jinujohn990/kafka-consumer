package com.jinu.kafka.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jinu.kafka.dto.User;

@RestController
public class ConsumerController {

	List<String> names = new ArrayList<>();

	List<User> users = new ArrayList<>();

	@KafkaListener(groupId = "group-1", topics = "testTopic2", containerFactory = "kafkaListenerContainerFactory")
	public void listenName(String name) {
		names.add(name);
	}

	@KafkaListener(groupId = "group-2", topics = "testTopic2", containerFactory = "kafkaUserListenerContainerFactory")
	public void listenName(User user) {
		users.add(user);
	}

	@GetMapping("/consumename")
	public List<String> consumeName() {
		return names;
	}

	@GetMapping("/consumeUsers")
	public List<User> consumeUsers() {
		return users;
	}
}
