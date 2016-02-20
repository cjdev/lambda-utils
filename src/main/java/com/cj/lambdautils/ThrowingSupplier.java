package com.cj.lambdautils;

/**
 * A ThrowingSupplier is a function that takes no parameters and returns one parameter, T.
 * Similar to java.util.Supplier except this can throw any type of exception.
 * @see com.cj.lambdautils.Try#to(ThrowingSupplier) 
 * @author dron
 */
@FunctionalInterface
public interface ThrowingSupplier<T> {
    T supply() throws Exception;
}
