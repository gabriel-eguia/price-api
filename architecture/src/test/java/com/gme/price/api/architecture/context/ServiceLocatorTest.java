package com.gme.price.api.architecture.context;

import com.gme.price.api.architecture.context.holder.ContextHolder;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ServiceLocatorTest {

	@Test
	void locate_whenInvoked_shouldCallApplicationContext() {
		ContextHolder holder = mock(ContextHolder.class);
		ServiceLocator.setContextHolder(holder);
		ServiceLocator.locate(Object.class);
		verify(holder).locate(Object.class);
	}
}
