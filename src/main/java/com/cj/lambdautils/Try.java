package com.cj.lambdautils;

import java.util.Optional;

public class Try {    

	/**
	 * Will wrap a piece of code that throws an exception and re-throw that exception as a RuntimeException.
	 *    
	 * This allows pipelining much more easily than standard exception handling.
	 */
	public static void orElseRuntimeException(ThrowingRunnable function) {
        try {
            function.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	public static Either<Exception, Void> to(ThrowingRunnable function) {
        try {
        	function.run();
            return Either.right(null);
        } catch (Exception e) {
            return Either.left(e);
        }
	}
	
	public static <T> Optional<T> toOption(ThrowingSupplier<T> function) {
		return to(function).getRight();
	}	
	
	/**
	 * Allows you to encapsulate a supplier that returns a value inside of an Either which will be empty if:
	 * <ol>
	 *    <li>The supplier returns null.
	 *    <li>The supplier throws an exception.
	 * </ol>
	 *    
	 * This allows pipelining much more easily than standard exception handling.
	 * @param function A function that may throw an exception or return null.
	 * @return the result of the function wrapped in an Optional.
	 */
	public static <T> Either<Exception, T> to(ThrowingSupplier<T> function) {
		try {
            return Either.right(function.supply());
        } catch (Exception e) {
            return Either.left(e);
        } 
		
	}
}
