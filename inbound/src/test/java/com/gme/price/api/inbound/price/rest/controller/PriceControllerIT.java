package com.gme.price.api.inbound.price.rest.controller;

import com.gme.price.api.architecture.usecase.UseCaseBus;
import com.gme.price.api.core.price.model.Price;
import com.gme.price.api.core.price.usecase.GetApplicablePriceByProductBrandAndDateQry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
class PriceControllerIT {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UseCaseBus bus;

	@Test
	void getProductPriceByIdBrandAndDate() throws Exception {
		long productId = 1L;
		long brandId = 2L;
		LocalDateTime date = LocalDateTime.now();
		doReturn(new Price(1, 2, 10.0, date, date, 0)).when(bus).invoke(any(GetApplicablePriceByProductBrandAndDateQry.class));

		mvc.perform(
				get("/price/product/" + productId + "/brand/" + brandId + "/" + date)
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk());
	}
}
