package com.k8s.microservices.personfrontendservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class PersonFrontendServiceApplication {

	@Value("${person.service.endpoint}")
	private String endpoint;

	public static void main(String[] args) {
		SpringApplication.run(PersonFrontendServiceApplication.class, args);
	}
	@Bean
	WebClient webClient(WebClient.Builder builder) {
		return builder
				.build();
	}


	@Bean
	RouteLocator customeRouterLocator(RouteLocatorBuilder builder){
		return builder
				.routes()
				.route("edp2",r-> r.path("/greeting/**")
				.filters(rw -> rw.rewritePath("/greeting/(?<name>.*)", "/${name}"))
				.uri(endpoint)
				)
				.build();
	}
}
@RestController
@RequestMapping(value = "/edge")
class ClientEdgeService {

	@Value("${person.service.endpoint}")
	private String endpoint;

	@Autowired
	private WebClient webClient;

	@GetMapping("/{name}")
	public Mono<String> greet(@PathVariable String name){
		return webClient
				.get()
				.uri(endpoint+name)
				.retrieve()
				.bodyToMono(String.class)
				;
	}
}