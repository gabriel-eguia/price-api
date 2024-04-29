package com.gme.price.api.inbound.price.rest.controller;

import com.gme.price.api.architecture.usecase.UseCaseBus;
import com.gme.price.api.core.price.model.Price;
import com.gme.price.api.core.price.usecase.GetApplicablePriceByProductBrandAndDateQry;
import com.gme.price.api.inbound.price.rest.dto.PriceRes;
import com.gme.price.api.inbound.price.rest.mapper.PriceInboundMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PriceControllerTest {

	private final UseCaseBus bus = mock(UseCaseBus.class);
	private final PriceInboundMapper priceMapper = mock(PriceInboundMapper.class);
	private final Price price = mock(Price.class);
	private final PriceRes priceRes = mock(PriceRes.class);
	private final Long productId = 1L;
	private final Long brandId = 2L;
	private final LocalDateTime date = LocalDateTime.now();

	@Test
	void getProductPriceByIdBrandAndDate_whenInvoked_shouldCallGetApplicablePriceByProductBrandAndDateQry() {
		PriceController controller = new PriceController(bus, priceMapper);

		controller.getProductPriceByIdBrandAndDate(productId, brandId, date);

		verify(bus).invoke(any(GetApplicablePriceByProductBrandAndDateQry.class));
	}

	@Test
	void getProductPriceByIdBrandAndDate_whenInvoked_shouldReturnApplicablePrice() {
		doReturn(price).when(bus).invoke(any(GetApplicablePriceByProductBrandAndDateQry.class));
		doReturn(priceRes).when(priceMapper).toRes(price);
		PriceController controller = new PriceController(bus, priceMapper);

		ResponseEntity<PriceRes> result = controller.getProductPriceByIdBrandAndDate(productId, brandId, date);

		assertEquals(priceRes, result.getBody());
	}
}
