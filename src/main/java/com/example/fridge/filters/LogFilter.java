package com.example.fridge.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class LogFilter implements WebFilter
{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) 
	{
		System.out.println(String.format("RestCal at  %s -  Method: %s , Path: %s ", java.time.LocalDateTime.now(), exchange.getRequest().getMethod(), exchange.getRequest().getPath()));
		return chain.filter(exchange);
	}

}
