package com.gme.price.api.outbound.price.adapter;

import com.gme.price.api.core.price.model.Price;
import com.gme.price.api.outbound.price.mapper.PriceOutboundMapper;
import com.gme.price.api.outbound.price.model.PriceEntity;
import com.gme.price.api.outbound.price.persistence.PriceRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

class PriceAdapterTest {

	private final PriceRepository priceRepository = mock(PriceRepository.class);
	private final PriceOutboundMapper priceMapper = mock(PriceOutboundMapper.class);

	@Test
	void getPricesByProductIdBrandIdAndDate_whenInvoked_shouldCallPriceRepositoryGetPricesByProductIdBrandIdAndDate() {
		PriceEntity priceEntity = mock(PriceEntity.class);
		Price price = mock(Price.class);
		Long productId = 1L;
		Long brandId = 2L;
		LocalDateTime date = LocalDateTime.now();
		doReturn(List.of(priceEntity)).when(priceRepository).getPricesByProductIdBrandIdAndDate(productId, brandId, date);
		doReturn(price).when(priceMapper).toModel(priceEntity);
		PriceAdapter sut = new PriceAdapter(priceRepository, priceMapper);

		sut.getPricesByProductIdBrandIdAndDate(productId, brandId, date);

		verify(priceRepository).getPricesByProductIdBrandIdAndDate(productId, brandId, date);
	}
}
