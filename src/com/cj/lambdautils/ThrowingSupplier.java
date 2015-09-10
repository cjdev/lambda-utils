package com.cj.lambdautils;


@FunctionalInterface
public interface ThrowingSupplier<T>{
	public T supply() throws Exception;
}
