package com.gme.price.api;

import com.gme.price.api.outbound.price.adapter.PriceAdapter;
import com.gme.price.api.outbound.price.mapper.PriceOutboundMapper;
import com.gme.price.api.outbound.price.persistence.PriceRepository;
import com.gme.price.api.outbound.price.persistence.jpa.PriceJpaRepository;
import com.gme.price.api.outbound.price.persistence.jpa.PriceRepositoryImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.mapstruct.factory.Mappers.getMapper;

@SpringBootApplication
public class OutboundTestApplication {

	@Bean
	PriceOutboundMapper priceOutboundMapper() {
		return getMapper(PriceOutboundMapper.class);
	}

	@Bean
	PriceRepositoryImpl priceRepositoryImpl(PriceJpaRepository priceJpaRepository) {
		return new PriceRepositoryImpl(priceJpaRepository);
	}

	@Bean
	PriceAdapter priceAdapter(PriceRepository priceRepository, PriceOutboundMapper priceOutboundMapper) {
		return new PriceAdapter(priceRepository, priceOutboundMapper);
	}
}
