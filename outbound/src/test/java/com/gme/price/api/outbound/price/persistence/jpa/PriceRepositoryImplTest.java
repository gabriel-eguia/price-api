package com.gme.price.api.outbound.price.persistence.jpa;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PriceRepositoryImplTest {

	private final PriceJpaRepository jpaRepository = mock(PriceJpaRepository.class);

	@Test
	void getPricesByProductIdBrandIdAndDate_whenInvoked_shouldCallJpaRepositoryWithSameParams() {
		Long productId = 1L;
		Long brandId = 2L;
		LocalDateTime date = LocalDateTime.now();
		PriceRepositoryImpl sut = new PriceRepositoryImpl(jpaRepository);

		sut.getPricesByProductIdBrandIdAndDate(productId, brandId, date);

		verify(jpaRepository).findByProductAndBrandAndDate(productId, brandId, date);
	}
}
