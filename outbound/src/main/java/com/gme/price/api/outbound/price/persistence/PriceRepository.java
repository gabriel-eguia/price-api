package com.gme.price.api.outbound.price.persistence;

import com.gme.price.api.outbound.price.model.PriceEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
	List<PriceEntity> getPricesByProductIdBrandIdAndDate(Long productId, Long brandId, LocalDateTime date);
}
