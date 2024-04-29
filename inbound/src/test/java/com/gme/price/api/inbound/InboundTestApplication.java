package com.gme.price.api.inbound;

import com.gme.price.api.inbound.price.rest.mapper.PriceInboundMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.mapstruct.factory.Mappers.getMapper;

@SpringBootApplication(scanBasePackages = "com.gme.price.api")
public class InboundTestApplication {

	@Bean
	PriceInboundMapper priceInboundMapper() {
		return getMapper(PriceInboundMapper.class);
	}
}
