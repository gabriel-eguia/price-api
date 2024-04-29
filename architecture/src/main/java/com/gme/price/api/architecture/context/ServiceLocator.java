package com.gme.price.api.architecture.context;


import com.gme.price.api.architecture.context.holder.ContextHolder;

import static org.slf4j.LoggerFactory.getLogger;

public final class ServiceLocator {

	private static final String TEST_CONTEXT_HOLDER = "com.gme.price.api.architecture.context.holder.TestContextHolder";
	private static ContextHolder contextHolder;

	private ServiceLocator() {
	}

	private static boolean isTestContextHolder(Class<?> clazz) {
		return clazz.getCanonicalName().equals(TEST_CONTEXT_HOLDER);
	}

	private static void initializeWithTestContextHolder() {
		try {
			contextHolder = (ContextHolder) Class.forName(TEST_CONTEXT_HOLDER).getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			getLogger(ServiceLocator.class).error("Could not load TestContextHolder", e);
		}
	}

	public static <T> T locate(Class<T> clazz) {
		if (contextHolder == null) initializeWithTestContextHolder();
		return contextHolder.locate(clazz);
	}

	public static boolean isInitialized() {
		return contextHolder != null && !isTestContextHolder(contextHolder.getClass());
	}

	public static void setContextHolder(ContextHolder holder) {
		contextHolder = holder;
	}
}
