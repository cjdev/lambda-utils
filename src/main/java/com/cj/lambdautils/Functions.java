package com.cj.lambdautils;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Functions {
    /**
     * Convenience method to convert a consumer into a "function" that returns void.
     *
     * @param consumer
     * @return
     */
    public static <T> Function<T, Void> voidFunction(Consumer<T> consumer) {
        return (Function<T, Void>) t -> {
            consumer.accept(t);
            return null;
        };
    }

    /**
     * Convenience method to change the type of the values of a map.
     *
     * @param input
     * @param valueMapper
     * @return
     */
    public static <T, U, V> Map<V, U> retypeValues(Map<V, T> input, Function<T, U> valueMapper) {
        return input.keySet().stream().collect(Collectors.toMap(k -> k, v -> {
            try {
                return (U) valueMapper.apply(input.get(v));
            } catch (NullPointerException e) {
                throw new RuntimeException("The provided function must return a non-null value.", e);
            }
        }));
    }

    /**
     * Converts the iterator to a non-parallel stream.
     *
     * @param iterator
     * @return
     */
    public static <T> Stream<T> streamOf(Iterator<T> iterator) {
        return streamOf(iterator, false);
    }

    /**
     * Converts the iterator into a stream, can be parallel or not.
     *
     * @param iterator
     * @param parallel True for parallel, False For Non-Parallel.
     * @return
     */
    public static <T> Stream<T> streamOf(Iterator<T> iterator, boolean parallel) {
        Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }
    

    /**
     * Allows you to construct a non-parallel stream out of a function that supplies values.
     * The stream ends when Optiona.empty() is returned.
     * @param supplier
     * @return
     */
	public static <T> Stream<T> streamOf(Supplier<Optional<T>> supplier) {
		return new FiniteGenerator<T>(supplier).stream();
	}
}
