package com.cj.lambdautils;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Functions {
	public static <T> Function<T, Void> voidFunction(Consumer<T> consumer){
		return (Function<T, Void>) t-> {
			consumer.accept(t);
			return null;
		};
	}
	
	public static <T, U, V> Map<V, U>  retypeValues(Map<V, T> input, Function<T, U> valueMapper){
		return input.keySet().stream().collect(Collectors.toMap(k->k, k->valueMapper.apply(input.get(k))));
	}
}
