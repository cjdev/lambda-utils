package com.cj.lambdautils;


import java.util.Optional;
import java.util.function.Supplier;


public class Try {
    public static <T> Optional<T> with(Supplier<T> function){
        try{
            return Optional.ofNullable(function.get());
        }catch(Exception e){
            return Optional.empty();
        }
    }
    
    /**
     * Convert checked exceptions to unchecked RuntimeExceptions.
     */
	public static <T>T wrapAnyExceptions(ThrowingSupplier<T> function){
		try{
			return function.supply();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
    /**
     * Convert checked exceptions to unchecked RuntimeExceptions.
     */	
	public static void wrapAnyExceptions(Runnable function){
		try{
			function.run();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

    public static <T> Optional<T> withThrowing(ThrowingSupplier<T> fn){
        return with(() -> wrapAnyExceptions(fn));
    }

    /**
     * Specialized use case: Only catch NullPointerException and return Optional.empty()
     */
    public static <T> Optional<T> nullSafeGet(Supplier<T> fn) {
        try{
            return Optional.of(fn.get());
        }catch(NullPointerException e){
            return Optional.empty();
        }
    }
}
