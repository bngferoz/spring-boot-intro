package com.springFeroz.photoapp.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import reactor.core.publisher.Mono;

@Configuration
public class GlobalFiltersConfiguration {

	final Logger logger = LoggerFactory.getLogger(GlobalFiltersConfiguration.class);
	
	@Order(1)
	@Bean
	public GlobalFilter secondPreFilter()
	{
		return (exchange, chain)->{
			
			logger.info("Second Pre filter is working!!");
			return chain.filter(exchange).then(Mono.fromRunnable(()->{
				logger.info("Second Post filter is working!!");
				
			}));
		};
	}
	
	@Order(2)
	@Bean
	public GlobalFilter thirdPreFilter()
	{
		return (exchange, chain)->{
			
			logger.info("Third Pre filter is working!!");
			return chain.filter(exchange).then(Mono.fromRunnable(()->{
				logger.info("first Post filter is working!!");
				
			}));
		};
	}
}
