package com.salpreh.qdo.configurations;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfig {
	
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JodaModule())
			.registerModule(new JavaTimeModule())
			.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false)
			.setDateFormat(new SimpleDateFormat("yyyy-MM-dd 'T'HH:mm:ssz"));

		return objectMapper;
	}
	

}
