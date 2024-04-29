package com.gme.price.api.core.price.port;

import com.gme.price.api.core.price.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PricePort {
	List<Price> getPricesByProductIdBrandIdAndDate(Long productId, Long brandId, LocalDateTime date);
}
