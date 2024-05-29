package com.in28minutes.microservicios.currencyconversionservice;

import org.slf4j.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController 
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name="sample-api", fallbackMethod = "harcodedResponse")
	//@CircuitBreaker(name="default", fallbackMethod = "harcodedResponse")
	//@RateLimiter(name="Default")
	//10s=>1000 calls to the sample API
	@Bulkhead(name="sample-api")
	public String sampleApi() {
		logger.info("Sample API call recived");
		/*ResponseEntity<String> forEntity = new RestTemplate()
				.getForEntity("http://localhost:8100/cualquiera", String.class);
		return forEntity.getBody();*/
		return "Sample api";
	}
	
	public String harcodedResponse(Exception ex) {
		return "fallback-response";
	}
}
