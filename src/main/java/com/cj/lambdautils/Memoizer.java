package com.cj.lambdautils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public class Memoizer {
	public static <I, O> Function<I, O> memoize(Function<I, O> f) {
	    ConcurrentMap<I, O> lookup = new ConcurrentHashMap<>();
	    return input -> lookup.computeIfAbsent(input, f);
	}
}
