package com.cj.lambdautils;

/**
 * This interface represents a standard function that returns void and takes no parameters.
 * Typically such a function uses closure scoped variables and contains side-effects.
 * @author dron
 */
public interface ThrowingRunnable {
    void run() throws Exception;
}

