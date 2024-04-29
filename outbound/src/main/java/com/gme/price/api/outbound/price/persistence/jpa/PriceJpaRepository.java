package com.gme.price.api.outbound.price.persistence.jpa;

import com.gme.price.api.outbound.price.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {
	@Query("""
			select p from PriceEntity p
			where p.productId = :productId and p.brand.id = :brandId and :date between p.startDate and p.endDate
			""")
	List<PriceEntity> findByProductAndBrandAndDate(
			@Param("productId") Long productId,
			@Param("brandId") Long brandId,
			@Param("date") LocalDateTime date
	);
}
