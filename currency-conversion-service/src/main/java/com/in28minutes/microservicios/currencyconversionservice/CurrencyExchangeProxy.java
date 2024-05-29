package com.in28minutes.microservicios.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//usamos el nombre del servicio al que queremos llegar, y la upriumera parte de la uri
//luego abajo copio lo que estaba en el controller de exchange, y cambio el nombre del metodo
//@FeignClient(name="currency-exchange", url="localhost:8000")
//para que se realice el balanceo entre los microserviciosse comienza comenbtando la linea anterior y agregando una nueva solo
//con el nombre
@FeignClient(name="currency-exchange")//lo que se busca con esto es que el cliente Feign hable con Eureka y recoja
//la instancia de currency-exchange y logre el balanceo, todo eso ocurrira eliminando la url del mapping
public interface CurrencyExchangeProxy {
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to);
}
