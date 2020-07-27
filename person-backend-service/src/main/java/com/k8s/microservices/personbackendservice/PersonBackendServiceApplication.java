package com.k8s.microservices.personbackendservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class PersonBackendServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonBackendServiceApplication.class, args);
	}

}
@Log4j2
@RestController
//@RequestMapping("/v1")
class PersonController {

	@GetMapping("/{name}")
	public Mono<String> greet(@PathVariable String name){
		log.info("with param {}",name);
		return Mono.just("Hello"+name);
	}

	@GetMapping("/")
	public Mono<String> greet(){
		log.info("{}","plain call");
		return Mono.just("Hello world..1");
	}

}