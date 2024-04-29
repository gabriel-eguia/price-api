package com.gme.price.api.architecture.usecase;

public abstract class UseCase<T> {

	String name = this.getClass().getName();

	protected abstract T run();

	protected T execute() {
		return run();
	}

	protected <R> R runInternal(UseCase<R> otherUsecase) {
		return otherUsecase.run();
	}
}
