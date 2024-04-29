package com.gme.price.api.app.price;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gme.price.api.app.PricesApplication;
import com.gme.price.api.inbound.price.rest.dto.PriceRes;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PricesApplication.class)
@AutoConfigureMockMvc
class PricesIT {

	private static final long PRODUCT_ID = 35455;
	private static final long BRAND_ID = 1;

	@Autowired
	private MockMvc mvc;

	private static Stream<Arguments> pricesParamsAndExpectedResponses() {
		return Stream.of(
				Arguments.of(PRODUCT_ID, BRAND_ID, LocalDateTime.of(2020, 6, 14, 10, 0), 35.50),
				Arguments.of(PRODUCT_ID, BRAND_ID, LocalDateTime.of(2020, 6, 14, 16, 0), 25.45),
				Arguments.of(PRODUCT_ID, BRAND_ID, LocalDateTime.of(2020, 6, 14, 21, 0), 35.50),
				Arguments.of(PRODUCT_ID, BRAND_ID, LocalDateTime.of(2020, 6, 15, 10, 0), 30.50),
				Arguments.of(PRODUCT_ID, BRAND_ID, LocalDateTime.of(2020, 6, 16, 21, 0), 38.95)
		);
	}

	@ParameterizedTest
	@MethodSource("pricesParamsAndExpectedResponses")
	void getApplicablePrices_whenInvoked_shouldReturnCorrectPrice(long productId, long brandId, LocalDateTime date, double expectedPrice) throws Exception {
		MvcResult mvcResult = mvc.perform(
				get("/price/product/" + productId + "/brand/" + brandId + "/" + date)
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk()).andReturn();

		PriceRes price = mapFromJson(mvcResult.getResponse().getContentAsString());

		assertEquals(expectedPrice, price.price());
	}

	private <T> T mapFromJson(String json) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		return objectMapper.readValue(json, (Class<T>) PriceRes.class);
	}
}
