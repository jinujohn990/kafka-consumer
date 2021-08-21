package com.jinu.kafka.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
	private static final long serialVersionUID = -1498499799962456700L;
	private String username;
	private String email;
}
