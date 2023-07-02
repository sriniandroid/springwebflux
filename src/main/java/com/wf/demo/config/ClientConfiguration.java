package com.wf.demo.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.wf.demo.dto.Event;

import reactor.core.publisher.Mono;

@Configuration
public class ClientConfiguration {
	
	private WebClient webClient;
	
	@Bean
	public WebClient configure(WebClient.Builder webClientBuilder) {
		webClientBuilder
		.baseUrl("http://localhost:8080")
		.defaultHeader("Content-type", "application/json");
		return webClientBuilder.build();
	}
	
	public Mono<Event> getApiCall(String apiUrl) {
		return this.webClient.get()
					.uri(apiUrl)
					.retrieve()
					.bodyToMono(Event.class);
	}
	 
}
