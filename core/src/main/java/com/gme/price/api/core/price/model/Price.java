package com.gme.price.api.core.price.model;

import java.time.LocalDateTime;

public record Price(
		long productId,
		long brandId,
		double price,
		LocalDateTime startDate,
		LocalDateTime endDate,
		int priority
) {
}
