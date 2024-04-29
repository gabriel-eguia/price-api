package com.gme.price.api.architecture.context.holder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SpringContextHolderTest {

	private ApplicationContext applicationContext;
	private SpringContextHolder contextHolder;

	@BeforeEach
	void setUp() {
		applicationContext = mock(ApplicationContext.class);
		contextHolder = new SpringContextHolder(applicationContext);
	}

	@Test
	void locate_whenInvoked_shouldCallApplicationContext() {
		contextHolder.locate(Object.class);
		verify(applicationContext).getBean(Object.class);
	}
}
