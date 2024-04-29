package com.gme.price.api;

import com.gme.price.api.inbound.price.rest.mapper.PriceInboundMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.mapstruct.factory.Mappers.getMapper;

@SpringBootApplication
public class InboundTestApplication {

	@Bean
	PriceInboundMapper priceInboundMapper() {
		return getMapper(PriceInboundMapper.class);
	}
}
