package com.gme.price.api.inbound.price.rest.dto;

import java.time.LocalDateTime;

public record PriceRes(
		long productId,
		long brandId,
		double price,
		LocalDateTime startDate,
		LocalDateTime endDate
) {
}
