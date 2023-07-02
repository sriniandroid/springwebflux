package com.wf.demo.controller;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wf.demo.dto.Event;
import com.wf.demo.exception.InvalidRequestException;
import com.wf.demo.rest.ServiceResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class TestController {

	 
	@RequestMapping(value = "/getEvent", method = RequestMethod.GET)
	public Mono<ServiceResponse> getEvent(@RequestParam(name = "id", required = false) long id) throws Exception {
		if(id == 0) {
			return Mono.error(new InvalidRequestException("ID is missing in request!"));
		}
		Event e = new Event();
		e.setId(id);
		e.setDate("12-12-2021");
		
		return Mono.just(ServiceResponse.builder()
				.responseTime(new Date().toString())
				.statusCode(HttpStatus.OK.value() + "")
				.response(e)
				.build()); 
	}
	
	@RequestMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/getAllEvents", method = RequestMethod.GET)
	public Flux<Event> getAllEvents(){
		Flux<Event> flux = Flux.fromStream(Stream.generate(() -> new Event(System.currentTimeMillis(), "Testing..")));
		Flux<Long> timer = Flux.interval(Duration.ofSeconds(1));
		return Flux.zip(flux, timer).map(Tuple2::getT1);
	}

	
	
}
