package com.cj.lambdautils;

import java.util.function.Consumer;
import java.util.function.Function;

public class Functions {
	public <T> Function<T, Void> voidFunction(Consumer<T> consumer){
		return (Function<T, Void>) t-> {
			consumer.accept(t);
			return null;
		};
	}
}
