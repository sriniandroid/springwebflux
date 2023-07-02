package com.wf.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Service
public class ApiService {

	@Autowired
	private WebClient webClient;
	
	public Mono<Object> getApiCall(String apiUrl) {
		return this.webClient.get()
						.uri(apiUrl)
						.retrieve()
						.bodyToMono(Object.class)
						.onErrorResume(e -> Mono.just("Error " + e.getMessage())
						        .flatMap(s -> ServerResponse.ok()
						          .contentType(MediaType.TEXT_PLAIN)
						          .bodyValue(s)));
	}
	
}
