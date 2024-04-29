package com.gme.price.api.outbound.price.mapper;

import com.gme.price.api.core.price.model.Price;
import com.gme.price.api.outbound.price.model.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PriceOutboundMapper {
	@Mapping(target = "brandId", source = "brand.id")
	Price toModel(PriceEntity priceEntity);

	List<Price> toModelList(List<PriceEntity> priceEntities);
}
