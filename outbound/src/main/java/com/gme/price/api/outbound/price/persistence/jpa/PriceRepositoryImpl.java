package com.gme.price.api.outbound.price.persistence.jpa;

import com.gme.price.api.outbound.price.model.PriceEntity;
import com.gme.price.api.outbound.price.persistence.PriceRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
@AllArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

	private final PriceJpaRepository jpaRepository;

	@Override
	public List<PriceEntity> getPricesByProductIdBrandIdAndDate(Long productId, Long brandId, LocalDateTime date) {
		return jpaRepository.findByProductAndBrandAndDate(productId, brandId, date);
	}
}
