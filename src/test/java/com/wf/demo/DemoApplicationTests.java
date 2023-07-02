package com.wf.demo;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.wf.demo.dto.Event;
import com.wf.demo.service.ApiService;

import reactor.core.publisher.Mono;

@SpringBootTest
@ContextConfiguration(classes=DemoApplication.class)
@RunWith(SpringRunner.class)
public class DemoApplicationTests {
	 
	private WebTestClient webTestClient;
	
	@Autowired
	private ApiService apiService; 
	
	@Before
	public void beforeTest() {
		this.webTestClient = WebTestClient.bindToServer()
						.baseUrl("http://localhost:8080").build();
	}
	
	@Test
	public void testEvent() throws Exception{
		this.webTestClient.get()
			.uri("/getEvents?id=1")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk();
	}
	 
	@Test
	public void apiTestEvent() throws Exception{
		Mono<Object> event = apiService.getApiCall("getEvent?id=1");
		assertNotNull(event);
	}

}
