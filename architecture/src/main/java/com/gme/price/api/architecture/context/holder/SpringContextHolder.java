package com.gme.price.api.architecture.context.holder;

import com.gme.price.api.architecture.context.ServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SpringContextHolder implements ContextHolder {

	private final ApplicationContext context;

	@Autowired
	SpringContextHolder(ApplicationContext applicationContext) {
		context = applicationContext;
		if (!ServiceLocator.isInitialized()) ServiceLocator.setContextHolder(this);
	}

	@Override
	public <T> T locate(Class<T> clazz) {
		return context.getBean(clazz);
	}
}
