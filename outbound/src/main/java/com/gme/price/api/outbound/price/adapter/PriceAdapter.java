package com.gme.price.api.outbound.price.adapter;

import com.gme.price.api.core.price.model.Price;
import com.gme.price.api.core.price.port.PricePort;
import com.gme.price.api.outbound.price.mapper.PriceOutboundMapper;
import com.gme.price.api.outbound.price.persistence.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceAdapter implements PricePort {

	private final PriceRepository repository;
	private final PriceOutboundMapper mapper;

	@Autowired
	PriceAdapter(
			PriceRepository priceRepository,
			PriceOutboundMapper priceOutboundMapper
	) {
		repository = priceRepository;
		mapper = priceOutboundMapper;
	}

	@Override
	public List<Price> getPricesByProductIdBrandIdAndDate(Long productId, Long brandId, LocalDateTime date) {
		return mapper.toModelList(repository.getPricesByProductIdBrandIdAndDate(productId, brandId, date));
	}
}
