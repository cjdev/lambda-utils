package com.cj.lambdautils;

import java.util.Optional;
import java.util.function.Supplier;

public class Try {    

	/**
	 * Will wrap a piece of code that throws an exception and re-throw that exception as a RuntimeException.
	 *    
	 * This allows pipelining much more easily than standard exception handling.
	 */
	public static void to(ThrowingRunnable function) {
        try {
            function.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	/**
	 * Allows you to encapsulate a supplier that returns a value inside of an Optional which will be empty if:
	 * <ol>
	 *    <li>The supplier returns null.
	 *    <li>The supplier throws an exception.
	 * </ol>
	 *    
	 * This allows pipelining much more easily than standard exception handling.
	 * @deprecated use to instead, which returns an either.
	 * @param function A function that may throw an exception or return null.
	 * @return the result of the function wrapped in an Optional.
	 */
	public static <T> Optional<T> too(ThrowingSupplier<T> function) {
		try {
            return Optional.ofNullable(function.supply());
        } catch (Exception e) {
            return Optional.empty();
        }
	}	
	
	public static <T> Either<Exception, T> to(ThrowingSupplier<T> function) {
		try {
            return Either.right(function.supply());
        } catch (Exception e) {
            return Either.left(e);
        } 
		
	}
}
