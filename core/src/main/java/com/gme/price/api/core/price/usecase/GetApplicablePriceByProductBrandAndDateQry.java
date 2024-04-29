package com.gme.price.api.core.price.usecase;

import com.gme.price.api.architecture.usecase.Query;
import com.gme.price.api.core.price.model.Price;
import com.gme.price.api.core.price.port.PricePort;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static com.gme.price.api.architecture.context.ServiceLocator.locate;

public class GetApplicablePriceByProductBrandAndDateQry extends Query<Price> {

	private final Long productId;
	private final Long brandId;
	private final LocalDateTime date;
	private final PricePort pricePort;

	GetApplicablePriceByProductBrandAndDateQry(
			Long productId,
			Long brandId,
			LocalDateTime date,
			PricePort pricePort
	) {
		this.productId = productId;
		this.brandId = brandId;
		this.date = date;
		this.pricePort = pricePort;
	}

	public GetApplicablePriceByProductBrandAndDateQry(
			Long productId,
			Long brandId,
			LocalDateTime date
	) {
		this(productId, brandId, date, locate(PricePort.class));
	}

	@Override
	protected Price run() {
		List<Price> availablePrices = pricePort.getPricesByProductIdBrandIdAndDate(productId, brandId, date);
		return availablePrices.isEmpty() ? null : availablePrices.stream().max(Comparator.comparing(Price::priority)).get();
	}
}
