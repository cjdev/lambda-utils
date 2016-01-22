package com.cj.lambdautils;

import java.util.Optional;
import java.util.function.Supplier;

public class Try {    

	public static void to(ThrowingRunnable function) {
        try {
            function.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	public static <T> Optional<T> to(ThrowingSupplier<T> fn) {
		try {
            return Optional.ofNullable(fn.supply());
        } catch (Exception e) {
            return Optional.empty();
        }
	}

    /**
     * Convert checked exceptions to unchecked RuntimeExceptions.
     * @deprecated Use Try.to
     */
    public static void wrapAnyExceptions(ThrowingRunnable function) {
        to(function);
    }

    /**
     * Convert checked exceptions to unchecked RuntimeExceptions.
     * @deprecated Use Try.to
     */
    public static <T> Optional<T> withThrowing(ThrowingSupplier<T> fn) {
        return to(fn);
    }

    /**
     * Specialized use case: Only catch NullPointerException and return Optional.empty()
     * @deprecated use Try.to() instead.  Maybe this shouldn't be deprecated?
     */
    public static <T> Optional<T> nullSafeGet(Supplier<T> fn) {
        try {
            return Optional.of(fn.get());
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }
    
    /**
     * Convert checked exceptions to unchecked RuntimeExceptions.
     * @deprecated use Try.to instead
     */
    public static <T> T wrapAnyExceptions(ThrowingSupplier<T> function) {
        try {
            return function.supply();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	/**
	 * @param function
	 * @return
	 * @deprecated use Try.to() instead.
	 */
    public static <T> Optional<T> with(Supplier<T> function) {
        try {
            return Optional.ofNullable(function.get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }


	
}
