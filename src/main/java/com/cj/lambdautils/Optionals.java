package com.cj.lambdautils;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Optionals {
    /**
     * This method is intended to be used to shorten
     * Stream<Optional<T>> stream
     * <p>
     * From:
     * stream.filter(Stream::isPresent).map(Stream::get)
     * <p>
     * To:
     * stream.flatMap(Optionals.nonEmpty())
     *
     * @return
     */
    public static <T, U> Function<Optional<T>, Stream<T>> nonEmpty() {
        return (a) -> a.isPresent() ? Stream.of(a.get()) : Stream.empty();
    }
}
