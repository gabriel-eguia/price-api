package com.gme.price.api.outbound.price.adapter;

import com.gme.price.api.core.price.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class PriceAdapterIT {

	private final Long productId = 35455L;
	private final Long brandId = 1L;
	private final LocalDateTime date = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
	@Autowired
	private PriceAdapter adapter;

	@Test
	void getPricesByProductIdBrandIdAndDate_whenProductIdNotExists_shouldReturnEmptyList() {
		Long nonExistingProductId = 999L;

		List<Price> prices = adapter.getPricesByProductIdBrandIdAndDate(nonExistingProductId, brandId, date);

		assertTrue(prices.isEmpty());
	}

	@Test
	void getPricesByProductIdBrandIdAndDate_whenBrandIdNotExists_shouldReturnEmptyList() {
		Long nonExistingBrandId = 999L;

		List<Price> prices = adapter.getPricesByProductIdBrandIdAndDate(productId, nonExistingBrandId, date);

		assertTrue(prices.isEmpty());
	}

	@Test
	void getPricesByProductIdBrandIdAndDate_whenDateWithoutPrices_shouldReturnEmptyList() {
		LocalDateTime dateWithoutPrices = LocalDateTime.now();

		List<Price> prices = adapter.getPricesByProductIdBrandIdAndDate(productId, brandId, dateWithoutPrices);

		assertTrue(prices.isEmpty());
	}

	@Test
	void getPricesByProductIdBrandIdAndDate_whenDateWithOnePrice_shouldReturnListWithOneElement() {
		LocalDateTime dateWithOnePrice = date.minusHours(6);

		List<Price> prices = adapter.getPricesByProductIdBrandIdAndDate(productId, brandId, dateWithOnePrice);

		assertEquals(1, prices.size());
	}

	@Test
	void getPricesByProductIdBrandIdAndDate_whenDateWithMultiplePrices_shouldReturnListWithMultipleElement() {
		List<Price> prices = adapter.getPricesByProductIdBrandIdAndDate(productId, brandId, date);

		assertTrue(prices.size() > 1);
	}
}
