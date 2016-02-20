package com.cj.lambdautils;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class Optionals {
    /**
     * This method is intended to be used to filter out the empty values in a stream of optionals in a pipeline.  So, given a Stream&lt;Optional&lt;T>>, 
     * <p>
     * Instead Of:
     * <pre>
	 * {@code
	 * stream.filter(Stream::isPresent).map(Stream::get)
	 * }
	 * </pre>
     * 
     * <p>
     * You can:
     * <pre>
	 * {@code
	 * stream.flatMap(Optionals.nonEmpty())
	 * }
	 * </pre>
     * 
     *
     * @return
     */
    public static <T, U> Function<Optional<T>, Stream<T>> nonEmpty() {
        return (a) -> a.isPresent() ? Stream.of(a.get()) : Stream.empty();
    }
}
