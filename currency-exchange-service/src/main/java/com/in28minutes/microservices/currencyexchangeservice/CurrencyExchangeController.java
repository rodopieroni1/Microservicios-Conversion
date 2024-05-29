package com.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	//te log se puede ver la siguiente informacion
	//currency-exchange,acc65545fe5e8e685f745195c8c4a329,79879adba671cba0][0;39m [35m15244[0;39m [2m---[0;39m [2m[currency-exchange] [nio-8000-exec-4][0;39m [2m[acc65545fe5e8e685f745195c8c4a329-79879adba671cba0] [0;39m[36mc.i.m.c.CurrencyExchangeController      [0;39m [2m:[0;39m retrieveExchangeValue called whit USD to INR
//acc65545fe5e8e685f745195c8c4a329,79879adba671cba0   esta id es lo que se necesita para hacer el rastreo de una solicitud
	//cuando vaya desde currency conversion a currency exchange se asigna este id
	// es lo que <groupId>io.micrometer</groupId> genera para el rastreo
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to) {
		logger.info("retrieveExchangeValue called whit {} to {}", from, to);
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		if(currencyExchange == null) {
			throw new RuntimeException("inhabilitada para encontrar datos de Form: "+ from +" to: "+to);
		}
		String port = environment.getProperty("local.server.port");	
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
	
	

}
