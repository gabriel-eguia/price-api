package com.gme.price.api.architecture.context.holder;

public interface ContextHolder {

	<T> T locate(Class<T> clazz);
}
