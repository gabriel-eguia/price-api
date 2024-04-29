package com.gme.price.api.inbound.price.rest.controller;

import com.gme.price.api.architecture.usecase.UseCaseBus;
import com.gme.price.api.core.price.usecase.GetApplicablePriceByProductBrandAndDateQry;
import com.gme.price.api.inbound.price.rest.dto.PriceRes;
import com.gme.price.api.inbound.price.rest.mapper.PriceInboundMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/price")
public class PriceController {

	private final UseCaseBus bus;
	private final PriceInboundMapper priceMapper;

	@GetMapping("/product/{productId}/brand/{brandId}/{date}")
	public ResponseEntity<PriceRes> getProductPriceByIdBrandAndDate(
			@PathVariable Long productId,
			@PathVariable Long brandId,
			@PathVariable LocalDateTime date
	) {
		return ResponseEntity.ok(
				priceMapper.toRes(
						bus.invoke(
								new GetApplicablePriceByProductBrandAndDateQry(
										productId,
										brandId,
										date
								)
						)
				)
		);
	}
}
