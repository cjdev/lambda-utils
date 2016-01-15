package com.cj.lambdautils;

@FunctionalInterface
public interface ThrowingSupplier<T> {
    T supply() throws Exception;
}
