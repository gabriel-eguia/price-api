package com.gme.price.api.inbound.price.rest.mapper;

import com.gme.price.api.core.price.model.Price;
import com.gme.price.api.inbound.price.rest.dto.PriceRes;
import org.mapstruct.Mapper;

@Mapper
public interface PriceInboundMapper {

	PriceRes toRes(Price price);
}
