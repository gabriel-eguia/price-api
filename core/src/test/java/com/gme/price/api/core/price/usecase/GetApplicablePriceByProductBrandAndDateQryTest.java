package com.gme.price.api.core.price.usecase;

import com.gme.price.api.core.price.model.Price;
import com.gme.price.api.core.price.port.PricePort;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetApplicablePriceByProductBrandAndDateQryTest {

	private final PricePort pricePort = mock(PricePort.class);

	@Test
	void run_whenInvoked_shouldCallPricePortGetPricesByProductIdBrandIdAndDate() {
		Long productId = 1L;
		Long brandId = 2L;
		LocalDateTime date = LocalDateTime.now();
		List<Price> expectedPrices = List.of(mock(Price.class));
		doReturn(expectedPrices).when(pricePort).getPricesByProductIdBrandIdAndDate(productId, brandId, date);

		new GetApplicablePriceByProductBrandAndDateQry(productId, brandId, date, pricePort).run();

		verify(pricePort).getPricesByProductIdBrandIdAndDate(productId, brandId, date);
	}

	@Test
	void run_whenMultiplePricesExists_shouldReturnPriceWithHighestPriority() {
		Long productId = 1L;
		Long brandId = 2L;
		LocalDateTime date = LocalDateTime.now();
		Price highPriorityPrice = new Price(productId, brandId, 11.0, date, date, 1);
		Price lowPriorityPrice = new Price(productId, brandId, 10.0, date, date, 0);
		List<Price> expectedPrices = List.of(lowPriorityPrice, highPriorityPrice);
		doReturn(expectedPrices).when(pricePort).getPricesByProductIdBrandIdAndDate(productId, brandId, date);

		Price result = new GetApplicablePriceByProductBrandAndDateQry(productId, brandId, date, pricePort).run();

		assertEquals(highPriorityPrice, result);
	}
}
